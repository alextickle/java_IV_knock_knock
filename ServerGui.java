import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ServerGui extends JFrame{
	ServerSocket serverSocket;
	int port = 4449;
	
	// main panel
	private final JPanel mainPanel;
	
	// button panel
	private JButton[] buttons = new JButton[3];
	private final JPanel buttonPanel;
	private final JButton createClient;
	private final JButton startServer;	
	private final JButton stopServer;	
	
	// instructions panel
	private final JPanel instructionsPanel;
	private JLabel errorsLabel;
	private JLabel instructionsLabel;
	
	public ServerGui(){
		super("Server");
		
		// initialize main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		// initialize instructions panel
		instructionsPanel = new JPanel();
		instructionsPanel.setLayout(new GridLayout(3, 1));
		errorsLabel = new JLabel("", SwingConstants.CENTER);
		instructionsLabel = new JLabel("", SwingConstants.CENTER);
		instructionsPanel.add(errorsLabel);
		mainPanel.add(instructionsPanel);
		
		// initialize button panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		startServer = new JButton("Start Server");
		startServer.setVisible(true);
		buttons[0] = startServer;
		startServer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try {
					startServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		    }
		});
		
		stopServer = new JButton("Stop Server");
		stopServer.setVisible(true);
		buttons[1] = stopServer;
		stopServer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try {
					stopServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		
		createClient = new JButton("Create Client");
		createClient.setVisible(true);
		buttons[2] = createClient;
		createClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				createClient();
		    }
		});
		
		for (int count = 0; count < buttons.length; count++){
			buttonPanel.add(buttons[count]);
		}		
		add(buttonPanel, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
		
	}
		
	// opens view as a JFrame
	public void start() throws IOException{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	
	public void startServer() throws IOException{
        serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
            instructionsLabel.setText("Server listening on port " + port);
        } catch (IOException e) {
            errorsLabel.setText("Could not listen on port " + port);
            System.exit(-1);
        }
        
        while (listening){
	       new KKMultiServerThread(serverSocket.accept()).start();
        }

        serverSocket.close();
	}
	
	public void stopServer() throws IOException{
		serverSocket.close();
	}
	
	public void createClient(){
    	new Thread(new KnockKnockClient()).start();
    }
	
}
