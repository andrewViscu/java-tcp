import java.io.*;
import java.net.*;

public class Server {


    final static int PORT = 8080;

    public int getPort() {
        return PORT;
    }
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Running on port " + PORT);
        Socket clientSocket = serverSocket.accept();

        System.out.println("Client connected!");
        System.out.println(clientSocket.getLocalAddress());

        clientSocket.close();
        serverSocket.close();
    }
}