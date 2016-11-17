package md.utm.fi131;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;





public class TcpServer  extends Thread {

	 private ServerSocket serverSocket;
	 private int port;
	 public  ArrayList<Nodes> listNodes;
	 Nodes thisNode;
	 public TcpServer(Nodes thisNode,ArrayList<Nodes> listNodes) {
		this.thisNode=thisNode;
		this.listNodes=listNodes;
		this.port=thisNode.getTcpPort();
	}
	public void run() {

		 try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	 	   
		
	     

	      while(true) {
	         try {
	            System.out.println("Waiting for client on port " + 
	               serverSocket.getLocalPort() + "...");
	            //serverul accespta conectiuni
	            Socket server = serverSocket.accept();
	            new TcpServerThread(server,thisNode,listNodes).start();
	            
	           
	            	}catch(SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e) {
	        	  System.out.println("Client deconectat");
	            e.printStackTrace();
					break;
	         } 
	      }
	   }
	   }

