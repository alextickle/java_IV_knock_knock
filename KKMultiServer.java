import java.net.*;
import java.util.ArrayList;

public class KKMultiServer implements Runnable{
	boolean listening = true;
	ArrayList<KKMultiServerThread> threads = new ArrayList<KKMultiServerThread>();
	KKMultiServerThread temp;
	
    public void run() {
        ServerSocket serverSocket = null;
        
        listening = true;
        System.out.println("Server starting...");

        try {
            serverSocket = new ServerSocket(4449);
        } catch (Exception e) {
            System.err.println("Could not listen on port: 4449.");
        }
        
        try {
	    	while (listening){
	  	       temp = new KKMultiServerThread(serverSocket.accept());
	  	       threads.add(temp);
	  	       temp.start();
	        }
        } catch(Exception e){}
        
        try {
        	serverSocket.close();
        } catch(Exception e){}
        
    }
    
    public void stopServer(){
    	listening = false;
    	for (KKMultiServerThread thread: threads){
    		thread.interrupt();
    	}
    }
}
