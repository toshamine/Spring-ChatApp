package dev.chiba.ChatApp.dto;

import dev.chiba.ChatApp.dto.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType messageType;
}
