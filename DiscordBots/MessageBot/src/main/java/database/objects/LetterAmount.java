package database.objects;

import database.objects.composite_keys.LetterId;

import javax.persistence.*;

@Entity
@Table(name = "letter_amount", schema = "guild")
@IdClass(LetterId.class)
public class LetterAmount {
    @Id
    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "user_id")
    private Info info;

    @ManyToOne
    @JoinColumn(name = "letter", referencedColumnName = "letter")
    private Letter letter;

    @ManyToOne
    @JoinColumn(name = "amount", referencedColumnName = "amount")
    private Amount amount;


    protected LetterAmount() {
    }

    public LetterAmount(Letter letter, Amount amount) {
        this.letter = letter;
        this.amount = amount;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof LetterAmount){

            LetterAmount l = (LetterAmount) o;

            return l.getLetter() == this.letter;
        }

        return false;
    }

    @Override
    public String toString(){
        return String.format("[letter: %s, times: %s", letter, amount);
    }
}
