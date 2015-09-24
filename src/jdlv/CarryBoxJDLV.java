package jdlv;

import java.util.Set;

public class CarryBoxJDLV implements Comparable<CarryBoxJDLV> {
	int time;
	int id;
	String direction;
	int i;
	int j;

	public CarryBoxJDLV() {

	}

	public CarryBoxJDLV(int time, int id, String direction) {
		this.time = time;
		this.id = id;
		this.direction = direction;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	@Override
	public String toString() {
		return "[CarryBoxJDLV time=" + time + ", id=" + id + ", direction="
				+ direction + " i=" + i + ", j=" + j+"] ";
	}

	@Override
	public int compareTo(CarryBoxJDLV c) {

		if (this.time > c.getTime())
			return 1;
		return -1;
	}

}
