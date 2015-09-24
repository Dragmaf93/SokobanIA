package gui;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jdlv.CellJDLV;
import logic.Goal;
import logic.Player;
import logic.World;

public class MainPane extends Pane {

	private World world;
	private Player player;
	private ImageManager imageManager;
	private boolean winner;
	private ImageView win;
	private int step = 0;
	
	public MainPane() {
		world = new World();
		imageManager = new ImageManager();
		winner = false;
		win = new ImageView("file:image/win2.png");
	}
	
	public void loadLevel(String level)
	{
		world.loadMatrix("./level/"+level);

		player = world.getPlayer();
		drawLevel();
	}
	
	public void drawLevel(){
//		ArrayList<CellJDLV> grounds = world.getGroundJDLV();
//		ArrayList<Goal> goals = world.getGoals();
//		for (CellJDLV cellJDLV : grounds) 
//			this.getChildren().add(imageManager.drawGround(cellJDLV.getI(), cellJDLV.getJ()));
		
		for (int i = 0; i < world.getNumberRow(); i++) {
			for (int j = 0; j < world.getNumberColumn(); j++) {
				if(world.isThereGround(i,j))
					this.getChildren().add(imageManager.drawGround(i, j));
				if(world.isThereGoal(i, j))
					this.getChildren().add(imageManager.drawGoal(i, j));
				this.getChildren().add(imageManager.drawImage(world.getObject(i, j)));
			}
		}
		
		
	}

	public void removeLevel()
	{
		for (int i = 0; i < world.getNumberRow(); i++) {
			for (int j = 0; j < world.getNumberColumn(); j++) {
				this.getChildren().remove(imageManager.drawGround(i, j));
				if(world.isThereGoal(i, j))
					this.getChildren().remove(imageManager.drawGoal(i, j));
				this.getChildren().remove(imageManager.drawImage(world.getObject(i, j)));
			}
		}
		
		winner = false;
		step = 0;
		this.getChildren().remove(win);
	}
	
	public void movePlayer(int direction) {
		if (!winner)
		{
			player.move(direction);
			step++;
			if (world.win())
			{
				winner = true;
				return;
			}
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public void addWin()
	{
		this.getChildren().remove(win);
		this.getChildren().add(win);
	}
	
	public boolean getWin() {
		return winner;
	}
	
	public int getStep()
	{
		return step;
	}
}
