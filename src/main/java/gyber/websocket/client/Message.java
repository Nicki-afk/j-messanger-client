package gyber.websocket.client;

public class Message {
    private String from;
    private String prefixTo; // через команду /username человек сможет отправить сообщение конкретному пользователю , если prefixTo не указан сообщение отправляется всем пользователям 
    private String content;




    public Message(){}



    public Message(String from, String to, String content) {
        this.from = from;
        this.prefixTo = to;
        this.content = content;
    }


    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return prefixTo;
    }
    public void setTo(String to) {
        this.prefixTo = to;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



}
