package restfy;

import WriterImplementation.PHPGenericWriterImpl;
import java.util.ArrayList;
import java.util.List;

public class RESTfy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PHPGenericWriterImpl w = new PHPGenericWriterImpl();
        String fileName = "test";
        w.header(fileName);
        List<String> vars =  new ArrayList<>();
        vars.add("var1");
        vars.add("var2");
        w.getInputVars(fileName, vars);
    }
    
}
