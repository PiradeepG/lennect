import java.io.File;
import java.util.*;
public class DataManager 
{
    ArrayList<User> arr ; 
    File UserList = new File("DataBase/UserList.txt");
    DataManager()
    {
        arr = new ArrayList<User>();
        load();
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
                    Teacher teacher = new Teacher(splitter[0],splitter[1], splitter[2], splitter[3].equals("t"));
                    teacher.subject = splitter[4];
                    arr.add(teacher);
                }
                else
                {
                    Student student = new Student(splitter[0],splitter[1], splitter[2], splitter[3].equals("t"));
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
        System.out.println("checkin as a valid user");
        for (User user : arr) 
        {
            System.out.println("user name" +user.getUserName());
            if(user.getUserName().equals(userName))
            {
                if(user.getPassword().equals(password))
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
    public static void sayHello()
    {
        System.out.println("hello");
    } 
}