import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class TeacherHandler 
{
    Socket socket;
    DataManager dataManager ; 
    Teacher teacher ;
    ObjectInputStream br ;
    ObjectOutputStream pr ;
    TeacherHandler(ObjectInputStream br , ObjectOutputStream pr , DataManager db ,Teacher teacher )
    {
        // this.socket = socket ;
        this.dataManager = db ;
        this.teacher = teacher ;
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
    public void Start()throws Exception
    {
        try 
        {
            System.out.println("Hello teacher "+teacher.name);
            pr.writeObject("t");
        } 
        catch (Exception e) 
        {
           
            // TODO: handle exception
        }
        
    } 
}
