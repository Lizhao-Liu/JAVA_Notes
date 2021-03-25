package exception;

public class CommandExecutionException extends DBException{
    public CommandExecutionException(String errorMessage) {
        super(errorMessage);
    }
}
