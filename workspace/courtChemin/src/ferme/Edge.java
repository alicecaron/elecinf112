package ferme;

import Metro.VertexInterface;

public class Edge {

	private int length;
	private VertexInterface start;
	private VertexInterface end;
	
	public Edge(int length, VertexInterface start, VertexInterface end) {
		this.length = length;
		this.start = start;
		this.end = end;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public VertexInterface getStart() {
		return start;
	}
	public void setStart(VertexInterface start) {
		this.start = start;
	}
	public VertexInterface getEnd() {
		return end;
	}
	public void setEnd(VertexInterface end) {
		this.end = end;
	}

	

}
