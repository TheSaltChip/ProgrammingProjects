package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "word", schema = "guild")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Word)) return false;

        Word word1 = (Word) o;

        return this.getWord().equals(word1.getWord());

    }

    @Override
    public String toString(){
        return String.format("[word: %s, times: %d]", word, times);//"[word: " + word + ", times: " + times + "]";
    }

    public void incTimes() {
        times++;
    }
}
