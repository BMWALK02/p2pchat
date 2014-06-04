import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class ServerConnection {

	Socket sockToServer;
	int port;
	InetAddress address;
	

		
		
	void Disconnect() throws IOException{
		sockToServer.close();
		}
		
	 void Connect(InetAddress address, int port) throws IOException{
		sockToServer = new Socket (address, port);
		sockToServer.getOutputStream();	
		}

	

}

	
