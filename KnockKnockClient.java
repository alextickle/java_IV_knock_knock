import java.io.*;
import java.net.*;

public class KnockKnockClient implements Runnable{
    public void run(){
		Socket kkSocket = null;
        ClientGui gui = null;
        String laptopName = "127.0.0.1";

        try {
            kkSocket = new Socket(laptopName, 4449);
            gui = new ClientGui(kkSocket);
            gui.setInstructionLabel("Listening on port 4449!");
        } catch (UnknownHostException e) {
        	gui.setErrorsLabel("Don't know about host: " + laptopName + ".");
            System.exit(1);
        } catch (IOException e) {
        	gui.setErrorsLabel("Couldn't get I/O for the connection to: " + laptopName + ".");
            System.exit(1);
        }
	}
}
