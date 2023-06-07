package saesigDiary.websocketnetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

public class ChatReadDto {
    private int chatId;
    private int memberId;

    public int getChatId() {
        return chatId;
    }

    public int getMemberId(){return memberId;}
}