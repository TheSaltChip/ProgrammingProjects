package queue;

import adt.KoeADT;
import exception.EmptyCollectionException;

public class SirkulaerKoe<T> implements KoeADT<T> {

    private final static int STDK = 100;
    private int foran, bak, antall;
    private T[] koe;

    public SirkulaerKoe() {
        this(STDK);
    }

    @SuppressWarnings("unchecked")
    public SirkulaerKoe(int startKapasitet) {
        foran = bak = antall = 0;
        koe = ((T[]) (new Object[startKapasitet]));
    }
    //...

    @Override
    public void innKoe(T el) {
        if (antall() == koe.length) {
            utvid();
        }

        koe[bak] = el;
        bak = (bak + 1) % koe.length;
        antall++;
    }

    @Override
    public T utKoe() {
        if (erTom())
            throw new EmptyCollectionException("Koe");

        T result = koe[foran];
        koe[foran] = null;

        foran = (foran + 1) % koe.length;
        antall--;

        return result;
    }

    @Override
    public T first() {
        if (erTom())
            throw new EmptyCollectionException("Koe");

        return koe[foran];
    }

    @Override
    public boolean erTom() {
        if (antall == 0)
            return true;
        else
            return false;
    }

    @Override
    public int antall() {
        return antall;
    }

    @SuppressWarnings("unchecked")
    private void utvid() {
        T[] tab = (T[]) (new Object[(int) Math.ceil(koe.length * 1.10)]);

        for (int i = 0; i < antall; i++) {
            tab[i] = koe[foran];
            foran = (foran + 1) % koe.length;
        }

        foran = 0;
        bak = antall;
        koe = tab;
    }
}// class

