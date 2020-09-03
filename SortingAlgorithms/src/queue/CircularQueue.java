package queue;

import exception.EmptyCollectionException;

/**
 * A circular queue class
 *
 * @param <T> The class type that is to be stored in the queue
 */
public class CircularQueue<T> {

    private final static int STANDARD_CAPACITY = 100; // Standard capacity for the queue
    private int front, behind, amount; // The front of the queue, the behind of the queue
    private T[] queue; // The array of which the queue is made off

    /**
     * Makes a circular queue with the size of 100
     */
    public CircularQueue() {
        this(STANDARD_CAPACITY);
    }

    /**
     * Makes a circular queue with the given size
     *
     * @param startCapacity The starting length of the circular queue
     */
    @SuppressWarnings("unchecked")
    public CircularQueue(int startCapacity) {
        front = behind = amount = 0;
        queue = ((T[]) (new Object[startCapacity]));
    }

    /**
     * Adds an element to the queue
     *
     * @param el The element that is added to the queue
     */
    public void enqueue(T el) {
        if (amount() == queue.length) {
            extend();
        }

        queue[behind] = el;
        behind = (behind + 1) % queue.length;
        amount++;
    }

    /**
     * Returns the next element in the queue
     *
     * @return The element that is first in queue
     */
    public T dequeue() {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        T result = queue[front];
        queue[front] = null;

        front = (front + 1) % queue.length;
        amount--;

        return result;
    }

    /**
     * Checks if the queue is empty
     * Returns true if it is empty
     * False if not
     *
     * @return true if the queue is empty, false if it is not
     */
    public boolean isEmpty() {
        return amount == 0;
    }

    /**
     * Returns the amount of elements in the queue
     *
     * @return The amount of elements in the queue
     */
    public int amount() {
        return amount;
    }

    /**
     * Extends the queue by 10% of its size
     */
    @SuppressWarnings("unchecked")
    private void extend() {
        T[] array = (T[]) (new Object[(int) Math.ceil(queue.length * 1.10)]);

        for (int i = 0; i < amount; i++) {
            array[i] = queue[front];
            front = (front + 1) % queue.length;
        }

        front = 0;
        behind = amount;
        queue = array;
    }
}// class

