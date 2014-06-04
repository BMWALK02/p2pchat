import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.util.*;
import java.io.*;

public class ClientGUI {

	private JFrame frame;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	// /Variables here kthx
	String username;
	InetAddress address;
	int port;
	BufferedReader reader; // reads data that comes from the server
	PrintWriter clientWriter; // prints data over network over to server
	static boolean isconnected = false;
	ArrayList<String> userList = new ArrayList(); // arraylist that holds
													// usernames

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.setToolTipText("Click me to send your message");
		btnSend.setBounds(329, 336, 89, 23);
		frame.getContentPane().add(btnSend);

		txtUsername = new JTextField();
		txtUsername.setToolTipText("Type in a username here");
		txtUsername.setText("UserName");
		txtUsername.setBounds(27, 11, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JTextPane txtpnPutYourMessages = new JTextPane();
		txtpnPutYourMessages.setText("Put your messages here");
		txtpnPutYourMessages.setBounds(27, 315, 292, 67);
		frame.getContentPane().add(txtpnPutYourMessages);

		final JList listOnlineUsers = new JList();
		listOnlineUsers
				.setToolTipText("These are the users that are currently connected");
		listOnlineUsers.setBounds(329, 40, 95, 265);
		frame.getContentPane().add(listOnlineUsers);

		JLabel lblOnlineUsers = new JLabel("Online Users");
		lblOnlineUsers.setBounds(346, 14, 65, 14);
		frame.getContentPane().add(lblOnlineUsers);

		final TextArea textArea = new TextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(28, 37, 292, 272);
		frame.getContentPane().add(textArea);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectedPressed();

			}

			// Code for when the connect button is pressed
			private void ConnectedPressed() {
				if (isconnected == true) {
					textArea.append("Already connected. \n");
				} else if (isconnected == false) {
					username = txtUsername.getText();
					txtUsername.setEditable(false);
					try {
						ServerConnection.Connect(address, port);
						InputStreamReader streamreader = new InputStreamReader(
								ServerConnection.sockToServer.getInputStream());
						reader = new BufferedReader(streamreader);
						clientWriter = new PrintWriter(
								ServerConnection.sockToServer.getOutputStream());
						clientWriter.println(username
								+ ":has connected.:Connect"); // Displays to
																// everyone that
																// user
																// connected.
						clientWriter.flush(); // flushes the buffer
						isconnected = true;
					} catch (Exception ex) {
						textArea.append("Error Connecting! Try Again. \n");
						txtUsername.setEditable(true);
					}

				}

			}
		});
		btnConnect.setBounds(123, 10, 89, 23);
		frame.getContentPane().add(btnConnect);

		JButton btnDisconect = new JButton("Disconnect");
		btnDisconect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisconnectedPressed();
			}

			// code for when the disconnect button is pressed
			private void DisconnectedPressed() {
				if (isconnected == false) {
					textArea.append("You're already disconnected! \n");
				} else if (isconnected == true) {
					txtUsername.setEditable(true);
					try {
						textArea.append("Disconnected \n");
						ServerConnection.Disconnect();
					} catch (Exception ex) {
						textArea.append("Could not disconnect. \n");
					}
					isconnected = false;
					listOnlineUsers.removeAll();
				}
			}

		});
		btnDisconect.setBounds(222, 10, 114, 23);
		frame.getContentPane().add(btnDisconect);
	}
}

