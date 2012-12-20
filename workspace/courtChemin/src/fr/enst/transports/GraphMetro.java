package fr.enst.transports;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GraphMetro{

	public Graph graph;
	
	public GraphMetro (BufferedReader buffer) throws IOException{
		String line;
		List<List<String>> lines = new LinkedList<List<String>>();
		while(true){
			line = buffer.readLine();
			if(line==null)
				break;
			if(!(line.startsWith("#"))){
				List<String> lineStationList = new LinkedList<String>();
				lineStationList=CommonFonctions.stationList(line);
				lines.add(lineStationList);
			}
		}
		this.graph = new Graph(lines);
	}
}
