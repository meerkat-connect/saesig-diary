package saesigDiary.websocketnetty.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import saesigDiary.websocketnetty.netty.NettyChattingClient;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();
    private static HashMap<Integer,String[]> roomMap = new HashMap<Integer, String[]>();


    private final WebSocketSendMessage webSocketSendMessage;
    private final NettyChattingClient nettyChattingClient;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();

        boolean isSessionAlive = false;

        for(WebSocketSession sess: numSet) {
            if(sess.getId().equals(session.getId())){
                isSessionAlive = true;
            }
        }
        if(isSessionAlive){
            nettyChattingClient.sendToServer(payload);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        numSet.add(session);
        String sessionId = session.getId();
        String url = session.getUri().toString();
        int chatId = Integer.parseInt(url.split("=")[1]);
        if (roomMap.containsKey(chatId)){
            String[] chatIdArrayOrigin = roomMap.get(chatId);
            String[] chatIdArrayNew = new String[chatIdArrayOrigin.length+1];
            for (int i=0; i<chatIdArrayOrigin.length; i++){
                chatIdArrayNew[i] = chatIdArrayOrigin[i];
            }
            chatIdArrayNew[chatIdArrayOrigin.length] = sessionId;
            roomMap.put(chatId,chatIdArrayNew);
        }else{
            String[] chatIdArrayNew = {sessionId};
            roomMap.put(chatId,chatIdArrayNew);
        }
        webSocketSendMessage.setSession(numSet);
        webSocketSendMessage.setRoopMap(roomMap);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        numSet.remove(session);
        String[] currntSessionList = roomMap.get(session.getUri().toString().split("=")[1]);
        String[] newSessionList = new String[currntSessionList.length-1];

        for (int i=0; i<currntSessionList.length; i++){
            int c=0;
            if (!currntSessionList[i].equals(session.getId())){
                newSessionList[c] = currntSessionList[i];
                c = c + 1;
            }
        }
        int cs = Integer.parseInt(session.getUri().toString().split("=")[1]);
        if (roomMap.get(cs).length == 0){
            roomMap.remove(cs);
        }else{
            roomMap.put(cs,newSessionList);
        }
    }
}