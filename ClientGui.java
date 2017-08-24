import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientGui extends JFrame{
	private final JButton[] buttons;
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
	private JLabel serverMessage;
	private JLabel instructionsLabel;
	
	
	public ClientGui(){
		super("Knock Knock");
		this.state = State.AWAITING_SERVER;
		
		// initialize main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		// initialize instructions panel
		instructionsPanel = new JPanel();
		instructionsPanel.setLayout(new GridLayout(3, 1));
		instructionsLabel = new JLabel("No instructions yet!", SwingConstants.CENTER);
		instructionsPanel.add(instructionsLabel);
		errorsLabel = new JLabel("No errors yet!", SwingConstants.CENTER);
		instructionsPanel.add(errorsLabel);
		serverMessage = new JLabel("No server message yet!", SwingConstants.CENTER);
		instructionsPanel.add(serverMessage);
		mainPanel.add(instructionsPanel);
		
		// initialize input panel
		inputPanel = new JPanel();
		inputField = new JTextField();
		inputField.setText("Testing - Input field");
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
				// TODO send input to server, set state to AWAITING_SERVER
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
	
	public void getInput(){
		String id = inputField.getText();
	}
}
