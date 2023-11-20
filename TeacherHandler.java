import java.util.*;
import java.net.Socket;
public class TeacherHandler 
{
    Socket socket;
    DataManager dataManager ; 
    Teacher teacher ;
    TeacherHandler(Socket socket , DataManager db ,Teacher teacher )
    {
        this.socket = socket ;
        this.dataManager = db ;
        this.teacher = teacher ;
    }  
    public void Start()
    {
        System.out.println("Hello "+teacher.name);
    } 
}
