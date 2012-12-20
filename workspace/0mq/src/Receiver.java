import org.zeromq.*;
import org.zeromq.ZMQ.*;

public class Receiver{
    public static void main (String[] args) {
	System.out.println("Loading to server");
	Context context = ZMQ.context(1);

	//Connection to the server
	Socket requester = context.socket(ZMQ.SUB);
	requester.connect("tcp://arrakis.enst.fr:4162");
	requester.subscribe("".getBytes());

	System.out.println("Connected to the server!");

	//Listening to the server
	while(true){
	    String reception = new String(requester.recv(0));
	    System.out.println(reception);
	}
	/*
        // Cleaning sockets
	requester.close();
	context.term();
	*/
    }
}
