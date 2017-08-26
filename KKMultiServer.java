import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {
    	ServerGui gui = null;
        ServerSocket serverSocket = null;
        boolean listening = true;
        System.out.println("Server starting...");

        try {
            serverSocket = new ServerSocket(4449);
            gui = new ServerGui(serverSocket);
            gui.setInstructionLabel("Listening on port 4449!");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4449.");
            System.exit(-1);
        }
        
        

        while (listening){
	       new KKMultiServerThread(serverSocket.accept()).start();
        }

        serverSocket.close();
    }
}
