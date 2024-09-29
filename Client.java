import java.io.*;
import java.net.*;

public class Client{
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        final int PORT = server.getPort(); 
        Socket socket = new Socket("localhost", PORT);
        
        
        socket.close();
    }
}
