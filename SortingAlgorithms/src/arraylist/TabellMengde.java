package arraylist;

import adt.MengdeADT;
import exception.EmptyCollectionException;

import java.util.Iterator;
import java.util.Random;

public class TabellMengde<T> implements MengdeADT<T> {
    // ADT-en Mengde implementert som tabell
    //
    private final static Random tilf = new Random();
    private final static int STDK = 100;
    private int antall;
    private T[] tab;

    public TabellMengde() {
        this(STDK);
    }

    @SuppressWarnings("unchecked")
    public TabellMengde(int start) {
        antall = 0;
        tab = (T[]) (new Object[start]);
    }

    public T[] getTab() {
        return tab;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean erTom() {
        return (antall == 0);
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            if (antall == tab.length) {
                utvidKapasitet();
            }
            tab[antall] = element;
            antall++;
        }
    }

    @SuppressWarnings("unchecked")
    private void utvidKapasitet() {
        T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
        for (int i = 0; i < tab.length; i++) {
            hjelpetabell[i] = tab[i];
        }
        tab = hjelpetabell;
    }

    @Override
    public T fjernTilfeldig() {
        if (erTom())
            throw new EmptyCollectionException("mengde");

        T svar = null;
        int indeks = tilf.nextInt(antall);
        svar = tab[indeks];
        tab[indeks] = tab[antall - 1];
        antall--;

        return svar;
    }

    /**
     * Fjerner alle forekomster av elementet
     */
    @Override
    public T fjern(T element) {
        // S�ker etter og fjerner element. Returnerer null-ref ved ikke-funn

        if (erTom())
            throw new EmptyCollectionException("mengde");

        T svar = null;

        if (inneholder(element)) {
            Iterator<T> teller = oppramser();

            while (teller.hasNext()) {
                svar = teller.next();
                if (element.equals(svar)) {
                    teller.remove();
                    antall--;
                }
            }
        }

        return svar;
    }

    @Override
    public boolean inneholder(T element) {
        boolean funnet = false;
        for (int i = 0; (i < antall) && (!funnet); i++) {
            if (tab[i].equals(element)) {
                funnet = true;
            }
        }
        return (funnet);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object m2) {
        boolean likeMengder = true;

        Iterator<T> it = ((TabellMengde<T>) m2).oppramser();

        int antallm2 = 0;

        while (it.hasNext()) {
            antallm2++;

            if (!this.inneholder(it.next()))
                likeMengder = false;
        }

        if (antallm2 != this.antall())
            likeMengder = false;

        return likeMengder;
    }

    @Override
    public void leggTilAlle(MengdeADT<T> m2) {
        Iterator<T> teller = m2.oppramser();
        while (teller.hasNext())
            leggTil(teller.next());
    }

    /*
     * Denne versjonen av unionen er lite effekktiv
     *
     * @Override public MengdeADT<T> union(MengdeADT<T> m2) {
     *
     * TabellMengde<T> begge = new TabellMengde<T>();
     *
     * for (int i = 0; i < amount; i++) {
     * 	begge.leggTil(tab[i]);
     * }
     *
     * Iterator<T> teller = m2.oppramser();
     *
     * while (teller.hasNext()) {
     * 	begge.leggTil(teller.next());
     * }
     *
     * return
     * (MengdeADT<T>)begge; }
     */
    @Override

    public MengdeADT<T> union(MengdeADT<T> m2) {
        MengdeADT<T> begge = new TabellMengde<T>();
        T element;

        begge = this;

        Iterator<T> it = m2.oppramser();

        while (it.hasNext()) {
            element = it.next();
            begge.leggTil(element);
        }

        return begge;
    }//

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> m2) {
        MengdeADT<T> snittM = new TabellMengde<T>();
        T element;

        Iterator<T> it = m2.oppramser();

        while (it.hasNext()) {
            element = it.next();
            if (this.inneholder(element)) {
                snittM.leggTil(element);
            }
        }

        return snittM;
    }

    @Override
    public MengdeADT<T> differens(MengdeADT<T> m2) {
        MengdeADT<T> differensM = new TabellMengde<T>();
        T element;

        Iterator<T> it = m2.oppramser();

        while (it.hasNext()) {
            element = it.next();
            if (!this.inneholder(element)) {
                differensM.leggTil(element);
            }
        }

        Iterator<T> tIt = this.oppramser();

        while (tIt.hasNext()) {
            element = tIt.next();
            if (!m2.inneholder(element))
                differensM.leggTil(element);
        }

        return differensM;
    }

    @Override
    public boolean undermengde(MengdeADT<T> m2) {
        boolean erUnderMengde = true;

        Iterator<T> it = m2.oppramser();

        while (it.hasNext()) {
            if (!this.inneholder(it.next())) {
                erUnderMengde = false;
            }
        }

        return erUnderMengde;
    }

    @Override
    public Iterator<T> oppramser() {
        return new TabellIterator<T>(tab, antall);
    }

	/*private void settInn(T element) {
		if (amount == tab.length) {
			utvidKapasitet();
		}
		tab[amount] = element;
		amount++;
	}*/

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();
        Iterator<T> it = this.oppramser();
        int index = 0;
        T element;

        while (it.hasNext()) {
            element = it.next();
            if (!(index == antall - 1)) {
                resultat.append(element.toString()).append(",");
            } else {
                resultat.append(element.toString());
            }
            index++;
        }

        return resultat.toString();
    }
}// class






























