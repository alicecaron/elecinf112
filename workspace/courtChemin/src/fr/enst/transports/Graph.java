package fr.enst.transports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Metro.IGraph;
import Metro.VertexInterface;

public class Graph implements IGraph{

	private final Map<String,VertexInterface> stationsMap;
	
	public Graph(List<List<String>> jsonList) throws IOException{
		stationsMap = new HashMap<String,VertexInterface>();
		
		for(int k=0;k<jsonList.size();k++){
			if(jsonList.get(k).get(0)==null)
				break;
			if(!(jsonList.get(k).get(0).startsWith("#"))){
				List<String> lineStationList = new LinkedList<String>();
				lineStationList=jsonList.get(k);
				//does the station labeled lineStationList.get(i) already exist?
				for(int i=0;i<lineStationList.size();i++){
					if(!(stationsMap.containsKey(lineStationList.get(i))))
						stationsMap.put(lineStationList.get(i),new Station(lineStationList.get(i)));
					//add successors for the currentStation (stationsMap.containsKey(lineStationList.get(i)))
					Station currentStation = (Station) stationsMap.get(lineStationList.get(i));
					if(i>0){
						//add neighbor if the neighbor doesn't already exist
						if(!currentStation.getNeighbours().contains(stationsMap.get(lineStationList.get(i-1))))
							currentStation.addNeighbour(stationsMap.get(lineStationList.get(i-1)));
					}
					if(i<lineStationList.size()-1){
						//create the successor to add it in the currentStation successors if necessary
						if(!(stationsMap.containsKey(lineStationList.get(i+1)))){
							Station newStation= new Station(lineStationList.get(i+1));
							stationsMap.put(lineStationList.get(i+1),newStation);
						}
						if(!currentStation.getNeighbours().contains(stationsMap.get(lineStationList.get(i+1))))
							currentStation.addNeighbour(stationsMap.get(lineStationList.get(i+1)));
					}
				}
			}
		}
	}

	@Override
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> stationsList = new ArrayList<VertexInterface>();
		for(VertexInterface iStation : stationsMap.values())
			stationsList.add(iStation);
		return stationsList;
	}
	@Override
	public Map<String,VertexInterface> getStationsMap(){
		return stationsMap;
	}
	@Override
	public ArrayList<VertexInterface> getNeighbours(VertexInterface iStation) {
		Station station = (Station) iStation;
		return station.getNeighbours();
	}
	@Override
	public int getWeight(VertexInterface departure, VertexInterface arrrival) {
		return 1;
	}

	//remove excepted stations
	public void removeVertices(VertexInterface stationToRemove){
		//remove stationToRemove in each successor of the station to remove
		Station station = (Station) stationToRemove;
		ArrayList<VertexInterface> neighbors = new ArrayList<VertexInterface>(station.getNeighbours());
		for(VertexInterface neighbor : neighbors){
			List<VertexInterface> itsNeighbors = neighbor.getNeighbours();
			itsNeighbors.remove(stationToRemove);
		}
		//remove the station to remove
		stationsMap.values().remove(stationToRemove);
	}
}
