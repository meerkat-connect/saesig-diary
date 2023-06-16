package saesigDiary.websocketnetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
public class ChatDataDto {
    private int chatId;
    private int senderId;
    private int receiverId;
    private String text;
    private String regDate;
    private int isRead;
}