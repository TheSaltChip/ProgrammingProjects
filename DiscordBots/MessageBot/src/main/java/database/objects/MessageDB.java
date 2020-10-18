package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "guild")
public class MessageDB {
    @Id
    private String message_id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserDB author;
    private String msg_content;

    protected MessageDB() {
    }

    public MessageDB(String message_id, UserDB author, String msg_content) {
        this.message_id = message_id;
        this.author = author;
        this.msg_content = msg_content;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public UserDB getAuthor() {
        return author;
    }

    public void setAuthor(UserDB author) {
        this.author = author;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    @Override
    public String toString() {
        return "\n{message_id:" + message_id + "\nauthor:" + author + "\nmessage_content:" + msg_content + "}\n";
    }
}
