package jdlv;

public class BoxJDLV
{
	int time;
	int id;
	int i;
	int j;
	
	public BoxJDLV() {

	}

	public BoxJDLV(int time, int id, int i, int j) {
		this.time = time;
		this.id = id;
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoxJDLV [time=" + time + ", id=" + id + ", i=" + i + ", j=" + j
				+ "]";
	}


}
