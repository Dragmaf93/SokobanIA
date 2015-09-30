package jdlv;


public class Solver extends Thread {

	private SokobanJDLV sokobanJDLV;
	
	public Solver(SokobanJDLV sokobanJDLV) {
		this.sokobanJDLV=sokobanJDLV;
	}
	
	@Override
	public void run() {
		sokobanJDLV.findPath();
	}
}
