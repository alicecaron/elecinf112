package ferme;

import java.util.ArrayList;
import java.util.List;

import Metro.VertexInterface;

public class Vertex implements VertexInterface{

	private final String label;
	private final List<VertexInterface> neighbours;
	private boolean reachable;
	private Edge west;
	private Edge east;
	private Edge north;
	private Edge south;
	
	public Vertex(String label){
		this.label=label;
		neighbours = new ArrayList<VertexInterface>();
		reachable = true;
	}
	public Edge getWest() {return west;}
	public void setWest(Edge edge) {this.west = edge;}
	public Edge getEast() {return east;}
	public void setEast(Edge edge) {this.east = edge;}
	public Edge getNorth() {return north;}
	public void setNorth(Edge edge) {this.north = edge;}
	public Edge getSouth() {return south;}
	public void setSouth(Edge edge) {this.south = edge;}


	public int direction(VertexInterface vertex){		
		int direction = -1;
		if(west!=null && west.getEnd() == vertex)
			direction = 3;
		if(south!=null && south.getEnd() == vertex)
			direction = 2;
		if(east!=null && east.getEnd() == vertex)
			direction = 1;
		if(north!=null && north.getEnd() == vertex)
			direction = 0;
		return direction;
	}
	
	public int length(VertexInterface vertex){
		if(direction(vertex)==3)
			return west.getLength();
		if(direction(vertex)==1)
			return east.getLength();
		if(direction(vertex)==2)
			return south.getLength();
		if(direction(vertex)==0)
			return north.getLength();
		return -1;
	}
	
	@Override		
	public String getLabel() {
		return label;
	}
	@Override
	public List<VertexInterface> getNeighbours() {
		return neighbours;
	}
	@Override
	public void addNeighbour(VertexInterface vertex) {
		this.neighbours.add(vertex);
	}
	
	@Override
	public void setReachable(boolean reach){
		this.reachable=reach;
	}
	@Override
	public boolean isReachable(){
		return reachable;
	}
	@Override
	public ArrayList<VertexInterface> getReachableNeighbours() {
		ArrayList<VertexInterface> reachableNeighbours = new ArrayList<VertexInterface>();
		for(VertexInterface reachableNeighbour : neighbours)
			if(reachableNeighbour.isReachable())
				reachableNeighbours.add(reachableNeighbour);
		return reachableNeighbours;
	}
}