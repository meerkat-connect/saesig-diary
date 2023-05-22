package saesigDiary.websocketnetty.websocket;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashSet;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();
    private HashMap<String,String[]> roomMap = new HashMap<String, String[]>();
    public void sendMessage(String payload) throws Exception {

        Gson gson = new Gson();
        chatJsonData param = gson.fromJson(payload,chatJsonData.class);
        TextMessage msg = new TextMessage(param.getText());
        for(WebSocketSession sess: numSet) {
            String sessionId = sess.getId();
//            String url = sess.getUri().toString();
//            String chatId = url.split("=")[1];
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
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet){
        this.numSet = numSet;
    }

    public void setRoopMap(HashMap<String,String[]> roomMap){
        this.roomMap = roomMap;
    }
}
