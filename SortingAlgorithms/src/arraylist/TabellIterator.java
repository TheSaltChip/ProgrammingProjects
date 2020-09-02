package arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TabellIterator<T> implements Iterator<T> {
    // Klasse som kan brukes til � g� gjennom alle elementer
// i et objekt av klasse Mengde n�r denne klassen Mengde er
// implementert vha tabell.
//
    private int antall;  // antall elementer i mengden
    private int pos;    // posisjonen til aktuelt element
    private T[] elementer;


    public TabellIterator(T[] tab, int lengde) {
        // Gi startverdier til iteratoren
        elementer = tab;
        antall = lengde;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        // Tester om det er flere elementer igjen
        return (pos < antall);
    }

    @Override
    public T next() {
        // Returner med peker til neste aktuelle element
        if (!hasNext())
            throw new NoSuchElementException();
        pos++;
        return elementer[pos - 1];
    }

    @Override
    public void remove() {
        elementer[pos] = elementer[antall];
        elementer[antall] = null;
        antall--;
    }
}//class