package saesigDiary.websocketnetty.websocket;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import saesigDiary.websocketnetty.chatting.ChatReadDto;
import saesigDiary.websocketnetty.chatting.ChattingService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    @Autowired
    private ChattingService chattingService;

    private HashMap<Integer, String[]> roomMap = new HashMap<Integer, String[]>();

    private HashMap<Integer, HashMap<Integer, String[]>> chatMap = new HashMap<Integer, HashMap<Integer, String[]>>();

    public void sendMessage(String payload) throws Exception {

        Gson gson = new Gson();

        chatJsonData param = gson.fromJson(payload, chatJsonData.class);
        TextMessage msg = new TextMessage(payload);

        int chatId = param.getChatId();
        int senderId = param.getMemberId();
        List<ChatReadDto> chatReadDtoList = chattingService.getChatMemberData(chatId);
        for (ChatReadDto receiver : chatReadDtoList) {
            String[] sessionChatArray = {};
            String[] sessionListArray = {};
            if (chatMap.containsKey(receiver.getMemberId())) {
                if (chatMap.get(receiver.getMemberId()).containsKey(chatId)) {
                    sessionChatArray = chatMap.get(receiver.getMemberId()).get(chatId);
                }
            }
            if (chatMap.containsKey(receiver.getMemberId())) {
                if (chatMap.get(receiver.getMemberId()).containsKey(0)) {
                    sessionListArray = chatMap.get(receiver.getMemberId()).get(0);
                }
            }
            for (WebSocketSession sess : numSet) {
                String sessionId = sess.getId();
                boolean isSend = false;
                if (sessionChatArray.length != 0) {
                    for (String sessionChatArrayItem : sessionChatArray) {
                        if (sessionChatArrayItem.equals(sessionId)) {
                            isSend = true;
                        }
                    }
                }
                if (sessionListArray.length != 0) {
                    for (String sessionListArrayItem : sessionListArray) {
                        if (sessionListArrayItem.equals(sessionId)) {
                            isSend = true;
                        }
                    }
                }
                if (isSend) {
                    sess.sendMessage(msg);
                }
            }

                if (param.getType().equals("message")) {
                    if (receiver.getMemberId() != senderId) {
                        chattingService.saveChattingData(chatId, param.getText(), senderId, receiver.getMemberId(), 0);
                    }
                } else if (param.getType().equals("readMessage")) {
                    chattingService.readMessage(senderId, chatId);
                }
        }
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet) {
        this.numSet = numSet;
    }

    public void setRoomMap(HashMap<Integer, String[]> roomMap) {
        this.roomMap = roomMap;
    }

    public void setChatMap(HashMap<Integer, HashMap<Integer, String[]>> chatMap) {
        this.chatMap = chatMap;
    }
}
