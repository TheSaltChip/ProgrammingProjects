package adt;

import exception.EmptyCollectionException;

import java.util.Iterator;

public interface MengdeADT<T> {
    /* De abstrakte  metodene under svarer til operasjoner i en ADT for en mengde */

    /**
     * Legger til et element til dette mengde-objektet (this) hvis det ikke fins fra
     * f�r
     *
     * @param element som skal legges til betinget
     */
    void leggTil(T element);

    /**
     * @param m2 er mengden som skal legges til denne mengden Legger til de
     *           elementer i m2 som ikke fins fra f�r i denne mengden m1 (this).
     */
    void leggTilAlle(MengdeADT<T> m2);

    /**
     * Fjerner og returnerer et tilfeldig element fra mengden
     *
     * @return returnerer det tilfeldige elementet ellers returneres null
     * @throws EmptyCollectionException unntak kastes hvis mengden allerede er
     *                                  tom
     */
    T fjernTilfeldig();

    /**
     * @param element er elementet som skal fjernes hvis det fins ellers returneres
     *                null
     * @return elementet som fjernes
     * @throws EmptyCollectionException unntak kastes hvis mengden allerede er
     *                                  tom
     */
    T fjern(T element);


    boolean inneholder(T element);

    /**
     * Tester om this-mengden og parameteren inneholder n�yaktig de samme elementene
     *
     * @param m2 er mengden som testes
     * @return sann hvis m1 og m2  er like ellers usann
     */
    boolean equals(Object m2);

    /**
     * Tester om mengden er tom (dvs. har 0 element)
     *
     * @return sann hvis mendgen er tom ellers usann
     */
    boolean erTom();

    /**
     * Finner antall elementer i mengden
     *
     * @return antall i mengden
     */
    int antall();

    /**
     * @param m2 er mengden det skal lages union med.
     * @return er union av mengden m2 og m1(this)-mengden
     */
    MengdeADT<T> union(MengdeADT<T> m2);

    /**
     *
     * @param element er det gitte elementet
     * @return sann hvis elementet fins ellers usann
     */

    /**
     * Lager en ny mengde som er snittet av mengden m1 og m2
     *
     * @param m2 er parameter
     * @return snittet av de to mengdene
     */
    MengdeADT<T> snitt(MengdeADT<T> m2);

    /**
     * Lager en ny mengde som er differensen av m1 og m2.
     *
     * @param m2 er parameteren
     * @return differensen av de to mengdene
     */
    MengdeADT<T> differens(MengdeADT<T> m2);

    /**
     * @param m2 er parameteren
     * @return sann hvis m2 er en undermengde av m1, ellers usann
     */
    public boolean undermengde(MengdeADT<T> m2);

    /**
     * Oppretter et 'oppramsobjekt' som kan brukes til � gjennomg� alle elementer i
     * mengden etter tur en gang
     *
     * @return et oppramsobjekt
     */
    Iterator<T> oppramser();

    /**
     * Omgj�r listen av hobbyer til en tekststreng
     *
     * @return string av hobbyene
     */
    String toString();
}
