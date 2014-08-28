package WriterImplementation;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import Model.DatabaseConfig;
import WriterInterface.GenericWriterInterface;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PHPGenericWriterImpl implements GenericWriterInterface {
   
    
    
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
    public void executeSqlQuery(String path, String query) {
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
                        "    $dbn->query($sqlQuery); \n" +
                        "} catch(PDOException $ex) {\n" +
                        "    echo \"An Error occured!\"; \n" +
                        "}";
        FileWriterImpl.getInstace().writeLine(phpCode, f);
    }

    @Override
    public void executeSqlQueryAndGetResultInVariable(String path, String query, String variableName) throws VariableManagerException{
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
        String phpCode= "$"+variableName+" = null;"+
                        "try {\n" +
                        "   $sqlQuery=\""+query+"\"; \n"+
                        "   $"+variableName+" = $dbn->query($sqlQuery); \n" +
                        "} catch(PDOException $ex) {\n" +
                        "    echo \"An Error occured!\"; \n" +
                        "}";        
        FileWriterImpl.getInstace().writeLine(phpCode, f);        
        VariableManagerImpl.getInstace().addVariable(variableName, path);
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
    public void writeArithmeticAndReturn(String Arithmetic) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void writeArithmeticAndGetResultInVariable(String Arithmetic) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void EOF(String Path) {
        FileWriterImpl writer =  FileWriterImpl.getInstace();
        File f ;
        try {
            f  = FileWriterImpl.getInstace().fileCreate(Path);
         } catch (IOException ex) {
             Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
        FileWriterImpl.getInstace().writeLine("?>", f);
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   
    
}
