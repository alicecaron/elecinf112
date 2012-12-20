package ferme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Metro.VertexInterface;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.enst.transports.ServerSocket;

public class Robot {


	private Map<String,String> commandList = new HashMap<String,String>();
	private ServerSocket socket;
	private SensorState robotSensors;
	private ObjectMapper mapper;
	private int orientation;

	public Robot() throws Exception{
		commandList.put("move", "{ \"command\" : \"move\",\"target\" : \"AC\"}");
		commandList.put("turnL","{ \"command\" : \"turn\",\"target\" : \"AC\",\"direction\" : \"L\" }");
		commandList.put("uTurn","{ \"command\" : \"turn\",\"target\" : \"AC\",\"direction\" : \"U\" }");
		commandList.put("turnR","{ \"command\" : \"turn\",\"target\" : \"AC\",\"direction\" : \"R\" }");
		commandList.put("sensors", "{\"command\" : \"sensors\",\"target\"  : \"AC\" }");
		commandList.put("reset", "{\"command\" : \"reset\",\"target\"  : \"AC\" }");

		orientation=0;
		socket = new ServerSocket("tcp://a405-01.enst.fr:4160",commandList.get("reset"));
		mapper = new ObjectMapper();
		robotSensors = mapper.readValue(socket.getJsonString(), new TypeReference<SensorState>() { });
		
	}
	
	public void left_hand() throws Exception{
		if(canTurnLeft()){
			update("turnL");
			update("move");
		}
		else{
			//update("uTurn");
			if(canMove())
				update("move");
			else{
				if(canTurnLeft()){
					update("turnL");
					update("move");
				}
				else{
					update("uTurn");
					if(canMove())
						update("move");
					else
						System.err.println("WARNING : Robot can not turn or move!");
				}	
			}
		}
	}
	
	public void pathToArrival(List<VertexInterface> vertexList) throws JsonParseException, JsonMappingException, IOException{
		while(vertexList.size()>1){
			Vertex currentStation = (Vertex) vertexList.get(vertexList.size()-1);
			List<VertexInterface> currentNeighbours = currentStation.getNeighbours();
			int direction;
			String dir = null;
			for(VertexInterface neighbour : currentNeighbours)
				if(vertexList.get(vertexList.size()-2)==neighbour){
					direction = currentStation.direction(vertexList.get(vertexList.size()-2));
					dir = orientation(direction);
				}
			if(!dir.equals("move") && !dir.equals("not"))
				update(dir);
			update("move");
			vertexList.remove(vertexList.size()-1);
		}
	}
	
	public String orientation(int direction){
		String dir=new String();
//		String[] table={"move","turnR","uTurn","turnL"};
//		String[] newTable = null;
//		
//		for(int i=0;i<0;i--)
//			if(i>=orientation)
//				newTable[3-i]=table[i];
//			else
//				newTable[i]=table[i];
//		
		
		switch(orientation){
		case 0:
			if(direction==0)dir = "move";
			else if(direction==1)dir = "turnR";
			else if(direction==2)dir = "uTurn";
			else if(direction==3)dir = "turnL";
			break;
		case 1:
			if(direction==0)dir = "turnL";
			else if(direction==1)dir = "move";
			else if(direction==2)dir = "turnR";
			else if(direction==3)dir = "uTurn";
			break;
		case 2:
			if(direction==0)dir = "uTurn";
			else if(direction==1)dir = "turnL";
			else if(direction==2)dir = "move";
			else if(direction==3)dir = "turnR";
			break;
		case 3:
			if(direction==0)dir = "turnR";
			else if(direction==1)dir = "uTurn";
			else if(direction==2)dir = "turnL";
			else if(direction==3)dir = "move";
			break;
		}
		orientation=direction;
		return dir;
	}
	
	public void update(String order) throws JsonParseException, JsonMappingException, IOException{
		if(order.equals("move"))
			System.out.println("I Move!");
		else if(order.equals("turnL"))
			System.out.println("I Turn Left!");
		else if(order.equals("turnR"))
			System.out.println("I Turn Right!");
		else 
			System.out.println("PROBLEM : command not found : "+order);
		socket.sendOrder(commandList.get(order));
		robotSensors = mapper.readValue(socket.getJsonString(), new TypeReference<SensorState>() { });
		
	}
	public boolean canMove() throws JsonParseException, JsonMappingException, IOException{
		System.out.println(socket.getJsonString());
		if(robotSensors.getSensors().get(1)==255 && robotSensors.getSensors().get(2)==255 && robotSensors.getSensors().get(3)==255)
			return true;
		else
			return false;
	}
	
	public boolean canTurnLeft() throws JsonParseException, JsonMappingException, IOException{
		System.out.println(socket.getJsonString());
		if(robotSensors.getSensors().get(0)==255)
			return true;
		else
			return false;
	}

}