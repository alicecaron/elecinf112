import org.zeromq.*;
import org.zeromq.ZMQ.*;

public class Sender{
    public static void main(String[] args){

    	if(args.length<2 || args.length>2){
		System.out.println("You must enter a Robot name AND a message!");
		System.exit(0);
	 }

	String message = args[0]+"|"+args[1];
	Context context = ZMQ.context(1);

	//Push socket
	Socket sender = context.socket(ZMQ.PUSH);

	//Connection to the server
	sender.connect("tcp://arrakis.enst.fr:4161");
	
	//Sending message
	sender.send(message.getBytes(), 0);

	//cleaning up
	sender.close();
	context.term();
	


    }
}
