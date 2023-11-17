import javax.sound.midi.Track;

public class Teacher extends User
{
    String subject ;
    Teacher(String name , String password , String email , String isAdmin)
    {
        super(name, password, email, isAdmin);
    }
}
