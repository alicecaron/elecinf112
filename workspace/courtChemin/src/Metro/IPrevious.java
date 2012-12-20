package Metro;

import java.util.ArrayList;

public interface IPrevious {

	public void setValue(VertexInterface station, VertexInterface value);
	public VertexInterface getValue(VertexInterface station);
	public ArrayList<VertexInterface> getShortestPath(VertexInterface departure);
}
