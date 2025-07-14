package com.electricity.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
  private String message;

  public ChatDto(String message){
    this.message = message;
  }

}
