package WriterImplementation;

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
       FileWriterImpl writer =  FileWriterImpl.getInstace();
       writer.fileDelete(Path);
       File f ;
       try {
           f  = writer.fileCreate(Path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
      writer.writeLine("<?php ", f);
      
        
    }

    @Override
    public void getInputVars(String Path, List<String> input) {
        FileWriterImpl writer =  FileWriterImpl.getInstace();
         File f ;
       try {
           f  = writer.fileCreate(Path);
        } catch (IOException ex) {
            Logger.getLogger(PHPGenericWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
       writer.writeLine("if($_GET){",f );
       for(String var : input){
           writer.writeLine("\t$"+var+" = $_GET[\""+var+"\"];", f);
           vars.addVariable(var);
       }
      writer.writeLine("}",f );
    }

    @Override
    public void useDatabase(String alias, String host, String port, String user, String passsword, String databaseName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void writeSqlQuery() {
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
    public void EOF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
