package ferme;

import java.util.List;

public class SensorState {
	
	private String _status;
	private List<Integer> _sensors;
	private String _target;

	public String getTarget() {
		return _target;
	}
	public void setTarget(String _target) {
		this._target = _target;
	}
	public String getStatus() { return _status; }
	public void setStatus(String _status) {this._status = _status;}
	
	public List<Integer> getSensors() {
	    return _sensors;
	}

	public void setSensors(List<Integer> _sensors) {
	    this._sensors = _sensors;
	}
}