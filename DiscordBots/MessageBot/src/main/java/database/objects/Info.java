package database.objects;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "info", schema = "guild")
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "info")
    private UserDB user;

    @ElementCollection
    @CollectionTable(name = "word", joinColumns = @JoinColumn(name = "word"))
    @MapKeyColumn(name = "word", length = 50)
    @Column(name = "times")
    private Map<String, Integer> words;

    @ElementCollection
    @CollectionTable(name = "letter", joinColumns = @JoinColumn(name = "letter"))
    @MapKeyColumn(name = "letter")
    @Column(name = "times")
    private Map<Character, Integer> letters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public void setWords(Map<String, Integer> words) {
        this.words = words;
    }

    public Map<Character, Integer> getLetters() {
        return letters;
    }

    public void setLetters(Map<Character, Integer> letters) {
        this.letters = letters;
    }
}
