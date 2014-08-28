
package WriterInterface;
import Exception.VariableManagerException;

public interface VariableManagerInterface {
    
    public boolean addVariable(String variableName, String path) throws VariableManagerException;
    public void deleteVariable(String variableName, String path);
    public boolean existVariable(String variableName, String path);
    
}
