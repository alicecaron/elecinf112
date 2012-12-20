package ferme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Metro.IGraph;
import Metro.VertexInterface;

public class GraphMap implements IGraph{

	private final Map<String,VertexInterface> stationsMap;
	private final List<Edge> edgeList;
	
	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public GraphMap(LinkedList<MapNode> jsonList) throws IOException{
		
		stationsMap = new HashMap<String,VertexInterface>();
		edgeList = new LinkedList<Edge>();
		for(MapNode mapNode : jsonList){

			//Add vertex start and end if they don't exist
			Vertex start = (Vertex) stationsMap.get(mapNode.getStart());
			if (start == null) {
				start = new Vertex(mapNode.getStart());
				stationsMap.put(mapNode.getStart(), start);
			}
			
			VertexInterface end = stationsMap.get(mapNode.getEnd());
			if (end == null) {
				end = new Vertex(mapNode.getEnd());
				stationsMap.put(mapNode.getEnd(), end);
			}
			
			
			if(!stationsMap.containsKey(mapNode.getStart())){
				stationsMap.put(mapNode.getStart(), start);
			}
			if(!stationsMap.containsKey(mapNode.getEnd())){
				stationsMap.put(mapNode.getEnd(), end);
			}
			
			
			//Add edge with its length
			Edge edge = new Edge(mapNode.getLength(),start,end);
			Edge edgeBack = new Edge(mapNode.getLength(),end,start);
			edgeList.add(edge);
			
			//Configure mutual vertex' neighbour
			if(mapNode.getDirection().equals("horizontal")){
				start.setEast(edge);
				((Vertex) end).setWest(edgeBack);
			}
			else{
				start.setSouth(edge);
				((Vertex) end).setNorth(edgeBack);

			}

			start.addNeighbour(end);
			end.addNeighbour(start);
		}
	}

	@Override
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> stationsList = new ArrayList<VertexInterface>();
		for(VertexInterface node : stationsMap.values())
			stationsList.add(node);
		return stationsList;
	}
	@Override
	public Map<String,VertexInterface> getStationsMap(){
		return stationsMap;
	}
	@Override
	public ArrayList<VertexInterface> getNeighbours(VertexInterface vertex) {
		return (ArrayList<VertexInterface>) vertex.getReachableNeighbours();
	}
	@Override
	public int getWeight(VertexInterface departure, VertexInterface arrival) {
		return ((Vertex) departure).length(arrival);
	}
	@Override
	//remove excepted stations
	public void removeVertices(VertexInterface stationToRemove){
		//remove stationToRemove in each successor of the station to remove
		Vertex station = (Vertex) stationToRemove;
		ArrayList<VertexInterface> neighbors = new ArrayList<VertexInterface>(station.getNeighbours());
		for(VertexInterface neighbor : neighbors){
			List<VertexInterface> itsNeighbors = neighbor.getNeighbours();
			itsNeighbors.remove(stationToRemove);
		}
		//remove the station to remove
		stationsMap.values().remove(stationToRemove);
	}	
}