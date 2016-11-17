package md.utm.fi131;
import sun.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		Udp udp=new Udp();
		int port = 5000;
		ArrayList<String> listNodeInf = new ArrayList<String>();
		String group = "225.4.5.6";
		String message = "Get info";
		listNodeInf=udp.getInfNodes(group,port,message);
		String serverName = "localhost";
		int portTcp = getPortMaxNode(listNodeInf);
		
		Tcp tcp=new Tcp();
		Socket soket = new Socket(serverName, portTcp);

		Message mes = new Message("command", "get", "all");
		Gson gson = new Gson();
		String jmes = gson.toJson(mes);
		tcp.tcpSend(jmes, soket);
		String res=tcp.tcpReceive(soket);
		System.out.println("tcp resp "+res+"\n");
			
	}
	public static int getPortMaxNode(ArrayList<String> listNodeInfo) {
		  int max = 0, tcpPort = 0;
		  for (String item : listNodeInfo) {
		   int con;
		   try {
		    con = Integer.parseInt(getAttribute("connections", item));

		    if (con >= max) {
		     max = con;
		     tcpPort = Integer.parseInt(getAttribute("tcpPort", item));

		    }
		   } catch (NumberFormatException e) {
		    e.printStackTrace();
		   }

		  }
		  return tcpPort;
		 }
	
	//scot atribut din json
	public static String getAttribute(String key, String input) {
		  JSONObject jObject;
		  String command = null;
		  try {
		   jObject = new JSONObject(input);
		   command = jObject.getString(key);
		  } catch (JSONException e) {
		   e.printStackTrace();
		  }

		  return command;
		 }
}
