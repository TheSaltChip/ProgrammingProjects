package exception;

//********************************************************************
//  EmptyCollectionException.java   //
//  Represents the scenario when the collection is empty
//********************************************************************

public class EmptyCollectionException extends RuntimeException {
    /******************************************************************
     * Setter opp et unntak med passende melding.
     ******************************************************************/
    public EmptyCollectionException(String samling) {
        super("" + samling + " er tom.");
    }
}
