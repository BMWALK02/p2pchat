package p2pchat;

import java.io.*;
import java.net.*;

public class Client {
	
	private static String sentence;
	private static String modifiedSentence;
	public static void main(String[] args) throws Exception {
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader (new InputStreamReader(System.in));
		//creates a new Bufferedreader named inFromuser that reads input from user input
		Socket clientSocket = new Socket ("localhost", 5190);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
		sentence= inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence= inFromServer.readLine();
		System.out.println("From Server: " + modifiedSentence);
		clientSocket.close();

	}
	public class API {
		String apiSentence = Client.sentence;
		String apimodSentence = Client.modifiedSentence;
	
		//allows for other codes to interact with Client code
		
	}
	
}
