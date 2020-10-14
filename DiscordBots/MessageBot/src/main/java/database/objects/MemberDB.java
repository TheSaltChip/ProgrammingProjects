package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member", schema = "guild")
public class MemberDB {

    private int user_id;
    private List<MessageDB> messages;
    private String username;
    private String discriminator;
    private String nickname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @OneToMany(mappedBy = "author")
    public List<MessageDB> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDB> messages) {
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
