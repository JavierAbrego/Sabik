package WriterImplementation;

import WriterInterface.FileWriterInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileWriterImpl implements FileWriterInterface{

    private static FileWriterImpl _fileWriter;
    
    private synchronized  static void createInstance(){
        if(_fileWriter==null){
            _fileWriter  = new FileWriterImpl();
        }
    }
    
    public static  FileWriterImpl getInstace(){
        if(_fileWriter==null) createInstance();
        return _fileWriter;
    }
    
    @Override
    public boolean fileExists(String Path) {
        File file = new File(Path);
        return file.exists();
    }

    @Override
    public File fileOpen(String Path) {
         return  new File(Path);
    }

    @Override
    public File fileCreate(String Path) throws IOException{
        File file = new File(Path);
        file.createNewFile();
        return file;
    }

    @Override
    public void writeLine(String line, File file) {
         BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(line+"\n");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileWriterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void fileDelete(String Path) {
         File file = new File(Path);
         file.delete();        
    }

    @Override
    public void fileDelete(File file) {
        file.delete();
    }

    
}
