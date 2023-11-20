import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class DataManager 
{
    ArrayList<User> arr ; 
    File UserList = new File("DataBase/UserList.txt");
    FileWriter fr ;
   
    DataManager()
    {
        arr = new ArrayList<User>();
        load(); 
        try
        {
            fr = new FileWriter(UserList , true);
        }
        catch(Exception e)
        {
            System.out.println("error in the file userlist "+e);
        }
    }
    private void load() 
    {
        Scanner sc = null ; 
        try
        {
            sc  = new Scanner(UserList);
            while (sc.hasNextLine()) 
        {
                String splitter[] = sc.nextLine().split("¬");
                System.out.println(Arrays.toString(splitter));
                System.out.println(" length == "+splitter.length);
                if(splitter.length==5)
                {
                    Teacher teacher = new Teacher(splitter[0],splitter[1], splitter[2], splitter[3]);
                    teacher.subject = splitter[4];
                    arr.add(teacher);
                }
                else
                {
                    Student student = new Student(splitter[0],splitter[1], splitter[2], splitter[3]);
                    arr.add(student);
                }
                System.out.println("added user ");
        }
        }
        catch(Exception e)
        {
            System.out.println("error while reading"+e);
        }
        
        
    }
    public  int isValidUser(String userName , String password)
    {
        System.out.println("inside the funtion is valiud user");
        int i = 0;
        System.out.println("password  given                            == "+password);
        System.out.println("size of array list "+arr.size());
        System.out.println("checkin as a valid user");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        for (User user : arr) 
        {
            System.out.println("user name" +user.name);
            if(user.name.equals(userName))
            { 
                System.out.println("orginal password "+user.password);
                if(user.password.equals(password))
                {
                    System.out.println("user name and password is correct");
                    return i;
                }
                System.out.println("user name is correct but password is wrong");
                return -4;
            }    
            i++;
        }
        System.out.println("no such user exist");
        return -1;
    }   
    public int isValidUser(String userName)
    {
        int i = 0 ;
        for (User user : arr) 
        {
            System.out.println("user name" +user.name);
            i ++ ;
            if(user.name.equals(userName))
            {
               return i;
            }    
        }
        return -1;
    }
    public static void sayHello()
    {
        System.out.println("hello");
    } 
    public void addUser(String user) throws IOException
    {
        fr.write(user+"\n");
        fr.flush();
    }
}