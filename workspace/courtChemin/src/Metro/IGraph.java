package Metro;

import java.util.ArrayList;
import java.util.Map;

public interface IGraph {
	public ArrayList<VertexInterface> getAllVertices();
	public ArrayList<VertexInterface> getNeighbours(VertexInterface station);
	public Map<String,VertexInterface> getStationsMap();
	public int getWeight(VertexInterface src, VertexInterface dst);
	public void removeVertices(VertexInterface verticesToRemove);
}
