package logic;

import jdlv.CellJDLV;

public interface ObjectGame 
{
	public int getI();
	public int getJ();
	
	public void setI(int i);
	public void setJ(int j);
	
	public void setCell(int i, int j);
	
	public CellJDLV getCellJDLV();
	
}
