import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGui extends JFrame{
	private final JButton[] buttons;
	private String inputString;
	private String serverMessage;
	public static enum State {
		AWAITING_SERVER, AWAITING_USER
	};
	private State state; 
	
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
	private JLabel serverMesageLabel;
	private JLabel instructionsLabel;
	
	
	public ClientGui(){
		super("Knock Knock");
		this.state = State.AWAITING_SERVER;
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
		serverMesageLabel = new JLabel("No server message yet!", SwingConstants.CENTER);
		instructionsPanel.add(serverMesageLabel);
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
//		        kkSocket.close();
		        System.exit(0);
		    }
		});
		
		sendButton = new JButton("Send");
		sendButton.setVisible(true);
		buttons[1] = sendButton;
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				inputString = inputField.getText();
				System.out.println(inputString);
				// TODO send to server
				inputField.setText("");
				serverMessage = "";
				inputString = "";
				state = State.AWAITING_SERVER;
		    }
		});
		
		for (int count = 0; count < buttons.length; count++){
			buttonPanel.add(buttons[count]);
		}		
		add(buttonPanel, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
		
		this.start();
	}
		
	// opens view as a JFrame
	public void start(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	
}
