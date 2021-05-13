package eu.mrndesign.matned.jsonplaceholder.exception;


public class NullEntityDataProvidedException extends RuntimeException{

    private static final String MESSAGE = "NULL_ENTITY_DATA_PROVIDED";

    public NullEntityDataProvidedException() {
        super(MESSAGE);
    }
}
