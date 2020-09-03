package arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private int amount;  // amount of elements in the iterator
    private int pos;    // The position of the current element
    private final T[] elements; //An array of all the elements


    public ArrayIterator(T[] array, int length) {
        elements = array;
        amount = length;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        return (pos < amount);
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();

        pos++;

        return elements[pos - 1];
    }

    @Override
    public void remove() {
        elements[pos] = elements[amount];
        elements[amount] = null;
        amount--;
    }
}