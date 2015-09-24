package gui;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Box;
import logic.Goal;
import logic.ObjectGame;
import logic.Player;
import logic.Wall;

public class ImageManager {

	private HashMap<String, Image> images;
	private int height =47;
	private int width = 50;
	
	public ImageManager() {
		images = new HashMap<>();
		loadImage();
	}
	
	public Image getImage(String name){
		return images.get(name);
	}
	
	private void loadImage(){
		images.put("ground",new Image("file:image/grass.png",width, 100,false,false));
		images.put("wall",new Image("file:image/wallBig.png",width, 100,false,false));
		images.put("goal",new Image("file:image/goal.png",width, 70,false,false));
		images.put("box",new Image("file:image/box.png",width, 70,false,false));
		images.put("player",new Image("file:image/player.png",width, 80,false,false));
	}
	public ImageView drawGround(int i, int j){
		ImageView view = new ImageView();
		view.setImage(images.get("ground"));
		view.setLayoutX(j*width);
		view.setLayoutY(i*height);
		return view;
	}
	public ImageView drawImage(ObjectGame objectGame){
		ImageView view = new ImageView();
		
		if (objectGame instanceof Player) {
			
			view.setImage(images.get("player"));
			view.setLayoutX(objectGame.getJ()*width);
			view.setLayoutY(objectGame.getI()*height);
		}
		if (objectGame instanceof Goal) {

			view.setImage(images.get("goal"));
			view.setLayoutX(objectGame.getJ()*width);
			view.setLayoutY(objectGame.getI()*height);
		}
		if (objectGame instanceof Box) {

			view.setImage(images.get("box"));
			view.setLayoutX(objectGame.getJ()*width);
			view.setLayoutY(objectGame.getI()*height);
		}
		if (objectGame instanceof Wall) {

			view.setImage(images.get("wall"));
			view.setLayoutX(objectGame.getJ()*width);
			view.setLayoutY(objectGame.getI()*height);
		}
		return view;
	}

	public ImageView drawGoal(int i, int j) {
		ImageView view = new ImageView();
		view.setImage(images.get("goal"));
		view.setLayoutX(j*width);
		view.setLayoutY(i*height);
		return view;

	}

}
