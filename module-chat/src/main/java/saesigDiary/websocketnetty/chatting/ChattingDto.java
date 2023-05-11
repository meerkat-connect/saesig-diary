package saesigDiary.websocketnetty.chatting;

import lombok.Data;

@Data
public class ChattingDto {

        private Long ID;
        private String EMAIL;
        private String PASSWORD;
        private String NICKNAME;
}
