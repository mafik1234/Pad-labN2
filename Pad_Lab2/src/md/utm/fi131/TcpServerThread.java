package md.utm.fi131;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

;

public class TcpServerThread extends Thread {

	protected Socket server;
	protected String topName;
	ArrayList<Nodes> listNodes;
	Tcp tcp = new Tcp();
	public ArrayList<Integer> colection=new ArrayList<Integer>();
	
	public ArrayList<Worker> listWorkers = new ArrayList<Worker>();
	private Nodes thisNode;
	public TcpServerThread(Socket clientSocket,Nodes thisNode, ArrayList<Nodes> listNodes) {
		this.server = clientSocket;
		this.listNodes = listNodes;
		this.thisNode=thisNode;

	}

	public void run() {

		populateColection();
		try {
			FileReader fr=new FileReader();
			
			listWorkers=fr.getObjFromFile("col"+thisNode.getId()+".json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * for(Integer node : colection){
		 * System.out.println("colect item  "+node); }
		 */

		while (true) {
			try {

				DataInputStream in = new DataInputStream(server.getInputStream());
				String input = in.readUTF();
			
				String command = getAttribute("command", input);
				String message = getAttribute("message", input);
				if (command.equals("get")) {
					if (message.equals("all")) {
						ArrayList<String> listResp = new ArrayList<String>();
						listResp = getAll(server);
						String json = new Gson().toJson(listWorkers);

						listResp.add(json);

						tcp.tcpSend(listResp.toString(), server);
					} else if (message.equals("yours")) {
						String json = new Gson().toJson(listWorkers);
						tcp.tcpSend(json, server);
					}

				}

			} catch (IOException e) {
				System.out.println("Client deconectat With Ctrl+C");
				// e.printStackTrace();
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getAttribute(String key, String input) throws JSONException {
		JSONObject jObject = new JSONObject(input);
		String command = jObject.getString(key);
		return command;
	}

	public void populateColection() {
		
		int n = (listNodes.size() + 1) * 10;
		for (int i = 1; i <= n; i++) {
			Random rand = new Random();

			int  val = rand.nextInt(50) + 1;
			colection.add(val);
		}
	}

	public ArrayList<String> getAll(Socket server) {
		ArrayList<String> listResp = new ArrayList<String>();

		for (Nodes node : listNodes) {
			Socket soket;
			try {
				soket = new Socket(node.getNodeIp(), node.getTcpPort());

				Message mes = new Message("command", "get", "yours");
				Gson gson = new Gson();
				String jmes = gson.toJson(mes);
				tcp.tcpSend(jmes, soket);

				listResp.add(tcp.tcpReceive(soket));
				soket.close();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listResp;

	}

}
