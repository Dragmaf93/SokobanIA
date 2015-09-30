package gui;
import org.jgrapht.experimental.alg.color.GreedyColoring;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Player;
import jdlv.SokobanJDLV;

public class SokobanGUI extends Application {

	private double height = 500, width = 900;
	private MainPane mainPane;
	private boolean firstTime=true;
	private GridPane grid;
	private SokobanJDLV sokobanJDLV;

	private GridPane gridForButton;
	
	private Levels levels;
	private ChoiceBox<String> levelBox;
	private String levelChoised;
	private Button reset;
	private Label stepDone;
	private ImageView step;
	private Button resolver;
	protected Button stepNext;
	
	public SokobanGUI() {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER_LEFT);
		
		grid.setBackground(new Background(new BackgroundImage(
				new Image(
				"file:image/sky.jpg",width,height,false,false), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		createGridForButton();
		levels = new Levels();
		
		mainPane = new MainPane();
		mainPane.setBackground(Background.EMPTY);
		levelChoised="level1";
		mainPane.loadLevel(levels.getLevel(levelChoised) );
		grid.add(mainPane,10,0);

		step = new ImageView("file:image/step.png");
		stepDone = new Label(" "+mainPane.getStep());
		stepDone.setGraphic(step);
		stepDone.setFont(new Font("Comic Sans MS", 17));
		stepDone.setTextFill(Color.web("277ce5"));
		grid.add(stepDone,10,1);
		
		levelBox = new ChoiceBox<>(FXCollections.observableArrayList(levels.getLevelsName()));
		levelBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number oldValue, Number newValue) {		
				
				levelChoised=levels.getLevel(newValue.intValue());
//				mainPane.loadLevel(levels.getLevel(levelChoised));
				reset();
			}
			
		});
		
		gridForButton.add(levelBox,25,3);
		Scene scene = new Scene(grid, width, height);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);

		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				if(key.getCode()==KeyCode.RIGHT){
					mainPane.movePlayer(Player.RIGHT);
					firstTime = true;
				}else if(key.getCode()==KeyCode.LEFT){
					mainPane.movePlayer(Player.LEFT);
					firstTime=true;
				}else if(key.getCode()==KeyCode.DOWN){
					mainPane.movePlayer(Player.DOWN);
					firstTime=true;
			}else if(key.getCode()==KeyCode.UP){
					mainPane.movePlayer(Player.UP);
					firstTime=true;
			}
//			else if(key.getCode()==KeyCode.SPACE){
//					if(firstTime){
//						sokobanJDLV.generaPercorso();
//						firstTime=false;
//					}
//					int step =sokobanJDLV.nextStep();
//					if(step!=-1)
//						mainPane.movePlayer(step);
//				}
			}
		});
		
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	SokobanGUI.this.width = newSceneWidth.doubleValue();
		    }
		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	SokobanGUI.this.height = newSceneHeight.doubleValue();
		    }
		});

		primaryStage.show();
		
		sokobanJDLV = new SokobanJDLV(mainPane.getWorld());
		
		new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				mainPane.drawLevel();
				
				grid.setBackground(new Background(new BackgroundImage(
						new Image(
						"file:image/sky.jpg",width,height,false,false), BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			
				stepDone.setText(String.valueOf(mainPane.getStep()));
				
				if (mainPane.getWin())
					mainPane.addWin();
				
			}
		}.start();
		
	}
	
	private void createGridForButton()
	{
		gridForButton = new GridPane();
		gridForButton.setHgap(5);
		gridForButton.setVgap(10);
		gridForButton.setAlignment(Pos.CENTER);
		
		reset = new Button();
		reset.setGraphic(new ImageView("file:image/reset.png"));
		reset.setOpacity(0.5);
		
		reset.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				reset();
			}
			
		});
		
		gridForButton.add(reset, 25, 0);
		
		resolver = new Button("Resolve with DLV");
		resolver.setOpacity(0.5);
		resolver.setFont(new Font("Comic Sans MS", 18));
		resolver.setTextFill(Color.web("277ce5"));
		
		resolver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(firstTime){
					sokobanJDLV.solver();
					firstTime=false;
				}
//				int step =sokobanJDLV.nextStep();
//				if(step!=-1)
//					mainPane.movePlayer(step);
				
				generateButtonNext();
			}
			
		});
		
		gridForButton.add(resolver, 25, 1);
		
		grid.add(gridForButton, 11, 0);
	}
	
	private void reset(){
		mainPane.removeLevel();
		mainPane.loadLevel(levels.getLevel(levelChoised));
		firstTime = true;
		gridForButton.getChildren().remove(stepNext);
		sokobanJDLV.setIndex();
	}
	private void generateButtonNext()
	{
		stepNext = new Button();
		stepNext.setGraphic(new ImageView("file:image/nextStep.png"));
		stepNext.setOpacity(0.5);
		stepNext.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int step = sokobanJDLV.nextStep();
				if(step != -1)
					mainPane.movePlayer(step);
				
			}
			
		});
		gridForButton.add(stepNext, 25, 2);
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
