package md.utm.fi131;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Tcp {

	//trimmite mesaj
	public void tcpSend(String mes,Socket client){
		

		try {
			
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			
			out.writeUTF(mes);
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//citeste mesaj
	public String tcpReceive(Socket client){
		 InputStream inFromServer;
		try {
			inFromServer = client.getInputStream();
			  DataInputStream in = new DataInputStream(inFromServer);
			  String mes=in.readUTF();
		      //  System.out.println("Server says " + mes);
		        return mes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	      
	}
	
	
	
	
}
