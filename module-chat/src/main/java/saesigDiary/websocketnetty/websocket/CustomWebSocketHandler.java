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

import java.util.HashMap;
import java.util.LinkedHashSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();
    private static HashMap<Integer,String[]> roomMap = new HashMap<Integer, String[]>();
    private static HashMap<Integer, HashMap<Integer,String[]>> chatMap = new HashMap<Integer, HashMap<Integer,String[]>>();

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
        int chatId = Integer.parseInt(getUrlParam(url,"chatId"));
        int memberId = Integer.parseInt(getUrlParam(url,"memberId"));
        if (chatMap.containsKey(memberId)){ // 이미 chatMap에 존재하는 멤버일경우.
            HashMap<Integer,String[]> memberIdMapOrigin = chatMap.get(memberId);
            if (memberIdMapOrigin.containsKey(chatId)){ // 이미 chatMap에 memberMap에 존재하는 채팅방일경우.
                String[] chatIdArrayOrigin = memberIdMapOrigin.get(chatId);
                String[] chatIdArrayNew = new String[chatIdArrayOrigin.length+1];
                for (int i=0; i<chatIdArrayOrigin.length; i++){
                    chatIdArrayNew[i] = chatIdArrayOrigin[i];
                }
                chatIdArrayNew[chatIdArrayOrigin.length] = sessionId;
                memberIdMapOrigin.put(chatId,chatIdArrayNew);
            }else{
                String[] chatIdArrayNew = {sessionId};
                memberIdMapOrigin.put(chatId,chatIdArrayNew);
            }
            chatMap.put(memberId,memberIdMapOrigin);
        }else{
            HashMap<Integer,String[]> memberIdMap = new HashMap<>();
            String[] chatIdArrayNew = {sessionId};
            memberIdMap.put(chatId,chatIdArrayNew);
            chatMap.put(memberId,memberIdMap);
        }
        webSocketSendMessage.setSession(numSet);
        webSocketSendMessage.setChatMap(chatMap);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        numSet.remove(session);
        String sessionId = session.getId();
        Integer memberId = Integer.parseInt(getUrlParam(session.getUri().toString(),"memberId"));
        Integer chatId = Integer.parseInt(getUrlParam(session.getUri().toString(),"chatId"));
        HashMap<Integer, String[]> memberMap = chatMap.get(memberId);
        String[] chatIdMap = memberMap.get(chatId);
        String[] newSessionList = new String[chatIdMap.length-1];
        int c = 0;
        for (int i=0; i<chatIdMap.length; i++){
            if (!chatIdMap[i].equals(sessionId)){
                newSessionList[c] = chatIdMap[i];
                c = c+1;
            }
        }
        if (newSessionList.length > 0){
            chatMap.get(memberId).put(chatId,newSessionList);
        }else{
            chatMap.get(memberId).remove(chatId);
            if (chatMap.get(memberId).size() == 0){
                chatMap.remove(memberId);
            }
        }
    }

    public String getUrlParam(String url, String paramName){
        String param = url.split("\\?")[1];
        String[] paramArray = param.split("&");
        for (String i : paramArray){
            if (i.split("=")[0].equals(paramName)){
                return i.split("=")[1];
            }
        }
        return null;
    }

}