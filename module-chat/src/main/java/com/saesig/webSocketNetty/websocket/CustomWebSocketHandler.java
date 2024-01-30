package com.saesig.webSocketNetty.websocket;

import com.saesig.config.auth.SessionMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.saesig.webSocketNetty.netty.NettyChattingClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();
    private static HashMap<Long, HashMap<Long, List<String> >> chatMap = new HashMap<Long, HashMap<Long,List<String>>>();

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
        Long chatId = Long.valueOf(getUrlParam(url,"chatId"));
        SessionMember senderMember = (SessionMember) session.getAttributes().get("testMember");
        Long memberId = senderMember.getId();
        if (chatMap.containsKey(memberId)){ // 이미 chatMap에 존재하는 멤버일경우.
            HashMap<Long,List<String>> memberIdMapOrigin = chatMap.get(memberId);
            if (memberIdMapOrigin.containsKey(chatId)){ // 이미 chatMap에 memberMap에 존재하는 채팅방일경우.
                chatMap.get(memberId).get(chatId).add(sessionId);
            }else{
                List<String> chatIdList = new ArrayList<String>();
                chatIdList.add(sessionId);
                chatMap.get(memberId).put(chatId,chatIdList);
            }
            chatMap.put(memberId,memberIdMapOrigin);
        }else{
            HashMap<Long,List<String>> memberIdMap = new HashMap<>();
            List<String> chatIdList = new ArrayList<>();
            chatIdList.add(sessionId);
            memberIdMap.put(chatId,chatIdList);
            chatMap.put(memberId,memberIdMap);
        }
        webSocketSendMessage.setSession(numSet);
        webSocketSendMessage.setChatMap(chatMap);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        numSet.remove(session);
        String sessionId = session.getId();
        SessionMember senderMember = (SessionMember) session.getAttributes().get("testMember");
        Long memberId = senderMember.getId();
        Long chatId = Long.valueOf(getUrlParam(session.getUri().toString(),"chatId"));
        chatMap.get(memberId).get(chatId).remove(sessionId);
        if (chatMap.get(memberId).get(chatId).size() == 0){
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