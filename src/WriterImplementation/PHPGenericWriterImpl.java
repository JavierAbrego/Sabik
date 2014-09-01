package WriterImplementation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import Model.DatabaseConfig;
import Model.Variable;
import Model.VariableList;
import WriterInterface.GenericWriterInterface;

public class PHPGenericWriterImpl implements GenericWriterInterface {
   
	private static PHPGenericWriterImpl _genericWriterImpl;
	    
	private PHPGenericWriterImpl() {
	    if( _genericWriterImpl != null ) {
	        throw new InstantiationError( "More instances of this object cannot be created." );
	    }
	}
	
	private synchronized  static void createInstance(){
	    if(_genericWriterImpl==null){
	    	_genericWriterImpl  = new PHPGenericWriterImpl();
	    }
	}
	
	public static  PHPGenericWriterImpl getInstace(){
	    if(_genericWriterImpl==null) createInstance();
	    return _genericWriterImpl;
	}
    
    @Override
    public void header(String path) {
       FileWriterImpl.getInstace().fileDelete(path);
       File f ;
       try {
           f  = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        FileWriterImpl.getInstace().writeLine("<?php ", f);
    }

    @Override
    public void getInputVars(String path, List<String> input) throws VariableManagerException{
        printInputVars(path, input, "_GET");      
    }
    
    @Override
    public void postInputVars(String path, List<String> input) throws VariableManagerException{
        printInputVars(path, input, "_POST");      
    }        
    
    private void printInputVars(String path, List<String> input, String method) throws VariableManagerException{      
        File f ;
        try {
            f  = FileWriterImpl.getInstace().fileCreate(path);
         } catch (IOException ex) {
             Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
        FileWriterImpl.getInstace().writeLine("if("+method+"){",f );
        for(String var : input){
            String line = "\t$"+var+" = $"+method+"[\""+var+"\"];";
            FileWriterImpl.getInstace().writeLine(line, f);
            VariableManagerImpl.getInstace().addVariable(var, path);            
        }
       FileWriterImpl.getInstace().writeLine("}",f );
    }

    
    @Override
    public void addDatabase(String alias, String host, String port, String user, String passsword, String databaseName) throws DBManagerException{
        String filename = "config/db_"+alias+".php";
        header(filename);
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(filename);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        FileWriterImpl.getInstace().writeLine("$_db_config_host="+host, f);
        FileWriterImpl.getInstace().writeLine("$_db_config_port="+port, f);
        FileWriterImpl.getInstace().writeLine("$_db_config_user="+user, f);
        FileWriterImpl.getInstace().writeLine("$_db_config_password="+passsword, f);
        FileWriterImpl.getInstace().writeLine("$_db_config_databaseName="+databaseName, f);
        EOF(filename);
        
        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setAlias(alias);
        databaseConfig.setConfigFileName(filename);
        databaseConfig.setDatabaseName(databaseName);
        databaseConfig.setPassword(passsword);
        databaseConfig.setPort(port);
        databaseConfig.setURL(host);
        databaseConfig.setUser(user);        
        DBManagerImpl.getInstace().addDatabaseConfig(databaseConfig);
        
    }
    
    @Override
    public void useDatabase(String alias, String path) {
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        //get database config
        DatabaseConfig db = DBManagerImpl.getInstace().getDatabaseConfig(alias);
        //include of connection details
        FileWriterImpl.getInstace().writeLine("include '"+db.getConfigFileName()+"';", f);
        //PDO connection to database
        String phpCode = "/* Connect to an ODBC database using driver invocation */\n" +
                        "$_dsn = 'mysql:dbname=$_db_config_databaseName;host=$_db_config_host';\n" +
                        "\n" +
                        "try {\n" +
                        "    $dbh = new PDO($_dsn, $_db_config_user, $_db_config_password);\n" +
                        "} catch (PDOException $e) {\n" +
                        "    echo 'Connection failed: ' . $e->getMessage();\n" +
                        "}";
        FileWriterImpl.getInstace().writeLine(phpCode, f);
    }
    
    @Override
    public void beginTransaction(String path){
    	String phpCode="try {\n"+
    					"	$dbn->beginTransaction();\n";
    	try {
			FileWriterImpl.getInstace().writeLine(phpCode, FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {			
			e.printStackTrace();
		}
    }
    
    @Override
    public void endTransaction(String path){
    	String phpCode = " $dbn->commit();\n" +
    			"} catch(PDOException $ex) {\n" +
    			"    //Something went wrong rollback!\n" +
    			"    $dbn->rollBack();\n" +
    			"    echo $ex->getMessage();\n" +
    			"}";
		try {
			FileWriterImpl.getInstace().writeLine(phpCode, FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {			
			e.printStackTrace();
		}
    }
    
    /* (non-Javadoc)
     * example of query
     * $stmt = $db->prepare("UPDATE table SET name=? WHERE id=?");
     * $stmt->execute(array($name, $id));
     * $affected_rows = $stmt->rowCount();
     * @see WriterInterface.GenericWriterInterface#executeSqlQuery(java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public void executeSqlQuery(String path, String query, List<Variable> queryParameters){
         File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
        FileWriterImpl.getInstace().writeLine("$dbn->query($sqlQuery);", f);
        String phpCode= "try {\n" +
                        "$sqlQuery=\""+query+"\"; \n"+
                        "   $stmt = $dbn->prepare($sqlQuery); \n";
        boolean useArrayOfParameters = false;
        if(queryParameters!=null){
        	if(queryParameters.size()>0){
        		useArrayOfParameters= true;
        	}
        	
        }
        if(useArrayOfParameters){
        	phpCode+="$stmt->execute(array(";
        	int paramNumber = 0;
        	for (Variable variable : queryParameters) {
        		if(paramNumber!=0){
        			phpCode+= ", ";
        		}
        		phpCode+= "$"+variable.getName()+" ";
			}
        	phpCode+= "));\n";
        }else{
        	phpCode+="$stmt->execute();";
        }
        phpCode += "} catch(PDOException $ex) {\n" +
                        "    echo \"An Error occured!\"; \n" +
                        "}";
        FileWriterImpl.getInstace().writeLine(phpCode, f);
    }

    @Override
    public void executeSqlQueryAndGetResultInVariable(String path, String query, List<Variable> queryParameters, String variableName) throws VariableManagerException{
        executeSqlQuery(path, query, queryParameters);
        try {
			FileWriterImpl.getInstace().writeLine("$"+variableName+"= $stmt->fetchAll(PDO::FETCH_ASSOC);", FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {
			e.printStackTrace();
		}        
        VariableManagerImpl.getInstace().addVariable(variableName, path);    	    
    }
    
    @Override
    public void executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(String path, String query, List<Variable> queryParameters, String resultVariableName) throws VariableManagerException{
    	executeSqlQuery(path, query, queryParameters);
        try {
			FileWriterImpl.getInstace().writeLine("$"+resultVariableName+"= $stmt->rowCount();", FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {
			e.printStackTrace();
		}        
        VariableManagerImpl.getInstace().addVariable(resultVariableName, path);
    
    }
    
    @Override
    public void countResultRowsNumberAndGetResultInVariable(String path, String rowsVariableName, String countResultVariableName) throws VariableManagerException{
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
        String phpCode = "$"+countResultVariableName+" = $"+rowsVariableName+"->rowCount();";
        FileWriterImpl.getInstace().writeLine(phpCode, f);
        VariableManagerImpl.getInstace().addVariable(countResultVariableName, path);
    }
    
    @Override
    public void getLastInsertedIdIntoVariable(String path, String resultVariableName) throws VariableManagerException {
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String phpCode = "$"+resultVariableName+" = $dbn->lastInsertId();";
        FileWriterImpl.getInstace().writeLine(phpCode, f);
        VariableManagerImpl.getInstace().addVariable(resultVariableName, path);
    }

    
    @Override
    public void EOF(String path) {
        File f ;
        try {
            f  = FileWriterImpl.getInstace().fileCreate(path);
         } catch (IOException ex) {
             Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
        FileWriterImpl.getInstace().writeLine("?>", f);
    }

	@Override
	public void printVariableAsJSON(String path, String variableName) {
		String line="echo json_encode(\"$"+variableName+"\");";
		try {
			FileWriterImpl.getInstace().writeLine(line, FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printVariableAsXML(String path, String variableName) {
		String line="echo xmlrpc_encode(\"$"+variableName+"\");";
		try {
			FileWriterImpl.getInstace().writeLine(line, FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeArithmeticAndGetResultInVariable(String path, String arithmetic, VariableList variableList, String resultVariableName) {
		List<String> values =  new ArrayList<>(); 
		for (Variable var : variableList.getVars()) {
			values.add("$"+var.getName());
		}
		String arithmeticLine = String.format(arithmetic.replace("?", "%s"), values.toArray());
		String line = resultVariableName+"="+arithmeticLine+";";
		try {
			FileWriterImpl.getInstace().writeLine(line, FileWriterImpl.getInstace().fileCreate(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

   
    
}
