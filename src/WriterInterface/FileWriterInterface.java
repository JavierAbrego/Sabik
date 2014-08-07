
package WriterInterface;

import java.io.File;
import java.io.IOException;

public interface FileWriterInterface {
    
    public boolean fileExists(String Path);
    public File fileOpen(String Path);
    public File fileCreate(String Path) throws IOException;
    public void writeLine(String line, File file);
    public void fileDelete(String Path);
    public void fileDelete(File file);
    
}
