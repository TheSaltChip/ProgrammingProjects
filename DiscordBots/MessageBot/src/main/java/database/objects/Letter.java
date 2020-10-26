package database.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "letter", schema = "guild")
public class Letter {
    @Id
    private Character letter;

    protected Letter(){
    }

    public Letter(Character letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    @Override
    public String toString(){
        return letter.toString();
    }
}
