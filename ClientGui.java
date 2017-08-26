import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGui extends JFrame{
	String fromServer;
    String fromUser;
	private final JButton[] buttons;
	private String inputString;
	private String serverMessage;
	private PrintWriter out;
	private BufferedReader in;
	
	// main panel
	private final JPanel mainPanel;
	
	// button panel
	private final JPanel buttonPanel;
	private final JButton stopButton;
	private final JButton sendButton;
	
	// input panel
	private final JPanel inputPanel;
	private final JTextField inputField;
	
	
	// instructions panel
	private final JPanel instructionsPanel;
	private JLabel errorsLabel;
	private JLabel serverMessageLabel;
	private JLabel instructionsLabel;
	
	
	public ClientGui(Socket socket){
		super("Client");
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		inputString = "";
		serverMessage = "";
		
		// initialize main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		// initialize instructions panel
		instructionsPanel = new JPanel();
		instructionsPanel.setLayout(new GridLayout(3, 1));
		instructionsLabel = new JLabel("No instructions yet!", SwingConstants.CENTER);
		instructionsPanel.add(instructionsLabel);
		errorsLabel = new JLabel("", SwingConstants.CENTER);
		instructionsPanel.add(errorsLabel);
		serverMessageLabel = new JLabel("No server message yet!", SwingConstants.CENTER);
		instructionsPanel.add(serverMessageLabel);
		mainPanel.add(instructionsPanel);
		
		// initialize input panel
		inputPanel = new JPanel();
		inputField = new JTextField("", 15);
		inputPanel.add(inputField);
		mainPanel.add(inputPanel);
		
		// initialize button panel
		buttons = new JButton[2];
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		stopButton = new JButton("Stop");
		stopButton.setVisible(true);
		buttons[0] = stopButton;
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// immediately close socket and ClientGui
				try {
					socket.close();
					System.exit(0);
				} catch (IOException e) {
					errorsLabel.setText(e.toString());
				}
				instructionsLabel.setText("socket closed");
		    }
		});
		
		sendButton = new JButton("Send");
		sendButton.setVisible(true);
		buttons[1] = sendButton;
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				inputString = inputField.getText();
				System.out.println(inputString);
				out.println(inputString);
				inputField.setText("");
				serverMessage = "";
				inputString = "";
		    }
		});
		
		for (int count = 0; count < buttons.length; count++){
			buttonPanel.add(buttons[count]);
		}		
		add(buttonPanel, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
		try {
			this.start();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
		
	// opens view as a JFrame
	public void start() throws IOException{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setVisible(true);
	
			while ((fromServer = in.readLine()) != null) {
			    setServerMessageLabel("Server: " + fromServer);
			    if (fromServer.equals("Bye."))
			        break;
			}
	}

	
	public void setInstructionLabel(String str){
		this.instructionsLabel.setText(str);
	}
	
	public void setErrorsLabel(String str){
		this.errorsLabel.setText(str);
	}
	
	public void setServerMessageLabel(String str){
		this.serverMessageLabel.setText(str);
	}
	
}
