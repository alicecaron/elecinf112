// javac -cp /usr/local/share/java/zmq.jar Robot.java
// java -Djava.library.path=/usr/local/lib -cp .:/usr/local/share/java/zmq.jar Robot AliceBot

import org.zeromq.*;
import org.zeromq.ZMQ.*;

import java.util.*;


public class Robot{
    
    private static final Context context = ZMQ.context(1);
    private static final ArrayList<Robot> robotList = new ArrayList<Robot>();
    private static final long bufferTime = 3000;
    private LinkedList<Long> antifloodList = new LinkedList<Long>();
    
    //Robots attributes
    private final String name;
    private final Socket requester = context.socket(ZMQ.SUB);
    private final Socket sender= context.socket(ZMQ.PUSH);


    public static void main (String[] args) {
    	if(args.length<=0){
			System.out.println("You must enter at least one name for your robot!");
			System.exit(0);
    	}
		for(String arg : args)
			robotList.add(new Robot(arg));	
	    	
		//Switching robots to handle messages
		while(true){
		    for(Robot rob : robotList)
			rob.handleMessage();
		}
    }
    
    public Robot(String name){
	
	this.name=name;
	//sockets and server connections
	requester.connect("tcp://arrakis.enst.fr:4162");
	requester.subscribe("".getBytes());
	sender.connect("tcp://arrakis.enst.fr:4161");
	System.out.println(name+" is connected to the server!");	   
    }

    private void handleMessage(){

	//Listening the server
	String reception = new String(requester.recv(0));
	String[] dividedReception = reception.split("\\|",2);
	if(dividedReception.length<2)
		return;

	//String receivedName = dividedReception[0];
	String receivedMessage = dividedReception[1];

	//comparison between the robot name and the message to know if the message is for the robot
	String[] table = receivedMessage.split(" ");
	boolean match = false;
	for(String string : table){
	    if(string.equals(name))
		match=true;
	}
	
	System.out.println(name+" received : "+reception);
    
	if(match){
	    //reversing the message
	    String reversedMessage = new StringBuffer(receivedMessage).reverse().toString();
	    System.out.println("This message contains your name.");
	    //envoi du message : inversion de reception
	    reversedMessage=name+"|"+reversedMessage;
	    
		if(antifloodList.size()<5){
	    	antifloodList.add(System.currentTimeMillis());
	    	reversedMessage+=antifloodList.size();
	       	sender.send(reversedMessage.getBytes(), 0);
	    }
	    else{
	    	long time = System.currentTimeMillis();
	    	if((time-antifloodList.peek()-bufferTime)>0){
	    		antifloodList.removeFirst();
	    		antifloodList.addLast(time);
	    		reversedMessage+=antifloodList.size();
	    		sender.send(reversedMessage.getBytes(), 0);
	    	}
	    	else{
	    		System.out.println("This message was not sent.");
	    	}
	    }
	}
	/*
	else{
	    //Loop between robots
	    int index = robotList.indexOf(this);
	    int nb = robotList.size();
	    if(index==nb-1)
		index = -1;
	    String followingName = robotList.get(index+1).name;
	    sender.send((name+"|"+"Hello "+followingName).getBytes(),0);
	}*/
    }


}
