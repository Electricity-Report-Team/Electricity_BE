package com.electricity.backend.controller;

import com.electricity.backend.dto.ChatDto;
import com.electricity.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
  private final ChatService chatService;

  @PostMapping
  public ResponseEntity<String> chat(@RequestBody ChatDto request) {
    String answer = chatService.getChatAnswer(request.getMessage());
    return ResponseEntity.ok(answer);
  }
}
