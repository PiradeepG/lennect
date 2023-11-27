import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
public class Server 
{
    ServerSocket serversocket;
    DataManager dataManager ;
    Socket socket ;
    BufferedReader br ;
    PrintWriter pr ;
    public Server()
    {
        try 
        {
            serversocket = new ServerSocket(4321);
            dataManager  = new DataManager() ; 
            System.out.println("hello");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Start()throws Exception
    {
        while(true)
        {
            socket = serversocket.accept();
            new Thread(new ClientHandler(socket , dataManager )).start();
        }
    }
    public static void main(String[] args) 
    {
        System.out.println("hello world");
        try
        {
            System.out.println("ll");
            new Server().Start();
            System.out.println("pp");
        }
        catch(Exception e)
        {
            System.out.println("Exception in stating the Start the method "+e);
        }
        
    }
}