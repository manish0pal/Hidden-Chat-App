package in.manish.calculator;


public class Message {
    String sender,msg;

    public Message() {
    }

    public Message(String sender, String msg) {
        this.sender = sender;
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public String getMsg() {
        return msg;
    }
}
