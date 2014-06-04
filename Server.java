package p2pchat;

import java.net.*;
//importing java.net.* will allows me to use ServerSocket()
import java.io.*;
//importing java.io.* allows me to use BufferedReader()

public class Server {
//Sets up a TCP server
	public static void main(String[] args) throws Exception {
		String clientSentence;
		ServerSocket welcomeSocket = new ServerSocket(5190);
		//Creates a new ServerSocket named welcomeSocket
		
		while (true){
			Socket connectionSocket = welcomeSocket.accept();
			//Listens for a connection to be made to the welcomeSocket and accepts the connection
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			//Creates a bufferedReader named inFromClient, which will read text from character-input stream from the connection socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			//Creates a data output stream that lets the server write primative java data types to the output stream.
			clientSentence = inFromClient.readLine();
			//reads a line of text from the bufferedreader inFromClient
			System.out.println("Received: " + clientSentence);
			outToClient.writeBytes(clientSentence);
		
		}
	}

}
