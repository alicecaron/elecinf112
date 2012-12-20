package Metro;

import java.util.HashSet;

public class SetImpl implements ISet {

	private final HashSet<VertexInterface> setInterface;

	public SetImpl(){
		setInterface = new HashSet<VertexInterface>();
	}
	@Override
	public void add(VertexInterface station) {
		setInterface.add(station);
	}
	@Override
	public boolean contains(VertexInterface station) {
		return setInterface.contains(station);
	}

}
