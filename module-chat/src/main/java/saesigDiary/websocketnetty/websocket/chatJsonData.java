package saesigDiary.websocketnetty.websocket;

public class chatJsonData {
    private String text;
        private int chatId;

        private int memberId;

        public String getText() {
            return text;
        }
        public int getChatId() {
            return chatId;
        }

        public int getMemberId(){return memberId;}
        @Override
        public String toString() {
            return "Member [text=" + text + ", name=" + chatId + "]";
        }
}