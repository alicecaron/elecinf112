package Metro;

import java.util.Hashtable;

public class Pi implements IPi {
	
	private final Hashtable<VertexInterface,Integer> table;	
	public Pi(){
		table = new Hashtable<VertexInterface,Integer>();
	}
	@Override
	public void setValue(VertexInterface station, int value) {
		table.put(station,new Integer(value));
	}
	@Override
	public int getValue(VertexInterface station) {
		return table.get(station);
	}

}