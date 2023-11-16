import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TeacherClient {
    ObjectInputStream br;
    ObjectOutputStream os;
    public TeacherClient(ObjectInputStream br,ObjectOutputStream  os){
        this.br=br;
        this.os=os;
    }
}