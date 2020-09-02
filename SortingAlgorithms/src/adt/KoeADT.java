package adt;

public interface KoeADT<T> {

    void innKoe(T el);

    T utKoe();

    T first();

    boolean erTom();

    int antall();
}
