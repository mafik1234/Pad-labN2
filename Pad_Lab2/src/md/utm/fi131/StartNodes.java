package md.utm.fi131;
import sun.net.*;

import java.io.IOException;
import java.net.*;


public class StartNodes {

	public static void main(String[] args) throws InterruptedException  {
		new InformerThread("conf0.xml").start();
		
		new InformerThread("conf1.xml").start();
		
		new InformerThread("conf2.xml").start();
		
		new InformerThread("conf3.xml").start();
		
		new InformerThread("conf4.xml").start();
	
		new InformerThread("conf5.xml").start();
	}

}
