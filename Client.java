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
    private BufferedReader systbufReader;
    private Socket sc;
    private Scanner scanner;

    public Client() {
        scanner = new Scanner(System.in);
        systbufReader = new BufferedReader(new InputStreamReader(System.in));
        setUserName();
        connect();
    }

    public void connect() {
        try {
            sc = new Socket("10.52.0.162", 1234);
            start(sc);
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

    public String getInput() {
        try {
            return systbufReader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public int getChoice() {
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

    public void start(Socket obj) {
        try (ObjectInputStream br = new ObjectInputStream(obj.getInputStream());
             ObjectOutputStream os = new ObjectOutputStream(obj.getOutputStream())) {

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
                os.writeObject(userName + "¬" + email + "¬" + password + "¬" + status + "¬" + subject);
                break;
            }
             else if (choice == 2) {
                os.writeObject(choice + "");
                System.out.println("How do you want to login?\n1-Using this computer\n2-Using email & password");
                int loginChoice = getChoice();

                if (loginChoice == 2) {
                    getDetailsFromUser();
                    os.writeObject(userName + "¬" + email + "¬" + password);
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
                    System.out.println("Invalid username and password");
                    getDetailsFromUser();
                    os.writeObject(userName + "¬" + email + "¬" + password);
                } 
                else if (line.equals("e1")) {
                    System.out.println("Sorry wrong password");
                    getPasswordFromUser();
                    os.writeObject(userName + "¬" + email + "¬" + password);
                }
                else if(line.equals("t")){
                    new TeacherClient(br, os);
                }
                else if(line.equals("s")){
                    new StudentClient(br, os);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error in client I/O: " + e.getMessage());
        }
    }

    public void getDetailsFromUser() {
        System.out.print("Enter your Email: ");
        email = getInput();
        getPasswordFromUser();
    }

    public void getPasswordFromUser() {
        System.out.print("Enter your password: ");
        password = getInput();
    }

    public static void main(String[] args) {
        Client cl = new Client();
    }
}
