package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "info", schema = "guild")
public class Info {

    @Id
    @Column(name = "user_id")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDB user;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WordAmount> wordAmounts;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LetterAmount> letterAmounts;

    protected Info() {
    }

    public Info(UserDB user) {
        this.user = user;
        letterAmounts = null;
        wordAmounts = null;
    }

    public String getId() {
        return id;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }

    public List<WordAmount> getWords() {
        return wordAmounts;
    }

    public void setWords(List<WordAmount> wordAmounts) {
        this.wordAmounts = wordAmounts;
    }

    public List<LetterAmount> getLetters() {
        return letterAmounts;
    }

    public void setLetters(List<LetterAmount> letterAmounts) {
        this.letterAmounts = letterAmounts;
    }

    @Override
    public String toString() {
        return String.format("{user: %s, words: %s, letters: %s}", user, wordAmounts, letterAmounts);
    }
}
