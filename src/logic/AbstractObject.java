package logic;

import jdlv.CellJDLV;

public abstract class AbstractObject implements ObjectGame 
{
	int positionI;
	int positionJ;
	
	CellJDLV cell;
	
	public AbstractObject(int i, int j) 
	{
		setCell(i, j);
	}
	
	@Override
	public CellJDLV getCellJDLV() {
		return cell;
	}
	
	@Override
	public int getI() {
		return positionI;
	}

	@Override
	public int getJ() {
		return positionJ;
	}

	@Override
	public void setI(int i) {
		cell.setI(i);
		this.positionI = i;
	}

	@Override
	public void setJ(int j){
		cell.setJ(j);
		this.positionJ = j;
	}

	@Override
	public void setCell(int i, int j) {
		if(cell==null)
			cell = new CellJDLV(i, j);
		cell.setI(i);
		cell.setJ(j);
		this.positionI = i;
		this.positionJ = j;
		
	}

}
