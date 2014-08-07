package WriterInterface;

import java.util.List;


public interface GenericWriterInterface {
    
    public void header(String Path);
    public void getInputVars(String Path, List<String> input);
    public void useDatabase(String alias, String host, String port, String user, String passsword, String databaseName);
    public void writeSqlQuery();
    public void writeSqlQueryAndGetResultInVariable();
    public void writeSqlQueryAndReturnBoolean();
    public void writeSqlQueryAndReturnRows();
    public void writeArithmeticAndReturn(String Arithmetic);
    public void writeArithmeticAndGetResultInVariable(String Arithmetic);
    public void EOF();
    
}
