package com.saesig.webSocketNetty.websocket;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.saesig.webSocketNetty.chatting.ChattingService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    @Autowired
    private ChattingService chattingService;

    private HashMap<Integer, String[]> roomMap = new HashMap<Integer, String[]>();

    public static HashMap<Long, HashMap<Long, List<String> >> chatMap = new HashMap<Long, HashMap<Long,List<String>>>();


    public void sendMessage(String payload) throws Exception {

        Gson gson = new Gson();

        chatJsonData param = gson.fromJson(payload, chatJsonData.class);
        ChatUserJsonData msgObj = gson.fromJson(payload, ChatUserJsonData.class);
        Long chatId = param.getChatId();
        Long senderId = param.getMemberId();
        Long receiver = param.getReceiverId();
        param.setSendTime(LocalDateTime.now());
        msgObj.setSendTime(LocalDateTime.now());
        TextMessage msg = new TextMessage(gson.toJson(msgObj));
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
        SaveDbChat(param.getType(), chatId, senderId, receiver,param.getText(),param.getSendTime());
    }

    public boolean isCheckSendSession(String sessionId, Long targetMemberId, Long chatId){ // 세션마다 채팅 보낼지 말지 여부 체크
        if (chatMap.containsKey(targetMemberId)){
            if (chatMap.get(targetMemberId).containsKey(chatId)){
                if (chatMap.get(targetMemberId).get(chatId).contains(sessionId)){
                    return true;
                }
            }
            if (chatMap.get(targetMemberId).containsKey(0L)){
                if (chatMap.get(targetMemberId).get(0L).contains(sessionId)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void SaveDbChat(String type, Long chatId,Long senderId,Long receiver, String text,  LocalDateTime sendTime){ // 채팅타입에 따라 메시지를 분기 처리.
        if (type.equals("message")) {
            if (receiver != senderId) { chattingService.saveChattingData(chatId, text, senderId, receiver, 0L, sendTime); }
        } else if (type.equals("readMessage")) { chattingService.readMessage(senderId, chatId); }
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet) {
        this.numSet = numSet;
    }

    public void setChatMap(HashMap<Long, HashMap<Long, List<String>>> chatMap) {
        this.chatMap = chatMap;
    }
}
