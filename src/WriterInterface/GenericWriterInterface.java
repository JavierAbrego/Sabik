package WriterInterface;

import Exception.DBManagerException;

import java.util.List;

import Exception.VariableManagerException;
import Model.Variable;
import Model.VariableList;

/**
 * @author abregoj
 *
 */
public interface GenericWriterInterface {
    
    /**
     * @param path
     */
    public void header(String path);
   
    /**
     * @param path
     * @param input
     * @throws VariableManagerException
     */
    public void getInputVars(String path, List<String> input) throws VariableManagerException;
   
    /**
     * @param path
     * @param input
     * @throws VariableManagerException
     */
    public void postInputVars(String path, List<String> input) throws VariableManagerException;
   
    /**
     * @param alias
     * @param path
     */
    public void useDatabase(String alias, String path);
   
    /**
     * @param alias
     * @param host
     * @param port
     * @param user
     * @param passsword
     * @param databaseName
     * @throws DBManagerException
     */
    public void addDatabase(String alias, String host, String port, String user, String passsword, String databaseName) throws DBManagerException;
    
    /**
     * @param path
     */
    public void beginTransaction(String path);
   
    /**
     * @param path
     */
    public void endTransaction(String path);
    
    /**
     * Writes in the specified file the code that corresponds to the execution of an SQL query
     * @param path //the path to the file where the code is going to be appended
     * @param query //query with ? as variable 
     * @param queryParameters //replace ? in query with variables
     */
    public void executeSqlQuery(String path, String query, List<Variable> queryParameters);
   
    
    /**
     * @param path
     * @param query
     * @param queryParameters
     * @param variableName
     * @throws VariableManagerException
     */
    public void executeSqlQueryAndGetResultInVariable(String path, String query, List<Variable> queryParameters, String variableName) throws VariableManagerException;   
  
    /**
     * @param path
     * @param rowsVariableName
     * @param resultVariableName
     * @throws VariableManagerException
     */
    public void countResultRowsNumberAndGetResultInVariable(String path, String rowsVariableName, String resultVariableName) throws VariableManagerException;
  
    /**
     * @param path
     * @param query
     * @param resultVariableName
     * @throws VariableManagerException
     */
    public void executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(String path, String query, List<Variable> queryParameters, String resultVariableName) throws VariableManagerException;
   
    /**
     * @param path
     * @param resultVariableName
     * @throws VariableManagerException
     */
    public void getLastInsertedIdIntoVariable(String path, String resultVariableName) throws VariableManagerException;
   
    /**
     * Arithmetic example: (?+?)*?
     * @param path
     * @param arithmetic
     * @param variableList
     */
    public void writeArithmeticAndGetResultInVariable(String path, String arithmetic, VariableList variableList);
    
    /**
     * @param path
     * @param variableName
     */
    public void printVariableAsJSON(String path, String variableName);
    
    /**
     * @param path
     * @param variableName
     */
    public void printVariableAsXML(String path, String variableName);
    
    /**
     * @param path
     */
    public void EOF(String path);
    
}
