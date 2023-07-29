package gyber.websocket.client;

public class Message {
    private String from;
    private String prefixTo;
    private String prefixPrint;         // С помощью этого префикса можно указать стиль отрисовки сообщения на клиенте
    private String content;




    public Message(){}



    public Message(String from, String prefixTo, String content) {
        this.from = from;
        this.prefixTo = prefixTo;
        this.content = content;
    }

    


    public Message(String from, String prefixTo, String prefixPrint, String content) {
        this.from = from;
        this.prefixTo = prefixTo;
        this.prefixPrint = prefixPrint;
        this.content = content;
    }



    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getPrefixTo() {
        return prefixTo;
    }
    public void setPrefixTo(String prefixTo) {
        this.prefixTo = prefixTo;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



    public String getPrefixPrint() {
        return prefixPrint;
    }



    public void setPrefixPrint(String prefixPrint) {
        this.prefixPrint = prefixPrint;
    }

    



}
