package fr.enst.transports;

import java.util.ArrayList;

import Metro.VertexInterface;

public class Station implements VertexInterface {

	private String label;
	private boolean reachable;
	private ArrayList<VertexInterface> neighbours;
	
	public Station(String label){
		this.label=label;	
		this.neighbours = new ArrayList<VertexInterface>();
	}
	@Override
	public void addNeighbour(VertexInterface station){
		neighbours.add(station);
	}
	@Override
	public String getLabel() {
		return label;
	}
	@Override
	public ArrayList<VertexInterface> getNeighbours() {
		return neighbours;
	}
	@Override
	public void setReachable(boolean reach) {
		// TODO Auto-generated method stub
		
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
	@Override
	public int length(VertexInterface arrival) {
		// TODO Auto-generated method stub
		return 0;
	}
}
