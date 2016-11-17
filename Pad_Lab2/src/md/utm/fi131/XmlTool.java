package md.utm.fi131;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTool {

	
	 public static  Nodes getNodeFtomConfig(String conf) {
    	File fXmlFile = new File(conf);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	Nodes infNode=new Nodes();
	try{
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	

		doc.getDocumentElement().normalize();


	NodeList nList = doc.getElementsByTagName("thisNode");
	
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);



		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			infNode.setId(eElement.getAttribute("id"));
			infNode.setNodeIp(eElement.getElementsByTagName("ip").item(0).getTextContent());
			infNode.setTcpPort(Integer.parseInt(eElement.getElementsByTagName("tcpPort").item(0).getTextContent()));

		}
	}
	}catch(Exception e){}
	return infNode;
    }
	
	 public static  ArrayList<Nodes> getNodeList(String conf) {
		 
		 
			File fXmlFile = new File(conf);
			 ArrayList<Nodes> listNodes = new ArrayList<Nodes>();
			try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
	
			NodeList nList = doc.getElementsByTagName("node");



	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);


		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			 Nodes infNode=new Nodes();
			infNode.setId(eElement.getAttribute("id"));
			infNode.setNodeIp(eElement.getElementsByTagName("ip").item(0).getTextContent());
			infNode.setTcpPort(Integer.parseInt(eElement.getElementsByTagName("tcpPort").item(0).getTextContent()));
			listNodes.add(infNode);
		}
	}}catch(Exception e){}
	return listNodes;
    } 
  
 

}