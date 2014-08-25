package WriterImplementation;

import Models.DatabaseConfig;
import Models.VariableList;
import WriterInterface.GenericWriterInterface;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PHPGenericWriterImpl implements GenericWriterInterface {
   
    public VariableList vars =  new VariableList();
    
    @Override
    public void header(String Path) {
       FileWriterImpl.getInstace().fileDelete(Path);
       File f ;
       try {
           f  = FileWriterImpl.getInstace().fileCreate(Path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        FileWriterImpl.getInstace().writeLine("<?php ", f);
    }

    @Override
    public void getInputVars(String Path, List<String> input) {
        printInputVars(Path, input, "_GET");      
    }
    
    @Override
    public void postInputVars(String Path, List<String> input) {
        printInputVars(Path, input, "_POST");      
    }        
    
    private void printInputVars(String Path, List<String> input, String method) {      
        File f ;
        try {
            f  = FileWriterImpl.getInstace().fileCreate(Path);
         } catch (IOException ex) {
             Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
        FileWriterImpl.getInstace().writeLine("if("+method+"){",f );
        for(String var : input){
            String line = "\t$"+var+" = $"+method+"[\""+var+"\"];";
            FileWriterImpl.getInstace().writeLine(line, f);
            vars.addVariable(var);
        }
       FileWriterImpl.getInstace().writeLine("}",f );
    }

    
    @Override
    public void addDatabase(String alias, String host, String port, String user, String passsword, String databaseName) {
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
    public void useDatabase(String alias, String Path) {
        File f;
        try {
            f = FileWriterImpl.getInstace().fileCreate(Path);
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
    public void executeSqlQuery(String Path, String Query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeSqlQueryAndGetResultInVariable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeSqlQueryAndReturnBoolean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeSqlQueryAndReturnRows() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeArithmeticAndReturn(String Arithmetic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeArithmeticAndGetResultInVariable(String Arithmetic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
