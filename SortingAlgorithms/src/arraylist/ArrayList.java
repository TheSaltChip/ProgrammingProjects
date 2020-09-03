package arraylist;

import exception.EmptyCollectionException;
import java.util.Iterator;

/**
 * An arraylist class that makes it possible to store object in a list
 *
 * @param <T> The object type that is going to be stored
 */
public class ArrayList<T> {
    private final static int STANDARD_CAPACITY = 100; //The standard capacity of the array that is used to store the elements
    private int amount; //Keeps track of the amount of elements
    private T[] arr; //The array used to store the elements

    /**
     * Creates an ArrayList object with the standard capacity as starting capacity
     */
    public ArrayList() {
        this(STANDARD_CAPACITY);
    }

    /**
     * Creates and ArrayList object with the given capacity
     *
     * @param capacity The starting capacity of the list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        amount = 0;
        arr = (T[]) (new Object[capacity]);
    }

    /**
     * Returns the amount of elements in the list
     *
     * @return Amount of elements
     */
    public int amount() {
        return amount;
    }

    /**
     * Checks if the list is empty or not
     * Returns true if it is empty, false if not
     *
     * @return Boolean value for if the list is empty
     */
    public boolean isEmpty() {
        return (amount == 0);
    }

    /**
     * Adds the given element to the list
     *
     * @param element The element that is getting added to the list
     */
    public void add(T element) {
        if (!contains(element)) {
            if (amount == arr.length) {
                extendCapacity();
            }
            arr[amount] = element;
            amount++;
        }
    }

    /**
     * Extends the capacity of the array
     */
    @SuppressWarnings("unchecked")
    private void extendCapacity() {
        T[] tempArr = (T[]) (new Object[(int) (1.10 * arr.length)]);

        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        arr = tempArr;
    }

    /**
     * Tries to remove an given element from the list
     *
     * @param element The element that is going to be removed if it exists in the list
     * @return The element that is removed or null if not found
     * @exception EmptyCollectionException Gets thrown if the list is empty
     */
    public T remove(T element) {
        if (isEmpty())
            throw new EmptyCollectionException("quantity");

        T answer = null;

        if (contains(element)) {
            Iterator<T> teller = iterator();

            while (teller.hasNext()) {
                answer = teller.next();

                if (element.equals(answer)) {
                    teller.remove();
                    amount--;
                }
            }
        }

        return answer;
    }

    /**
     * Checks if the list contains an given element
     *
     * @param element The element that is to be checked
     * @return True if the element exist, false if not
     */
    public boolean contains(T element) {
        boolean found = false;

        for (int i = 0; i < amount; i++) {
            if (arr[i].equals(element)) {
                found = true;
                break;
            }
        }

        return (found);
    }

    /**
     * Checks if two lists have equal content
     *
     * @param m2 The other list
     * @return True if the two lists have the same content, false if not
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object m2) {
        boolean equalQuantities = true;

        Iterator<T> it = ((ArrayList<T>) m2).iterator();

        int amountM2 = 0;

        while (it.hasNext()) {
            amountM2++;

            if (!this.contains(it.next()))
                equalQuantities = false;
        }

        if (amountM2 != this.amount())
            equalQuantities = false;

        return equalQuantities;
    }

    /**
     * Creates an iterable object of the list
     *
     * @return An ArrayIterator of the list
     */
    public ArrayIterator<T> iterator() {
        return new ArrayIterator<>(arr, amount);
    }

    /**
     * Transforms the content of the list into a string
     *
     * @return String representation of the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator<T> it = this.iterator();

        int index = 0;
        T element;

        while (it.hasNext()) {
            element = it.next();

            if (!(index == amount - 1)) {
                builder.append(element.toString()).append(",");
            } else {
                builder.append(element.toString());
            }
            index++;
        }

        return builder.toString();
    }
}// class
