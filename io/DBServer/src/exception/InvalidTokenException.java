package exception;

public class InvalidTokenException extends InvalidQueryException{
    String errorMessage;
    public InvalidTokenException(String errorMessage){
        super(errorMessage);
    }
}
