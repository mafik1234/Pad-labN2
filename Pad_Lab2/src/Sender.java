import sun.net.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
public class Sender {

 public static void main(String[] args) throws IOException {
	// Which port should we send to
	 int port = 6789;
	 // Which address
	 String group = "225.4.5.7";
	 // Which ttl
	 int ttl = 1;
	 // Create the socket but we don't bind it as we are only going to send data
	 MulticastSocket s = new MulticastSocket();
	 // Note that we don't have to join the multicast group if we are only
	 // sending data and not receiving
	 // Fill the buffer with some data
	 byte buf[] = new byte[1024];
	// for (int i=0; i<buf.length; i++) buf[i] = (byte)i;
	 String string="kesa";
	  buf = string.getBytes(Charset.forName("UTF-8"));
	 // Create a DatagramPacket 
	 DatagramPacket pack = new DatagramPacket(buf, buf.length,
	 					 InetAddress.getByName(group), port);
	 // Do a send. Note that send takes a byte for the ttl and not an int.
	 s.send(pack,(byte)ttl);
	 // And when we have finished sending data close the socket
	 s.close();
 }

}