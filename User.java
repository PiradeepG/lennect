import java.util.*;
public class User 
{
    String name ;
    String password ;
    String email ;
    Boolean isAdmin ;
    User()
    {
        name = null ;
        password = null;
        email=null;
        isAdmin=false;
    }
    User(String name , String password , String email , String isAdmin)   
    {
        this.name = name ;
        this.email = email ;
        this.password = password ;
        this.isAdmin = isAdmin.equals("t")?true:false ;
    }
}