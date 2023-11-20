import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentHandler {

    Socket socket;
    DataManager dataManager ; 
    Student student;
    StudentHandler(Socket socket , DataManager db ,Student student )
    {
        this.socket = socket ;
        this.dataManager = db ;
        this.student = student ;
    }  
    public void Start()
    {
        System.out.println("Hello "+student.name);

    } 
    
}
