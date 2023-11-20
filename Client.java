import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private String userName;
    private String email;
    private String password;
    private char status;
    private String subject;
    private static BufferedReader systbufReader;
    private Socket sc;
    private  static Scanner scanner;
    ObjectOutputStream os;
    ObjectInputStream br;
    Console console;
    public Client(){
        console= System.console();
        scanner = new Scanner(System.in);
        systbufReader = new BufferedReader(new InputStreamReader(System.in));
        setUserName();
        connect();
    }

    public void connect(){
        try {
            sc = new Socket("10.52.0.112", 4321);
            br = new ObjectInputStream(sc.getInputStream());
            os = new ObjectOutputStream(sc.getOutputStream());
            start();
        } catch (IOException e) {
            System.err.println("Error in connecting to the server: " + e.getMessage());
        }
    }

    public void setUserName() {
        try {
            Process process = Runtime.getRuntime().exec("whoami");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            userName = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error in getting client name: " + e.getMessage());
        }
    }

    public static String getInput() {
        try {
            return systbufReader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public static int getChoice() {
        int num = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter a number: ");
                num = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter a proper number");
                scanner.nextLine();
            }
        }
        return num;
    }

    public void start(){
         try{

            System.out.println("What do you wish to do?\n1-SIGNUP\n2-LOGIN\n3-EXIT");
            int choice = getChoice();
            while(true){
            if (choice == 1) {
                os.writeObject(choice + "");
                System.out.println("How do you want to signup?\n1-As a Teacher\n2-As a student");
                int stream = getChoice();
                while(true){
                if (stream == 1) {
                    status = 't';
                    System.out.println("Which stream are you?\n1-Java\n2-Python\n3-JavaScript");
                    int subChoice = getChoice();

                    while (true) {
                        if (subChoice >= 1 && subChoice <= 3) {
                            subject = (subChoice == 1) ? "Java" : (subChoice == 2) ? "Python" : "JavaScript";
                            break;
                        } 
                        else {
                            System.out.println("Invalid input");
                            subChoice = getChoice();
                        }
                    }
                    break;
                } 
                else if (stream == 2) {
                    status = 's';
                    break;
                } 
                else {
                    System.out.println("Invalid input");
                    stream=getChoice();
                }
            }
                getDetailsFromUser();
                password=PasswordChecker.validatePassword(password);
                if(status=='t'){
                    os.writeObject(userName + "¬" + email + "¬" + password + "¬" + status + "¬" + subject);
                    break;
                }
                else{
                    os.writeObject(userName + "¬" + email + "¬" + password + "¬" + status);
                    break;
                }
            }
             else if (choice == 2) {
                os.writeObject(choice + "");
                System.out.println("How do you want to login?\n1-Using this computer\n2-Using email & password");
                int loginChoice = getChoice();

                if (loginChoice == 2) {
                    getDetailsFromUser();
                    os.writeObject(userName + "¬"+ password);
                }
                else if(loginChoice==1){
                    os.writeObject(userName);
                }
                break;
            } 
            else if (choice == 3) {
                System.exit(1);
                break;
            }
            else{
                System.out.println("Invalid input");
                choice=getChoice();
            }
        }
            String line;
            while ((line = (String) br.readObject()) != null) {
                System.out.println(line);
                if (line.equals("e2")) {
                    System.out.println("Invalid user");
                    start();
                } 
                else if (line.equals("e1")) {
                    System.out.println("Sorry wrong password");
                    System.out.println("Do you want to continue?\n1-Yes\n2-Goback");
                    int backChoice=getChoice();
                    while(true){
                        if(backChoice==1){
                            System.out.println("Here in choice 1");
                            os.writeObject("go");
                            getPasswordFromUser();
                            os.writeObject(userName + "¬" + email + "¬" + password);
                            break;
                        }
                        else if(backChoice==2){
                            os.writeObject("exit");
                            start();
                        }
                        else{
                            System.out.println("Invalid input");
                            backChoice=getChoice();
                        }
                    }
                }
                else if(line.equals("e0")){
                    System.out.println("Username already exist's");
                    start();
                }
                else if(line.equals("t")){
                    new TeacherClient(br, os,userName);
                }
                else if(line.equals("s")){
                    new StudentClient(br, os);
                }
            }
         }
         catch(Exception e){
            System.out.println("error");
         } 
    }

    public void getDetailsFromUser() {
        System.out.print("Enter your Email: ");
        email = getInput();
        getPasswordFromUser();
    }

    public void getPasswordFromUser() {
        password = new String(console.readPassword("Enter your password\n"));
    }

    public static void main(String[] args){
        Client cl = new Client();
    }
}
