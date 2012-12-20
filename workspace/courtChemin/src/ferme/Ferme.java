package ferme;

import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.enst.transports.Chemin;
import fr.enst.transports.ServerSocket;

public class Ferme {

	public static void main(String[] args) throws Exception{

		//Using the map (import from the server) to get the shortest path
		ServerSocket jsonREQ = new ServerSocket("tcp://a405-01.enst.fr:9734", "{ \"request\" : \"map\" }");
		
		ObjectMapper mapper = new ObjectMapper();
		LinkedList<MapNode> jsonList = mapper.readValue(jsonREQ.getJsonString(), new TypeReference<LinkedList<MapNode>>() { });

		GraphMap graph = new GraphMap(jsonList);
		Chemin path = new Chemin(args, graph);
		
		//lefthand
		Robot robot = new Robot();
		//robot.left_hand();
		robot.pathToArrival(path.getShortestPath());
	}
}