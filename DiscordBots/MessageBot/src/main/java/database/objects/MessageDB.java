package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "guild")
public class MessageDB {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserDB author;
    private String msg_content;

    protected MessageDB() {
    }

    public MessageDB(String id, UserDB author, String msg_content) {
        this.id = id;
        this.author = author;
        this.msg_content = msg_content;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public UserDB getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "\n{id:" + id + "\nauthor:" + author + "\nmessage_content:" + msg_content + "}\n";
    }
}
