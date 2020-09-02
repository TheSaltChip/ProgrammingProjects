package exception;

/**
 * EmptyCollectionException.java
 * Represents the scenario when the collection is empty
 */
public class EmptyCollectionException extends RuntimeException {
    /**
     * Creates the exception
     *
     * @param collection Which collection throws the exception
     */
    public EmptyCollectionException(String collection) {
        super("" + collection + " is empty.");
    }
}
