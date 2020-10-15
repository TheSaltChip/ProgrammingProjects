package database.objects;

import net.dv8tion.jda.api.entities.Message;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "guild")
public class MessageDB {

    private int message_id;
    private MemberDB author;
    private String msg_content;

    @Id
    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    public MemberDB getAuthor() {
        return author;
    }

    public void setAuthor(MemberDB author) {
        this.author = author;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    @Override
    public String toString(){
        return "\n{message_id:" + message_id + "\nauthor:" + author + "\nmessage_content:" + msg_content + "}\n";
    }
}
