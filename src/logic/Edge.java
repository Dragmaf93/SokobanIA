package logic;

import jdlv.CellJDLV;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge{

	
	public CellJDLV getSource(){
		return (CellJDLV) super.getSource();
	}
	
	public CellJDLV getTarget(){
		return (CellJDLV) super.getTarget();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
