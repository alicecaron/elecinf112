package fr.enst.transports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GraphURL{

	public Graph graph;
	
	public GraphURL(LinkedList<ArrayList<String>> jsonList) throws IOException{
		
		List<List<String>> lines = new LinkedList<List<String>>();
		
		//transform jsonList in lines
		for(int i=0;i<jsonList.size();i++){
			if(jsonList.get(i).get(0)==null)
				break;
			if(!(jsonList.get(i).get(0).startsWith("#"))){
				lines.add(jsonList.get(i));
			}
		}
		this.graph = new Graph(lines);
	}
}
