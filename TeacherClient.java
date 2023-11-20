import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TeacherClient extends FileOperations {
    ObjectInputStream br;
    ObjectOutputStream os;
    String name;
    public TeacherClient(ObjectInputStream br,ObjectOutputStream  os,String name){
        this.br=br;
        this.os=os;
        System.out.println("In teacher client");
    }
    
    public void start(){
        System.out.println("");
    }
}