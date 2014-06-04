import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class ServerConnection {

	static Socket sockToServer;
	static int port;
	static InetAddress address;
	
	public static void main(String[] args) throws IOException {
		
		
	Disconnect();{
		sockToServer.close();
		}
		
	Connect(address, port);{
		sockToServer = new Socket (address, port);
		sockToServer.getOutputStream();	
		}

	}

	static void Disconnect() {
		// TODO Auto-generated method stub
		
	}

	static void Connect(InetAddress address2, int port2) {
		// TODO Auto-generated method stub
		
	}

}
