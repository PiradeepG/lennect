import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentClient extends FileOperations {
    ObjectInputStream br;
    ObjectOutputStream os;
    public StudentClient(ObjectInputStream br,ObjectOutputStream  os){
        this.br=br;
        this.os=os;
        System.out.println("In student client");
    }
}
