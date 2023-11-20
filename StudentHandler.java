import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentHandler {

    Socket socket;
    DataManager dataManager ; 
    Student student;
    ObjectInputStream br ;
    ObjectOutputStream pr ;
    StudentHandler(ObjectInputStream br , ObjectOutputStream pr , DataManager db , Student student )
    {
        this.dataManager = db ;
        this.student = student ;
        try 
        {
            this.pr = pr;
            this.br = br;
            System.out.println("hello");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }  
    public void Start()
    {
        try 
        {
            System.out.println("Hello student "+student.name);
            pr.writeObject("s");
        } 
        catch (Exception e) 
        {
            System.out.println("error in student start "+e);
            // TODO: handle exception
        }
        
    } 
    
}
