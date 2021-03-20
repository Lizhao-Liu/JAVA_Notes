package parse;

public class InvalidTokenException extends Exception{
    String errorMessage;
    public InvalidTokenException(String errorMessage){
        this.errorMessage=errorMessage;
    }
    public String toString(){
        return errorMessage;
    }
}
