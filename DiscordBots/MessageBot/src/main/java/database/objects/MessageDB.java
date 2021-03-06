package database.objects;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message", schema = "guild")
public class MessageDB {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserDB author;

    @Column(name = "msg_content")
    private String msgContent;

    @Column(name = "time_created")
    private LocalDateTime dateCreated;

    protected MessageDB() {
    }

    public MessageDB(String id, UserDB author, String msg_content, LocalDateTime dateCreated) {
        this.id = id;
        this.author = author;
        this.msgContent = msg_content;
        this.dateCreated = dateCreated;
    }

    public String getMsg_content() {
        return msgContent;
    }

    public UserDB getAuthor() {
        return author;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, author: %s, messageContent: %s}", id, author, msgContent);//"\n{id:" + id + "\nauthor:" + author + "\nmessage_content:" + msgContent + "}\n";
    }
}
