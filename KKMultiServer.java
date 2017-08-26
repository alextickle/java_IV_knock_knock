import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        System.out.println("Server starting...");

        try {
            serverSocket = new ServerSocket(4449);
            System.out.println("Listening on port: 4449");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4449.");
            System.exit(-1);
        }
        
        new Thread(new KnockKnockClient()).start();

        while (listening){
	       new KKMultiServerThread(serverSocket.accept()).start();
        }

        serverSocket.close();
    }
}
