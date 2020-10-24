package database.objects;

import javax.persistence.*;

@Entity
@Table(name = "letter", schema = "guild")
public class Letter {
    @Id
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

    public void setLetter(Character letter) {
        this.letter = letter;
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

        if (o instanceof Letter){

            Letter l = (Letter) o;

            return l.getLetter() == this.letter;
        }

        if(o instanceof Character){
          Character c = (Character) o;

          return this.getLetter() == c;
        }

        return false;


    }
}