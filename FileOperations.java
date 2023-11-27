import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileOperations {
    public FileData getFileObject(String path){
        try{
        FileInputStream fis=new FileInputStream(path);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();

        byte[] buffer=new byte[1024];
        int bytereading;
        while((bytereading=fis.read(buffer))!=-1){
            baos.write(buffer, 0, bytereading);
        }

        return new FileData(new File(path).getName(), baos.toByteArray());
        }
        catch(Exception e){
        }
        return null;
    }

}
