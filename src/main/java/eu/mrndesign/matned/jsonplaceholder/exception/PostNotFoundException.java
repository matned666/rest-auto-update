package eu.mrndesign.matned.jsonplaceholder.exception;


public class PostNotFoundException extends RuntimeException{

    private static final String MESSAGE = "POST_NOT_FOUND_EXCEPTION";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
