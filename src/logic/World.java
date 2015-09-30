package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.FoundIndex;

import jdlv.BoxJDLV;
import jdlv.CellJDLV;

public class World {
	private int numberRow;
	private int numberColumn;

	private ObjectGame world[][];
	private CellJDLV worldGround[][];
	private ArrayList<Goal> goals;
	private ArrayList<Box> boxs;

	GeneratorPathPlayer generatorPathPlayer;
	private Player player;
	private ArrayList<BoxJDLV> boxForJDLV;
	private CellJDLV playerJDLV;
	private ArrayList<CellJDLV> goalForJDLV;
	private ArrayList<CellJDLV> groundForJDLV;
	private ArrayList<CellJDLV> deadlockGrounds;

	static int id = 0;

	public World() {

	}

	public ObjectGame getObject(int i, int j) {
		return world[i][j];
	}

	public void loadMatrix(File file) {
		try {
			System.out.println(file);
			FileReader filein = new FileReader(file);
			int next;
			int currentRow = 0, currentColumn = 0;
			BufferedReader b = new BufferedReader(filein);
			this.numberRow = Integer.parseInt(b.readLine());
			this.numberColumn = Integer.parseInt(b.readLine());

			world = new ObjectGame[numberRow][numberColumn];
			worldGround = new CellJDLV[numberRow][numberColumn];
			goals = new ArrayList<>();
			boxForJDLV = new ArrayList<>();
			goalForJDLV = new ArrayList<>();
			boxs = new ArrayList<Box>();
			groundForJDLV = new ArrayList<>();
			deadlockGrounds = new ArrayList<>();
			generatorPathPlayer = new GeneratorPathPlayer();

			do {
				next = b.read();
				if (next != -1 && currentRow<numberRow) {
					CellJDLV groundTmp = new CellJDLV(currentRow, currentColumn);
					char nextc = (char) next;
					switch (nextc) {
					case '0':
					case '2':
						world[currentRow][currentColumn] = null;
						worldGround[currentRow][currentColumn] = groundTmp;
						
						groundForJDLV.add(groundTmp);
						generatorPathPlayer.addVertex(groundTmp);
						break;
					case '1':
						world[currentRow][currentColumn] = new Wall(currentRow,	currentColumn);
						break;
					case '3':
						player = new Player(currentRow, currentColumn, this);
						playerJDLV = player.getCellJDLV();
						world[currentRow][currentColumn] = player;
						worldGround[currentRow][currentColumn] = groundTmp;

						groundForJDLV.add(groundTmp);
						generatorPathPlayer.addVertex(groundTmp);
						break;
					case '4':
						Box boxTmp = new Box(currentRow, currentColumn, this);
						boxs.add(boxTmp);
						world[currentRow][currentColumn] = boxTmp;
						worldGround[currentRow][currentColumn] = groundTmp;
						
						groundForJDLV.add(groundTmp);
						generatorPathPlayer.addVertex(groundTmp);
						break;
					case '5':
						goals.add(new Goal(currentRow, currentColumn));
						goalForJDLV.add(new CellJDLV(currentRow, currentColumn));
						world[currentRow][currentColumn] = goals.get(goals.size() - 1);
						worldGround[currentRow][currentColumn] = groundTmp;
						
						groundForJDLV.add(groundTmp);
						generatorPathPlayer.addVertex(groundTmp);
						break;
					case'7':
						world[currentRow][currentColumn] = null;

					}
					
					if (currentColumn == numberColumn) {
						currentRow++;
						currentColumn = 0;
					} else
						currentColumn++;
				}
			} while (next != -1);

			filein.close();
			b.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("File non esistente");
		}
		addEdges();
		System.out.println("VEDIAMOOOOOOO "+generatorPathPlayer.worldGraph.edgeSet().size());
		checkDeadlockGrounds();
	}

	private boolean isDeadlock(CellJDLV cell) {
		if (world[cell.getI() - 1][cell.getJ()] instanceof Wall
				&& world[cell.getI()][cell.getJ() - 1] instanceof Wall)
			return true;
		if (world[cell.getI() + 1][cell.getJ()] instanceof Wall
				&& world[cell.getI()][cell.getJ() - 1] instanceof Wall)
			return true;
		if (world[cell.getI() - 1][cell.getJ()] instanceof Wall
				&& world[cell.getI()][cell.getJ() + 1] instanceof Wall)
			return true;
		if (world[cell.getI() + 1][cell.getJ()] instanceof Wall
				&& world[cell.getI()][cell.getJ() + 1] instanceof Wall)
			return true;

		if (world[cell.getI() - 1][cell.getJ()] instanceof Wall
				|| world[cell.getI() + 1][cell.getJ()] instanceof Wall) {
			int i = cell.getI();
			
			boolean up = true;
			if(world[cell.getI() + 1][cell.getJ()] instanceof Wall)
				up= false;
			
			boolean rightWall = false;
			boolean leftWall = false;

			for (int j = cell.getJ(); j < numberColumn; j++) {
				if (world[i][j] instanceof Wall) {
					rightWall = true;
					break;
				} else if (world[i][j] instanceof Goal) 
					break;
				else if(up && !(world[i-1][j] instanceof Wall))
					break;
				else if(!up && !(world[i+1][j] instanceof Wall))
					break;
			}

			for (int j = cell.getJ(); j >= 0; j--) {
				if (world[i][j] instanceof Wall) {
					leftWall = true;
					break;
				} else if (world[i][j] instanceof Goal)
					break;
				else if(up && !(world[i-1][j] instanceof Wall))
					break;
				else if(!up && !(world[i+1][j] instanceof Wall))
					break;
			}

			if (rightWall && leftWall)
				return true;

		}

		if (world[cell.getI()][cell.getJ() - 1] instanceof Wall
				|| world[cell.getI()][cell.getJ() + 1] instanceof Wall) {
			int j = cell.getJ();

			boolean left = true;
			if(world[cell.getI()][cell.getJ()+1] instanceof Wall)
				left= false;
			
			boolean downWall = false;
			boolean upWall = false;

			for (int i = cell.getI(); i < numberRow; i++) {
				if (world[i][j] instanceof Wall) {
					downWall = true;
					break;
				} else if (world[i][j] instanceof Goal)
					break;
				else if(left && !(world[i][j-1] instanceof Wall))
					break;
				else if(!left && !(world[i][j+1] instanceof Wall))
					break;
			}

			for (int i = cell.getI(); i >= 0; i--) {
				if (world[i][j] instanceof Wall) {
					upWall = true;
					break;
				} else if (world[i][j] instanceof Goal)
					break;
				else if(left && !(world[i][j-1] instanceof Wall))
					break;
				else if(!left && !(world[i][j+1] instanceof Wall))
					break;
			}

			if (downWall && upWall)
				return true;

		}

		return false;
	}

	private void checkDeadlockGrounds() {
		for (CellJDLV ground : groundForJDLV) {
			if (!(world[ground.getI()][ground.getJ()] instanceof Goal)
					&& isDeadlock(ground))
				deadlockGrounds.add(ground);
		}
	}

	public void print() {
		for (int i = 0; i < numberRow; i++) {
			String row = "";
			for (int j = 0; j < numberColumn; j++) {
				if (world[i][j] != null)
					row += world[i][j].toString();
				else
					row += "    ";
				row += " ";
			}
			System.out.println(row);
		}

	}

	public boolean isThereGoal(int i, int j) {
		for (int z = 0; z < goals.size(); z++)
			if (goals.get(z).getI() == i && goals.get(z).getJ() == j)
				return true;
		return false;
	}

	public void movePlayer(int i1, int j1, int i2, int j2) {
		Player player = (Player) world[i1][j1];
		player.setCell(i2, j2);
		world[i2][j2] = player;
		world[i1][j1] = null;

	}

	public void moveBox(int i1, int j1, int i2, int j2) {
		Box box = (Box) world[i1][j1];

		box.setCell(i2, j2);
		world[i2][j2] = box;
		world[i1][j1] = null;

	}

	public void movePlayerAndBox(int i1, int j1, int i2, int j2, int i3, int j3) {
		moveBox(i2, j2, i3, j3);
		movePlayer(i1, j1, i2, j2);
	}

	public int getNumberRow() {
		return numberRow;
	}

	public int getNumberColumn() {
		return numberColumn;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public ArrayList<BoxJDLV> getBoxJDLV() {
		ArrayList<BoxJDLV> boxForJDLV = new ArrayList<>();
		for (int i = 0; i < boxs.size(); i++)
			boxForJDLV.add(new BoxJDLV(1, i, boxs.get(i).getI(), boxs.get(i)
					.getJ()));

		return boxForJDLV;
	}

	public CellJDLV getPlayerJDLV() {
		return playerJDLV;
	}

	public ArrayList<CellJDLV> getGoalsJDLV() {
		return goalForJDLV;
	}

	public ArrayList<CellJDLV> getGroundJDLV() {
		return groundForJDLV;
	}

	public ArrayList<CellJDLV> getDeadlockGroundJDLV() {
		return deadlockGrounds;
	}

	public boolean win() {
		int boxWin = 0;

		for (Box box : boxs)
			for (Goal goal : goals)
				if (box.getI() == goal.getI() && box.getJ() == goal.getJ()) {
					boxWin++;
					break;
				}

		if (boxWin == boxs.size())
			return true;
		return false;
	}

	public boolean isThereGround(int i, int j) {
		for (int z = 0; z < groundForJDLV.size(); z++)
			if (groundForJDLV.get(z).getI() == i && groundForJDLV.get(z).getJ() == j)
				return true;
		return false;
	}
	public GeneratorPathPlayer getGeneratorPathPlayer() {
		return generatorPathPlayer;
	}
	
	public	void addEdges(CellJDLV ground){
		if(worldGround[ground.getI()+1][ground.getJ()] instanceof CellJDLV){
			generatorPathPlayer.addEdge(worldGround[ground.getI()+1][ground.getJ()],ground);
			generatorPathPlayer.addEdge(ground,worldGround[ground.getI()+1][ground.getJ()]);			
		}
		if(worldGround[ground.getI()-1][ground.getJ()] instanceof CellJDLV){
			generatorPathPlayer.addEdge(ground, worldGround[ground.getI()-1][ground.getJ()]);
			generatorPathPlayer.addEdge( worldGround[ground.getI()-1][ground.getJ()],ground);
			
		}
		if(worldGround[ground.getI()][ground.getJ()+1] instanceof CellJDLV){
			generatorPathPlayer.addEdge(ground, worldGround[ground.getI()][ground.getJ()+1]);
			generatorPathPlayer.addEdge( worldGround[ground.getI()][ground.getJ()+1],ground);
			
		}
		if(worldGround[ground.getI()][ground.getJ()-1] instanceof CellJDLV){
			generatorPathPlayer.addEdge(ground, worldGround[ground.getI()][ground.getJ()-1]);
			generatorPathPlayer.addEdge( worldGround[ground.getI()][ground.getJ()-1],ground);
			
		}
	}
	
	private void addEdges(){
		
		print();
		for (CellJDLV ground : groundForJDLV) {
			
			if(worldGround[ground.getI()+1][ground.getJ()] instanceof CellJDLV)
				generatorPathPlayer.addEdge(ground, worldGround[ground.getI()+1][ground.getJ()]);
			if(worldGround[ground.getI()-1][ground.getJ()] instanceof CellJDLV)
				generatorPathPlayer.addEdge(ground, worldGround[ground.getI()-1][ground.getJ()]);
			if(worldGround[ground.getI()][ground.getJ()+1] instanceof CellJDLV)
				generatorPathPlayer.addEdge(ground, worldGround[ground.getI()][ground.getJ()+1]);
			if(worldGround[ground.getI()][ground.getJ()-1] instanceof CellJDLV)
				generatorPathPlayer.addEdge(ground, worldGround[ground.getI()][ground.getJ()-1]);
		}
	}
	
	public CellJDLV getWorldGround(int i, int j) {
		return worldGround[i][j];
	}
	
	public ObjectGame getObjectGame(int i, int j){
		return world[i][j];
	}
	
}
