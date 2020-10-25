package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "letter", schema = "guild")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Character letter;
    private int times;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private Info info;

    protected Letter() {
    }

    public Letter(char letter, int times) {
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

        if (o instanceof Letter){

            Letter l = (Letter) o;

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
