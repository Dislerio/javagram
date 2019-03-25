package diplomWork.presenter.objects;

import org.javagram.response.object.Message;

public class MyMessage {
    private int id;
    private int fromId;
    private int toId;
    private boolean out;
    private boolean unread;
    private int date;
    private String message;

    public MyMessage(Message message) {
            this.id = message.getId();
            this.fromId = message.getFromId();
            this.toId = message.getToId();
            this.out = message.isOut();
            this.unread = message.isUnread();
            this.date = message.getDate();
            this.message = message.getMessage();
    }

    public int getId() {
        return this.id;
    }

    public int getFromId() {
        return this.fromId;
    }

    public int getToId() {
        return this.toId;
    }

    public boolean isOut() {
        return this.out;
    }

    public boolean isUnread() {
        return this.unread;
    }

    public int getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }
}