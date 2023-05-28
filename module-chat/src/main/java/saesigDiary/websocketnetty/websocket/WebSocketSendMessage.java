package saesigDiary.websocketnetty.websocket;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import saesigDiary.websocketnetty.chatting.ChattingService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashSet;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    @Autowired
    private ChattingService chattingService;

    private HashMap<Integer,String[]> roomMap = new HashMap<Integer, String[]>();
    public void sendMessage(String payload) throws Exception {

        Gson gson = new Gson();
        chatJsonData param = gson.fromJson(payload,chatJsonData.class);

        TextMessage msg = new TextMessage(payload);
        for(WebSocketSession sess: numSet) {
            String sessionId = sess.getId();
            param.getChatId();
            boolean isSend = false;
            for (int i=0; i<roomMap.get(param.getChatId()).length; i++){
                if (roomMap.get(param.getChatId())[i].equals(sessionId)){
                    isSend = true;
                }
            }
            if (isSend){
                sess.sendMessage(msg);
            }
        }
        if (param.getType().equals("message")){
            chattingService.saveChattingData(param.getChatId(),param.getText(), param.getMemberId(), 0);
        }
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet){
        this.numSet = numSet;
    }

    public void setRoopMap(HashMap<Integer,String[]> roomMap){
        this.roomMap = roomMap;
    }
}
