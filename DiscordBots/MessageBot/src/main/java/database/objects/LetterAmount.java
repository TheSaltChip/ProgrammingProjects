package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "letter_amount", schema = "guild")
public class LetterAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "user_id")
    private Info info;

    @ManyToOne
    @JoinColumn(name = "letter", referencedColumnName = "letter")
    private Letter letter;

    private Integer amount;


    protected LetterAmount() {
    }

    public LetterAmount(Info info) {
        this.info = info;
    }

    public LetterAmount(Info info, Letter letter, Integer amount) {
        this.info = info;
        this.letter = letter;
        this.amount = amount;
    }

    public Letter getLetter() {
        return letter;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public Info getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof LetterAmount) {

            LetterAmount l = (LetterAmount) o;

            return this.getLetter().equals(l.getLetter());
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("[id: %d, info: %s, letter: %s, times: %d]", id, info.getId(), letter, amount);
    }
}
