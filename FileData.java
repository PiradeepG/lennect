import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class FileData implements Serializable {
    private String fileName;
    private byte[] fileContent;

    public FileData(String fileName,byte[] fileContent){
        this.fileName=fileName;
        this.fileContent=fileContent;
    }
    public String getFileName(){
        return this.fileName;
    }
    public byte[] getFileContent(){
        return this.fileContent;
    }
     @Override
    public String toString() {
        return new String(fileContent, StandardCharsets.UTF_8);
    }
}
