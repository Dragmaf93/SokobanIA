package logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jdlv.CellJDLV;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GeneratorPathPlayer {

	public DirectedGraph<CellJDLV, Edge> worldGraph ; 
	private List<Edge> lastGeneratedPath;
	
	public GeneratorPathPlayer() {
		worldGraph=new DefaultDirectedGraph<>(Edge.class);
	}
	
	public void addVertex(CellJDLV ground){
		worldGraph.addVertex(ground);
	}
	public void addEdge(CellJDLV ground1, CellJDLV ground2){
		
		worldGraph.addEdge(ground1, ground2);
	}
	
	public void findPathBetween(CellJDLV start,CellJDLV finish){
		
//		DijkstraShortestPath<CellJDLV, Edge> dijkstraShortestPath = new DijkstraShortestPath<CellJDLV, Edge>(worldGraph, start, finish);
		lastGeneratedPath = DijkstraShortestPath.findPathBetween(worldGraph, start, finish);
		System.out.println(lastGeneratedPath);
	}
	
	public boolean hasSolution(){
		if(lastGeneratedPath==null) return false;
		return true;
	}
	public boolean removeVertex(CellJDLV ground){
		System.out.println("rimuovi "+ ground);
		return worldGraph.removeVertex(ground);
	}
		
	public void addPlayerMovement(List<String> playerMovements){
		for (int i = 0; i < lastGeneratedPath.size() ;i++ ) {
			Edge edge = lastGeneratedPath.get(i);
			CellJDLV source = edge.getSource();
			CellJDLV target = edge.getTarget();
			
			if(source.getI()==target.getI()){
				if(source.getJ()+1 == target.getJ())
					playerMovements.add("right");
				else if(source.getJ()-1 == target.getJ())
					playerMovements.add("left");
			}
			else if(source.getJ()==target.getJ()){
				if(source.getI()+1 == target.getI()){
					playerMovements.add("down");
				}else if(source.getI()-1 == target.getI()){
					playerMovements.add("up");
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		
		GeneratorPathPlayer generatorPathPlayer = new GeneratorPathPlayer();
		List<String> playerMovements = new ArrayList<String>();
		
		CellJDLV ground1 = new CellJDLV(0, 0);
		CellJDLV ground2 = new CellJDLV(0, 1);
		CellJDLV ground3 = new CellJDLV(0, 2);
		CellJDLV ground4 = new CellJDLV(1, 0);
		CellJDLV ground5 = new CellJDLV(1, 1);
		
		generatorPathPlayer.addVertex(ground1);
		generatorPathPlayer.addVertex(ground2);
		generatorPathPlayer.addVertex(ground3);
		generatorPathPlayer.addVertex(ground4);
		generatorPathPlayer.addVertex(ground5);
		
		generatorPathPlayer.addEdge(ground1, ground2);
		generatorPathPlayer.addEdge(ground1, ground4);
		generatorPathPlayer.addEdge(ground2, ground3);
		generatorPathPlayer.addEdge(ground4, ground5);
		
		generatorPathPlayer.findPathBetween(ground1, ground3);
		
		generatorPathPlayer.addPlayerMovement(playerMovements);
		
		System.out.println(playerMovements);
		
	}
	
}
