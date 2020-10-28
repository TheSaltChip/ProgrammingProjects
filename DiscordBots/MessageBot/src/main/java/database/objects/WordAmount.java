package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "word_amount", schema = "guild")
public class WordAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "user_id")
    private Info info;

    @ManyToOne()
    @JoinColumn(name = "word", referencedColumnName = "word")
    private Word word;

    private Integer amount;

    protected WordAmount() {
    }

    public WordAmount(Word word, Integer amount) {
        this.word = word;
        this.amount = amount;
    }

    public Word getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof WordAmount)) return false;

        WordAmount word1 = (WordAmount) o;

        return this.getWord().equals(word1.getWord());

    }

    @Override
    public String toString() {
        return String.format("[word: %s, times: %d]", word, amount);//"[word: " + word + ", times: " + times + "]";
    }
}
