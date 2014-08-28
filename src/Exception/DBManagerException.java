package Exception;

public class DBManagerException extends Exception {
    
    public DBManagerException(String message){
        super(message);
    }
    
    public DBManagerException(String message, Throwable throwable){
        super(message, throwable);
    }
    
}
