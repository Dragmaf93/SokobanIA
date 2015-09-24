package jdlv;

public class CellJDLV 
{
	int i;
	int j;
	
	public CellJDLV()
	{
		
	}
	public CellJDLV(int i, int j)
	{
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
	@Override
	public String toString() {
		return "CellJDLV [i=" + i + ", j=" + j + "]";
	}
	
	@Override
	public boolean equals(Object obj){ 
		CellJDLV c = (CellJDLV) obj;
		return this.i == c.i && this.j == c.j;
	}
}
