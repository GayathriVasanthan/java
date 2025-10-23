import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {

            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to the server.");


            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userMessage;
            String serverMessage;

        
            while (true) {
                System.out.print("You: ");
                userMessage = userInput.readLine();
                serverOutput.println(userMessage);  // Send message to the server

                serverMessage = serverInput.readLine();  // Get server's response
                System.out.println(serverMessage);  // Display the server's response

                if ("bye".equalsIgnoreCase(userMessage)) {
                    System.out.println("Ending conversation.");
                    break;
                }
            }


            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
