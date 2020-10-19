package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "guild")
public class UserDB {
    @Id
    private String id;

    @OneToMany(mappedBy = "author")
    private List<MessageDB> messages;
    private String username;
    private String discriminator;
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private Info info;

    protected UserDB() {
    }

    public UserDB(String id, String username, String discriminator, String nickname) {
        this.id = id;
        this.username = username;
        this.discriminator = discriminator;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public List<MessageDB> getMessages() {
        return messages;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return ("(id:" + id + ";username:" + username + ";discriminator:" + discriminator + ";nickname:" + nickname + ")");
    }
}
