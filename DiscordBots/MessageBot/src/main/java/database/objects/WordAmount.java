package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "word_times", schema = "guild")
public class WordAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String word;
    private int times;

    @ManyToOne
    @JoinColumn(name = "collection", referencedColumnName = "user_id")
    private Info info;

    protected WordAmount() {
    }

    public WordAmount(String word, int times) {
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

        if (!(o instanceof WordAmount)) return false;

        WordAmount word1 = (WordAmount) o;

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
