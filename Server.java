import java.io.*;
import java.net.*;

public class Server extends Thread {


    private int port;
    private ServerSocket serverSocket;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
    }
    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            this.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopServer() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                System.out.println("Waiting to connect...");
                Socket socket = serverSocket.accept();

                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();

            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        
        Server server = new Server(8080);
        System.out.println("Running on port " + server.port);
        server.startServer();

        try {
            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.stopServer();
    }
}

class RequestHandler extends Thread {
    private Socket socket;
    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Recieved a connection!");
            //socket.getInputStream is an InputStream coming from the socket.
            //We need to read it
            //InputStreamReader reads character by character, so we do
            //BufferReader and wrap it around InputStreamReader for efficiency.
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String inputLine = in.readLine();
            while(inputLine != null && inputLine.length() > 0) {
                out.println("Echo: " + inputLine);
                out.flush();
                System.out.println(inputLine + " said from " + socket.getLocalPort());
                inputLine = in.readLine();
            }
            in.close();
            out.close();
            socket.close();
        } 
        catch (IOException e) {
            e.getStackTrace();
        }
    }
}