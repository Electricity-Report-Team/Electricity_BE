package com.electricity.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ChatService {

  @Value("${openai.api-key}")
  private String apiKey;

  private final JdbcTemplate jdbcTemplate;
  private final RestTemplate restTemplate = new RestTemplate();

  public String getChatAnswer(String userMessage) {
    // 키워드 추출
    String region = extractRegion(userMessage);
    String industry = extractIndustry(userMessage);
    Integer year = extractYear(userMessage);

    String usage = null;
    String prediction = null;

    if (region != null && industry != null && year != null) {
      usage = getPowerUsage(region, industry, year); // 연도 지정된 월별 사용량
      prediction = getPrediction(region, industry);  // 예측값은 항상 2026
    } else if (region != null && industry != null) {
      usage = getPowerUsage(region, industry); // 연도 없으면 연도별 사용량
      prediction = getPrediction(region, industry);
    }

    // 메시지 생성
    StringBuilder systemMessage = new StringBuilder("""
      당신은 대한민국 전력 데이터 도우미입니다.
      사용자의 질문에 따라 실제 DB에서 조회한 데이터를 기반으로 정확하게 응답해야 합니다.
      서울뿐 아니라 전국 모든 지역(예: 광주, 경기 등)의 데이터가 존재합니다.
    """);

    if (usage != null && !usage.isBlank() && !usage.contains("없습니다")) {
      systemMessage.append("\n\n📊 과거 전력 사용량:\n").append(usage);
    }

    if (prediction != null && !prediction.isBlank() && !prediction.contains("없습니다")) {
      systemMessage.append("\n\n📈 예측 전력 사용량:\n").append(prediction);
    }

    List<Map<String, String>> messages = List.of(
        Map.of("role", "system", "content", systemMessage.toString()),
        Map.of("role", "user", "content", userMessage)
    );

    // GPT API 호출
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(apiKey);
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = Map.of(
        "model", "gpt-3.5-turbo",
        "messages", messages
    );

    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

    try {
      ResponseEntity<JsonNode> response = restTemplate.postForEntity(
          "https://api.openai.com/v1/chat/completions",
          entity,
          JsonNode.class
      );
      return "GPT: " + response.getBody()
          .get("choices").get(0)
          .get("message").get("content").asText();
    } catch (Exception e) {
      e.printStackTrace();
      return "GPT 응답 실패!";
    }
  }

  // 🔍 지역 추출
  private String extractRegion(String message) {
    List<String> regions = jdbcTemplate.queryForList("SELECT regionName FROM Region", String.class);
    for (String region : regions) {
      if (message.contains(region)) return region;
    }
    return null;
  }

  // 🔍 산업 추출
  private String extractIndustry(String message) {
    List<String> industries = jdbcTemplate.queryForList("SELECT industryName FROM Industry", String.class);
    for (String industry : industries) {
      if (message.contains(industry)) return industry;
    }
    return null;
  }

  // 🔍 연도 추출
  private Integer extractYear(String message) {
    Pattern pattern = Pattern.compile("(20\\d{2})년");
    Matcher matcher = pattern.matcher(message);
    if (matcher.find()) {
      return Integer.parseInt(matcher.group(1));
    }
    return null;
  }

  // ✅ 연도별 총합 조회
  private String getPowerUsage(String regionName, String industryName) {
    String sql = """
        SELECT year, ROUND(SUM(power), 2) AS totalPower
        FROM MonthlyEnergyStates
        WHERE regionId = (SELECT regionId FROM Region WHERE regionName = ?)
          AND industryId = (SELECT industryId FROM Industry WHERE industryName = ?)
        GROUP BY year
        ORDER BY year
    """;

    List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, regionName, industryName);
    if (result.isEmpty()) return "해당 조합에 대한 전력 사용 데이터가 없습니다.";

    StringBuilder sb = new StringBuilder(regionName + " " + industryName + "의 연도별 전력 소비량입니다:\n");
    for (Map<String, Object> row : result) {
      int year = ((Number) row.get("year")).intValue();
      double power = ((Number) row.get("totalPower")).doubleValue();
      sb.append(String.format("- %d년: %, .0fWh\n", year, power));
    }
    return sb.toString();
  }

  // ✅ 특정 연도 월별 조회
  private String getPowerUsage(String regionName, String industryName, Integer year) {
    String sql = """
      SELECT month, ROUND(SUM(power), 2) AS totalPower
      FROM MonthlyEnergyStates
      WHERE regionId = (SELECT regionId FROM Region WHERE regionName = ?)
        AND industryId = (SELECT industryId FROM Industry WHERE industryName = ?)
        AND year = ?
      GROUP BY month
      ORDER BY month
    """;

    List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, regionName, industryName, year);
    if (result.isEmpty()) return "해당 연도에 대한 전력 사용 데이터가 없습니다.";

    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%d년 %s %s의 월별 전력 사용량은 다음과 같습니다:\n", year, regionName, industryName));
    for (Map<String, Object> row : result) {
      int month = ((Number) row.get("month")).intValue();
      double power = ((Number) row.get("totalPower")).doubleValue();
      sb.append(String.format("- %d월: %, .0fWh\n", month, power));
    }
    return sb.toString();
  }

  // ✅ 예측 전력 조회 (2026)
  private String getPrediction(String regionName, String industryName) {
    String sql = """
      SELECT predictMonth, ROUND(predictPower, 2) AS power
      FROM Predict
      WHERE regionId = (SELECT regionId FROM Region WHERE TRIM(regionName) = ?)
        AND industryId = (SELECT industryId FROM Industry WHERE TRIM(industryName) = ?)
      ORDER BY predictMonth
    """;

    List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, regionName.trim(), industryName.trim());
    if (result.isEmpty()) return "예측 데이터가 없습니다.";

    double total = 0;
    StringBuilder sb = new StringBuilder("2026년 ")
        .append(regionName).append(" ").append(industryName)
        .append("의 월별 예측 전력 소비량입니다:\n");
    for (Map<String, Object> row : result) {
      int month = ((Number) row.get("predictMonth")).intValue();
      double power = ((Number) row.get("power")).doubleValue();
      sb.append(String.format(" - %d월: %, .0fWh\n", month, power));
      total += power;
    }
    sb.append(String.format("\n총합: %, .0fWh\n", total));
    sb.append(String.format("평균: %, .0fWh", total / 12.0));
    return sb.toString();
  }

  // ✅ 전체 지역 합산 예측 (선택적 활용 가능)
  private String getPrediction(String regionName) {
    String sql = """
      SELECT predictMonth, ROUND(SUM(predictPower), 2) AS power
      FROM Predict
      WHERE regionId = (SELECT regionId FROM Region WHERE regionName = ?)
      GROUP BY predictMonth
      ORDER BY predictMonth
    """;

    List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, regionName);
    if (result.isEmpty()) return "예측 데이터가 없습니다.";

    double total = 0;
    StringBuilder sb = new StringBuilder(regionName + "의 2026년 월별 전력 소비량 예측:\n");
    for (Map<String, Object> row : result) {
      int month = ((Number) row.get("predictMonth")).intValue();
      double power = ((Number) row.get("power")).doubleValue();
      sb.append(String.format("- %d월: %, .0fWh\n", month, power));
      total += power;
    }
    sb.append(String.format("\n총합: %, .0fWh\n", total));
    sb.append(String.format("평균: %, .0fWh", total / 12.0));
    return sb.toString();
  }
}
