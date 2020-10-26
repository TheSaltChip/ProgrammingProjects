package database.objects;

import database.objects.composite_keys.LetterId;

import javax.persistence.*;

@Entity
@Table(name = "letter_times", schema = "guild")
@IdClass(LetterId.class)
public class LetterAmount {
    @Id
    private Character letter;

    @Id
    private int times;

    @ManyToOne
    @JoinColumn(name = "collection", referencedColumnName = "user_id")
    private Info info;

    protected LetterAmount() {
    }

    public LetterAmount(char letter, int times) {
        this.letter = letter;
        this.times = times;
    }

    public Character getLetter() {
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
        return String.format("[letter: %c, times: %d", letter, times);
    }

    public void incTimes() {
        times++;
    }
}
