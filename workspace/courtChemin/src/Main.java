import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;

import Metro.IGraph;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.enst.transports.Chemin;
import fr.enst.transports.GraphMetro;
import fr.enst.transports.GraphURL;
import fr.enst.transports.ServerSocket;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		IGraph metro;
		
		if(args.length<3){
			System.err.println("Need a URL, a filename or a station name");
			System.exit(0);
		}
		if(args[0].contains("://")){
			//import map from server with a 0mq socket
			ServerSocket jsonREQ = new ServerSocket(args[0], "{ \"request\" : \"map\" }");
			
			//jsonMap a déjsonifier
			LinkedList<ArrayList<String>> jsonList;
			ObjectMapper mapper = new ObjectMapper();
			jsonList = mapper.readValue(jsonREQ.getJsonString(),LinkedList.class);
			metro = new GraphURL(jsonList).graph;
			Chemin path = new Chemin(args, metro);
		}
		else{
			try{
				Reader reader = new FileReader(args[0]);
				BufferedReader buffer = new BufferedReader(reader);
				metro = new GraphMetro(buffer).graph;
				Chemin path = new Chemin(args, metro);
			}catch(IOException e){
				System.err.println("Unable to get file \""+args[0]+"\"");
				System.exit(0);
			}
		}		
	}
}