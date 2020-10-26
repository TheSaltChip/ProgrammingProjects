package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "info", schema = "guild")
public class Info {

    @Id
    @OneToOne(mappedBy = "info")
    @Column(name = "user_id")
    private UserDB user;

    @OneToMany(mappedBy = "info")
    private List<WordAmount> words;

    @OneToMany(mappedBy = "info")
    private List<LetterAmount> letterTimes;

    protected Info() {
    }

    public Info(UserDB user, List<WordAmount> words, List<LetterAmount> letterTimes) {
        this.user = user;
        this.words = words;
        this.letterTimes = letterTimes;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }

    public List<WordAmount> getWords() {
        return words;
    }

    public void setWords(List<WordAmount> words) {
        this.words = words;
    }

    public List<LetterAmount> getLetters() {
        return letterTimes;
    }

    public void setLetters(List<LetterAmount> letterTimes) {
        this.letterTimes = letterTimes;
    }

    @Override
    public String toString() {
        return String.format("{user: %s, words: %s, letters: %s}", user, words, letterTimes);
    }
}
