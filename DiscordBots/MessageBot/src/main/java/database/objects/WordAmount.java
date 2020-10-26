package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "word_amount", schema = "guild")
public class WordAmount {

    @Id
    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "user_id")
    private Info info;

    @ManyToOne
    @JoinColumn(name = "word", referencedColumnName = "word")
    private Word word;

    @ManyToOne
    @JoinColumn(name = "amount", referencedColumnName = "amount")
    private Amount amount;


    protected WordAmount() {
    }

    public WordAmount(Word word, Amount amount) {
        this.word = word;
        this.amount = amount;
    }

    public Word getWord() {
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
    public String toString() {
        return String.format("[word: %s, times: %s]", word, amount);//"[word: " + word + ", times: " + times + "]";
    }
}
