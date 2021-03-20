package exception;

public class ParseCommandException extends Exception{
    String errorMessage;

    public ParseCommandException(String err){
        errorMessage = err;
    }

    public String toString(){
        return "[ERROR] "+ errorMessage;
    }
}
