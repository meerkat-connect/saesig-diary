package saesigDiary.websocketnetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sample")
@Getter
@Setter
@NoArgsConstructor
public class ChatDataDto {

    @Id
    private String id;
    private String chatId;
    private int sender_seq;
    private String text;
    private String reg_date;
}