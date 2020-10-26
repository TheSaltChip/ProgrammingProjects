package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "letter_amount", schema = "guild")
public class LetterAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "user_id")
    private Info info;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "letter", referencedColumnName = "letter")
    private Letter letter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amount", referencedColumnName = "amount")
    private Amount amount;


    protected LetterAmount() {
    }


    public LetterAmount(Info info) {
        this.info = info;
    }

    public Letter getLetter() {
        return letter;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
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

        if (o instanceof Letter) {
            Letter l = (Letter) o;

            return this.getLetter().equals(l);
        }

        if (o instanceof Character) {
            Character c = (Character) o;
            return this.letter.getLetter() == c;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("[info: %s, letter: %s, times: %s]", info.getId(), letter, amount);
    }
}
