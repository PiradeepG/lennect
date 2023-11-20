import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
class ClientHandler implements Runnable 
{
    Socket socket ;
    ObjectInputStream br ;
    ObjectOutputStream pr ;
    DataManager dataManager ;
    User currentUser = null ;
    ClientHandler(Socket socket , DataManager dataManager)
    {
        System.out.println("A new device connected ");
        try
        {
            this.socket = socket ; 
            pr =new ObjectOutputStream(socket.getOutputStream());
            br = new ObjectInputStream(socket.getInputStream());
            this.dataManager = dataManager ; 
            System.out.println("initilides all ");
        }
        catch(Exception e)
        {
            System.out.println("error while initilising the buffred and printwriter");
        }
        
    }   
    public void run()
    {
        try 
        {
           
            int choise  =  Integer.parseInt((String)br.readObject());
            System.out.println("chosise "+choise);
            String authString = (String)br.readObject();
            System.out.println(authString);
            if(choise == 1)
            {
                signUp(authString);
            }
            else
            {
                logIn(authString);
            }
        } 
        catch (Exception e) 
        {
           
        }
    }
    public void logIn(String authString) throws Exception 
    {
        int loginResponse = dataManager.isValidUser(authString.split("¬")[0], authString.split("¬")[1]);
        if(loginResponse == -4)
        {
            pr.writeObject("e1");// e1 -- > PASSWORD IS WRONG 
            
        }
        else if(loginResponse > 0)
        {
            currentUser = dataManager.arr.get(loginResponse);
           
            // if(currentUser.isAdmin())
            // { 
            //     pr.writeObject("student");
            //     Teacher teacher = (Teacher)(currentUser);
            //     teacher.start();   
            // }
            // else
            // {
            //     pr.writeObject("teacher:");
            //     Student student = (Student)(currentUser); 
            //     student.start();
            // }
        }
        else
        {
            pr.writeObject("e2"); //  E3  -- > NO SUCH USER IS FOUND 
        }
    }
    public void signUp(String authString)throws Exception
    {
        System.out.println(" going to the signup block");
        String splitter[] = authString.split("¬");
        System.out.println(Arrays.toString(splitter));
        System.out.println(splitter[0]+"   "+splitter[1]);
        System.out.println(splitter[0] + splitter[1]);
        int loginResponse = dataManager.isValidUser(splitter[0], splitter[1]);
        System.out.println("Login response "+loginResponse);
        System.out.println("end of sign up");
        
        if(loginResponse == -1)
        {
            pr.writeObject("go");
            System.out.println("the user can be added");
            if(splitter.length > 4) // TEACHER IS TRYING TO SINGUP 
            {
                System.out.println("TEACHER IS TRYING TO SINGUP ");
                Teacher teacher = new Teacher(splitter[0],splitter[1], splitter[2], splitter[3]);
                teacher.subject = splitter[4];
                dataManager.arr.add(teacher);
                TeacherHandler obj = new TeacherHandler(socket, dataManager , teacher);
                pr.writeObject("go");
                obj.Start();
            }
            else //  STUDENT IS TRYING TO SIGNUP 
            {
                System.out.println("CHILDREN IS TRYING TO SIGNUP ");
                Student student = new Student(splitter[0],splitter[1], splitter[2], splitter[3]);
                dataManager.arr.add(student);
                StudentHandler obj = new StudentHandler(socket, dataManager , student);
                obj.Start();
            }
        }
        else
        {
            pr.writeObject("e0");
        }
    }
}
