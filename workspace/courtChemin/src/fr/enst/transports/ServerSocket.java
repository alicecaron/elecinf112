package fr.enst.transports;

import org.zeromq.*;
import org.zeromq.ZMQ.*;

public class ServerSocket {
	
	private String jsonString;
	private String url;
	private Context context;
	private Socket requester;
	
    public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public ServerSocket(String url, String order) {
		this.setUrl(url);
		this.context = ZMQ.context(1);
		this.requester = context.socket(ZMQ.REQ);
		requester.connect(url);
		sendOrder(order);
		//Sending and listening to the server
		//requester.send(order.getBytes(), 0);
		//jsonString = new String(requester.recv(0));
    }
	
	public void sendOrder(String order){
		//Sending and listening to the server
		requester.send(order.getBytes(), 0);
		jsonString = new String(requester.recv(0));
	}
}