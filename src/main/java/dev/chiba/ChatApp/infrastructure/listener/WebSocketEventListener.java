package dev.chiba.ChatApp.infrastructure.listener;


import dev.chiba.ChatApp.dto.ChatMessage;
import dev.chiba.ChatApp.dto.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnect(
            SessionDisconnectEvent event
    )
    {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("user {} disconnected",username);
            ChatMessage chatMessage = ChatMessage.builder()
                    .messageType(MessageType.LEAVE)
                    .content("user "+username+" disconnected")
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("topic/public",chatMessage);
        }
    }
}
