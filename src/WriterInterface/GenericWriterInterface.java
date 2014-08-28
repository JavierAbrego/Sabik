package WriterInterface;

import Exception.DBManagerException;
import java.util.List;
import Exception.VariableManagerException;

public interface GenericWriterInterface {
    
    public void header(String path);
    public void getInputVars(String path, List<String> input) throws VariableManagerException;
    public void postInputVars(String path, List<String> input) throws VariableManagerException;
    public void useDatabase(String alias, String path);
    public void addDatabase(String alias, String host, String port, String user, String passsword, String databaseName) throws DBManagerException;
    public void executeSqlQuery(String Path, String Query);
    public void executeSqlQueryAndGetResultInVariable(String path, String query, String variableName) throws VariableManagerException;   
    public void countResultRowsNumberAndGetResultInVariable(String path, String rowsVariableName, String resultVariableName) throws VariableManagerException;
    public void writeArithmeticAndReturn(String Arithmetic);
    public void writeArithmeticAndGetResultInVariable(String Arithmetic);
    public void EOF(String path);
    
}
