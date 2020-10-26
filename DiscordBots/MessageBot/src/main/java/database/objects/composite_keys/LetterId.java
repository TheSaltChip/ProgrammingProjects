package database.objects.composite_keys;

import javax.persistence.JoinColumn;
import java.io.Serializable;

public class LetterId implements Serializable {

    @JoinColumn(name = "letter", referencedColumnName = "letter")
    private Character letter;

    @JoinColumn(name = "times", referencedColumnName = "times")
    private Integer times;

    public LetterId(char letter, int times) {
        this.letter = letter;
        this.times = times;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
