package fr.enst.transports;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommonFonctions {

	
	static List<List<String>> makeTrainLines(BufferedReader buffer) throws IOException{
		final List<List<String>> trainLines = new LinkedList<List<String>>();
		
		//get each trainLine as a list of Strings (stations)
		String currentBufferLine;
		while(true){
			currentBufferLine=buffer.readLine();
			if(currentBufferLine==null)
				return trainLines;
			if(!currentBufferLine.startsWith("#")){
				trainLines.add(stationList(currentBufferLine));
			}
		}
	}
	
	static List<String> stationList(String line){
		String[] stationTab;
		stationTab=line.split("\\,");
		return Arrays.asList(stationTab);
	}
	
	static Map<String,Integer> createMap(List<List<String>> trainLines){
		Map<String,Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < trainLines.size(); i++) {
			for(String trainStation : trainLines.get(i))
				//test key's presence
				if(map.containsKey(trainStation))
					map.put(trainStation, map.get(trainStation)+1);
				else
					map.put(trainStation, 1);
		}
		return map;
	}

	static void printKeyPoints(Map<String, Integer> map){
		//search key points in the map
		for (Map.Entry<String,Integer> key : map.entrySet()){
			if(key.getValue()!=1)
				System.out.println(key.getKey());
		}		
	}
	
	static List<List<String>> lineListing(String station,BufferedReader buffer) throws IOException{
		List<List<String>> lineList=new LinkedList<List<String>>();
		String currentBufferLine;
		while(true){
			currentBufferLine=buffer.readLine();
			if(currentBufferLine==null)
				return lineList;
			if(!currentBufferLine.startsWith("#")){
				//does the current line contains the station?
				if(currentBufferLine.matches(station))
					//retrieving the station list of the line that contains the station
					lineList.add(CommonFonctions.stationList(currentBufferLine));
			}
		}
	}
}
