package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "info", schema = "guild")
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    private UserDB user;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL)
    private List<Word> words;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL)
    private List<Letter> letters;

    protected Info() {
    }

    public Info(UserDB user, List<Word> words, List<Letter> letters) {
        this.user = user;
        this.words = words;
        this.letters = letters;
    }

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

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, user: %s, words: %s, letters: %s}", id, user, words, letters);
    }
}
