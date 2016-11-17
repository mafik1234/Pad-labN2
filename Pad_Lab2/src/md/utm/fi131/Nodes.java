package md.utm.fi131;

import java.util.ArrayList;

public class Nodes {
String id;
String nodeIp;
int tcpPort;
public Nodes(){}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getNodeIp() {
	return nodeIp;
}
public void setNodeIp(String nodeIp) {
	this.nodeIp = nodeIp;
}
public int getTcpPort() {
	return tcpPort;
}
public void setTcpPort(int tcpPort) {
	this.tcpPort = tcpPort;
}

}
