package ferme;

public class MapNode {
	
	private String _start;
	private String _end;
	private int _length;
	
	private String _direction;
	
	public String getDirection() {
		return _direction;
	}
	public void setDirection(String _direction) {
		this._direction = _direction;
	}
	public String getStart() {
		return _start;
	}
	public void setStart(String _start) {
		this._start = _start;
	}
	public String getEnd() {
		return _end;
	}
	public void setEnd(String _end) {
		this._end = _end;
	}
	public int getLength() {
		return _length;
	}
	public void setLength(int _length) {
		this._length = _length;
	}
	
}
