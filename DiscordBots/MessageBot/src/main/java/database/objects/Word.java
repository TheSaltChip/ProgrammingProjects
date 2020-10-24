package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "word", schema = "guild")
public class Word {
    @Id
    private String word;
    private int times;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private Info info;

    protected Word() {
    }

    public Word(String word, int times) {
        this.word = word;
        this.times = times;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Word)) return false;

        Word word1 = (Word) o;

        return this.getWord().equals(word1.getWord());

    }
}
