package Metro;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;



public class Previous implements IPrevious{

	private final Hashtable<VertexInterface, VertexInterface> table;
	private LinkedList<Integer> weight = new LinkedList<Integer>();
	
	public LinkedList<Integer> getWeight() {
		return weight;
	}
	public void setWeight(int value) {
		weight.push(value);
	}
	public Previous(){
		table = new Hashtable<VertexInterface,VertexInterface>();
	}
	@Override
	public void setValue(VertexInterface station, VertexInterface value) {
		table.put(station, value);
	}
	@Override
	public ArrayList<VertexInterface> getShortestPath(VertexInterface station) {
		ArrayList<VertexInterface> shortestPath = new ArrayList<VertexInterface>();
		while(station!=null){
			shortestPath.add(station);
			station=getValue(station);
			
			//collect weight
			if(shortestPath.size()>0){
				weight.add(shortestPath.get(shortestPath.size()-1).length(station));
			}

		}
		weight.removeLast();
		return shortestPath;
	}
	@Override
	public VertexInterface getValue(VertexInterface station) {
		return table.get(station);
	}
}