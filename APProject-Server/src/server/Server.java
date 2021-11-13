package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket socket;
	private Socket conSocket;
	
	public Server() {
		try {
			socket = new ServerSocket(8000);
			
			while(true) {
				conSocket = socket.accept();
				new Thread(new ThreadHandler(conSocket)).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
	}
	
	private void closeConnection() {
		try {
			conSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
