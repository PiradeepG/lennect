import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TeacherClient extends FileOperations {
    ObjectInputStream br;
    ObjectOutputStream os;
    String name;
    FileOperations fileOperations;
    public TeacherClient(ObjectInputStream br,ObjectOutputStream  os,String name) throws Exception{
        this.br=br;
        this.os=os;
        this.name=name;
        this.fileOperations=new FileOperations();
        System.out.println("In teacher client");
        start();
    }
    
    public void start() throws Exception{
        System.out.println("Successfully logged in "+name );
        System.out.println("What do you want to do?\n1-Post Assignment\n2-View submissions\n3-Exit");
        int choice=Client.getChoice();
        while(true){
            if(choice==1){
                os.writeObject(choice+"");
                sendFileToServer();
            }
            else if(choice==2){
                os.writeObject(choice+"");
            }
            else if(choice==3){
                new Client();
            }
            else{
            choice=Client.getChoice();
            }
            start();
        }
    }
    public void sendFileToServer() throws Exception {
        System.out.println("Enter the path of the file you want to send");
        FileData fileData=fileOperations.getFileObject(Client.getInput());
        while(fileData==null){
            System.out.println("Invalid Filepath\nEnter a correct path:");
            fileData=fileOperations.getFileObject(Client.getInput());
        }
        System.out.println("Found");
        os.writeObject(fileData);
        System.out.println("sent");
    }
}