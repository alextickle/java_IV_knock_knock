import java.net.*;
import java.io.*;

public class KKMultiServer implements Runnable{
	boolean listening = true;
    public void run() {
        ServerSocket serverSocket = null;
        listening = true;
        System.out.println("Server starting...");

        try {
            serverSocket = new ServerSocket(4449);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4449.");
        }
        
        try {
	    	while (listening){
	  	       new KKMultiServerThread(serverSocket.accept()).start();
	        }
        } catch(IOException e){
        	e.printStackTrace();
        }
        
        try {
        	serverSocket.close();
        } catch(IOException e){
        	e.printStackTrace();
        }
        
    }
    
    public void stopServer(){
    	listening = false;
    }
}
