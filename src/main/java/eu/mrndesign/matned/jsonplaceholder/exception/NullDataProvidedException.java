package eu.mrndesign.matned.jsonplaceholder.exception;


public class NullDataProvidedException extends RuntimeException{

    private static final String MESSAGE = "NULL_DATA_PROVIDED";

    public NullDataProvidedException() {
        super(MESSAGE);
    }
}
