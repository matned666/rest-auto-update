package eu.mrndesign.matned.jsonplaceholder.exception;


public class NullDataProvidedException extends RuntimeException{

    private static final String NULL_DATA_PROVIDED = "NULL_DATA_PROVIDED";

    public NullDataProvidedException() {
        super(NULL_DATA_PROVIDED);
    }
}
