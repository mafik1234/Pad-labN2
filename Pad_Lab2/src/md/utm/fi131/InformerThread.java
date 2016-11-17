package md.utm.fi131;

import sun.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;

public class InformerThread extends Thread {
	int thread;
	String configFile;
	Nodes thisNode;
	public ArrayList<Nodes> listNodes;
	

	public InformerThread(String configFile) {

		this.configFile = configFile;
		
	}

	public InformerThread(int i) {
		this.thread = i;
	}

	public void run() {
		thisNode = XmlTool.getNodeFtomConfig(configFile);//citeste configuratiile lui 
		listNodes = XmlTool.getNodeList(configFile);// citeste configuratiile cu nodurile care are legatura
		int port = 5000;
		String group = "225.4.5.6";	
		new TcpServer(thisNode,listNodes).start();
		Udp udp = new Udp();
		DatagramPacket receivePacket;
		try {
             //asteapta mesaje
			receivePacket = udp.reciveMulticast(group, port);
			String con = listNodes.size() + "";
			String res = "{\"nodeId\":\"" + thisNode.getId() + "\",\"nodeIP\":\"" + thisNode.getNodeIp()
					+ "\",\"tcpPort\":\"" + thisNode.getTcpPort() + "\",\"connections\":\"" + con + "\"}";
			udp.sendResponceToClient(res, receivePacket, port, group);
             // Tremite raspuns 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
