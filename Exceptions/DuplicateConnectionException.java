package Exceptions;

public class DuplicateConnectionException extends Exception {
    private String message;
    
    public DuplicateConnectionException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
