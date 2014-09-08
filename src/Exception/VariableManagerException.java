package Exception;

public class VariableManagerException extends Exception {
	private static final long serialVersionUID = 3331253829824368680L;

	public VariableManagerException(String message){
        super(message);
    }
    
    public VariableManagerException(String message, Throwable throwable){
        super(message, throwable);
    }
    
}
