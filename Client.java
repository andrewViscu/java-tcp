import java.io.*;
import java.net.*;

public class Client{
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Please, specify the port.");
            System.out.println("Usage: Client <port>");
            System.exit(0);
        }
        int port = 0;
        try {
            port = Integer.valueOf(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Port number must be an integer.");
            System.exit(0);
        }
        System.out.println("Trying to connect to localhost:" + port);
        try {
        Socket socket = new Socket("localhost", port); 

        //Read about in Server.java
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String fromUser = stdIn.readLine();
        String fromServer = "";
        while(fromUser != null && fromUser.length() > 0) {
           out.println(fromUser);
           out.flush();
            fromServer = in.readLine();
            System.out.println(fromServer);
            fromUser = stdIn.readLine();
        }
        in.close();
        out.close();
        socket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }       
}
