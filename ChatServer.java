import java.io.*;
import java.net.*;

public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
    
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started, waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

          
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Client: " + message);
                output.println("Server: " + message);  // Echo the message back to the client

                if ("bye".equalsIgnoreCase(message)) {
                    System.out.println("Ending communication with the client.");
                    break;
                }
            }

          
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
