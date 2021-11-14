package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
	private ServerSocket socket;
	private Socket conSocket;
	
	private static final Logger logger = LogManager.getLogger(Server.class);
	
	public Server() {
		try {
			socket = new ServerSocket(8000);
			
			while(true) {
				conSocket = socket.accept();
				new Thread(new ThreadHandler(conSocket)).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
			logger.error("An IO exception occured when creating an object of the server");
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(  e.getClass().getName() + " exception occured when creating an object of the server");
		}finally {
			closeConnection();
		}
	}
	
	private void closeConnection() {
		try {
			conSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
			logger.error( " An IO exception occured when closing the connection on the server");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(  e.getClass().getName() + " exception occured when closing the connection on the server");
		}
	}
}
