package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "guild")
public class UserDB {
    @Id
    private String user_id;

    @OneToMany(mappedBy = "author")
    private List<MessageDB> messages;
    private String username;
    private String discriminator;
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private Info info;

    protected UserDB () {}

    public UserDB(String user_id, String username, String discriminator, String nickname) {
        this.user_id = user_id;
        this.username = username;
        this.discriminator = discriminator;
        this.nickname = nickname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return ("(user_id:" + user_id + ";username:" + username + ";discriminator:" + discriminator + ";nickname:" + nickname + ")");
    }


}
