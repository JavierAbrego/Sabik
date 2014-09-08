package Exception;

public class DBManagerException extends Exception {
	private static final long serialVersionUID = 5323215832303561172L;

	public DBManagerException(String message){
        super(message);
    }
    
    public DBManagerException(String message, Throwable throwable){
        super(message, throwable);
    }
    
}
