package database.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "letter", schema = "guild")
public class Letter {
    @Id
    private Character letter;

    protected Letter() {
    }

    public Letter(Character letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }

    protected void setLetter(Character letter) {
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Letter) {
            Letter l = (Letter) o;
            return this.letter == l.getLetter();
        }

        if (o instanceof Character) {
            Character c = (Character) o;
            return this.letter == c;
        }

        return false;
    }

    @Override
    public String toString() {
        return letter.toString();
    }
}
