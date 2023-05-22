package saesigDiary.websocketnetty.websocket;

public class chatJsonData {
        private String text;
        private String chatId;

        public String getText() {
            return text;
        }
        public String getChatId() {
            return chatId;
        }
        @Override
        public String toString() {
            return "Member [text=" + text + ", name=" + chatId + "]";
        }
}