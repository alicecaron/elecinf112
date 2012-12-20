package Metro;

import java.util.ArrayList;
import java.util.List;

public interface VertexInterface {
	public String getLabel();
	public List<VertexInterface> getNeighbours();
	public void addNeighbour(VertexInterface station);
	//public float getDistance(IStation neighbour);
	//public int length(VertexInterface arrival);
	void setReachable(boolean reach);
	boolean isReachable();
	ArrayList<VertexInterface> getReachableNeighbours();
	public int length(VertexInterface arrival);
}
