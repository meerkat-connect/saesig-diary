package com.saesig.webSocketNetty.websocket;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.saesig.webSocketNetty.chatting.ChattingService;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    @Autowired
    private ChattingService chattingService;

    private HashMap<Integer, String[]> roomMap = new HashMap<Integer, String[]>();

    private static HashMap<Integer, HashMap<Integer, List<String> >> chatMap = new HashMap<Integer, HashMap<Integer,List<String>>>();


    public void sendMessage(String payload) throws Exception {

        Gson gson = new Gson();

        chatJsonData param = gson.fromJson(payload, chatJsonData.class);
        TextMessage msg = new TextMessage(payload);
        int chatId = param.getChatId();
        int senderId = param.getMemberId();
        int receiver = param.getReceiverId();
        for (WebSocketSession sess : numSet) {
            String sessionId = sess.getId();
            boolean isSend = isCheckSendSession(sessionId, senderId, chatId);
            if (isSend == false){
                isSend = isCheckSendSession(sessionId, receiver, chatId);
            }
            if (isSend) {
                sess.sendMessage(msg);
            }
        }
        SaveDbChat(param.getType(), chatId, senderId, receiver,param.getText());
    }

    public boolean isCheckSendSession(String sessionId, int targetMemberId, int chatId){ // 세션마다 채팅 보낼지 말지 여부 체크
        if (chatMap.containsKey(targetMemberId)){
            if (chatMap.get(targetMemberId).containsKey(chatId)){
                if (chatMap.get(targetMemberId).get(chatId).contains(sessionId)){
                    return true;
                }
            }
            if (chatMap.get(targetMemberId).containsKey(0)){
                if (chatMap.get(targetMemberId).get(0).contains(sessionId)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void SaveDbChat(String type, int chatId,int senderId,int receiver, String text){ // 채팅타입에 따라 메시지를 분기 처리.
        if (type.equals("message")) {
            if (receiver != senderId) { chattingService.saveChattingData(chatId, text, senderId, receiver, 0); }
        } else if (type.equals("readMessage")) { chattingService.readMessage(senderId, chatId); }
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet) {
        this.numSet = numSet;
    }

    public void setChatMap(HashMap<Integer, HashMap<Integer, List<String>>> chatMap) {
        this.chatMap = chatMap;
    }
}
