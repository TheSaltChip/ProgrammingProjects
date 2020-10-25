package database.objects;

import javax.persistence.*;
import javax.xml.registry.infomodel.User;
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Info info;

    protected UserDB() {
    }

    public UserDB(String id, String username, String discriminator) {
        this.id = id;
        this.username = username;
        this.discriminator = discriminator;
    }

    public String getId() {
        return id;
    }

    public List<MessageDB> getMessages() {
        return messages;
    }

    public void updateMessages(List<MessageDB> messages) {
        this.messages.addAll(messages);
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof UserDB)) {
            return false;
        }

        UserDB u = (UserDB) obj;
        return this.id.equals(u.getId());
    }

    @Override
    public String toString() {
        return String.format("(id: %s, username: %s, discriminator: %s)", id, username, discriminator);
    }
}
