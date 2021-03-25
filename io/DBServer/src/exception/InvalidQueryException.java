package exception;

public class InvalidQueryException extends DBException{
    String errorMessage;

    public InvalidQueryException(String errorMessage) {
        super(errorMessage);
    }
}
