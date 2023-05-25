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
public class ChattingRoomDto {

    @Id
    private int id;
    private String title;
    private String chat_id;
    private String created_at;
    private String last_msg;
}