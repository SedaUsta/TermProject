import java.io.File;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
//import javafx.util.Duration;
import javafx.animation.PathTransition;
//import javafx.animation.Timeline;



public class Level {
	private ObservableList<TrafficLight> trafficLights;
	private ObservableList<Building> buildings;
	private ObservableList<RoadTile> roadTiles;
	private ObservableList<Path> paths;
	private ObservableList<PathTransition> pathTransitions;
	//private int pathLengths;

	private Scene scene;
	private Pane pane;

	private double width;
	private double height;
	private int xGridCells;
	private int yGridCells;
	private int numberOfPaths;

	private int numberOfCars;
	private int score;
	private int allowedAccidents;
	private int crashes;
	
	String textString ;
	Text text1;

	public Level(String name) {
		pane = new Pane();

		trafficLights = FXCollections.observableArrayList();
		buildings = FXCollections.observableArrayList();
		roadTiles = FXCollections.observableArrayList();
		paths = FXCollections.observableArrayList();

		File file = new File(name);
		String content = "";
		try (Scanner input = new Scanner(file)) {
			while (input.hasNextLine()) {
				content = input.nextLine();
				String[] array = content.split(" ");

				if (array[0].equalsIgnoreCase("Metadata")) {
					width = Double.parseDouble(array[1]);
					height = Double.parseDouble(array[2]);
					xGridCells = Integer.parseInt(array[3]);
					yGridCells = Integer.parseInt(array[4]);
					numberOfPaths = Integer.parseInt(array[5]);
					numberOfCars = Integer.parseInt(array[6]);
					allowedAccidents = Integer.parseInt(array[7]);

					double gridWidth = width / xGridCells;
					double gridHeight = height / yGridCells;

					for (int i = 0; i < yGridCells; i++) {
						for (int j = 0; j < xGridCells; j++) {
							Rectangle gridCell = new Rectangle(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
							gridCell.setStroke(Color.BLACK);
							gridCell.setFill(Color.MEDIUMTURQUOISE);

							pane.getChildren().add(gridCell);
						}
					}
				}

				else if (array[0].equalsIgnoreCase("Building")) {
					int type = Integer.parseInt(array[1]);
					int rotation = Integer.parseInt(array[2]);
					int color = Integer.parseInt(array[3]);
					int xCell = Integer.parseInt(array[4]);
					int yCell = Integer.parseInt(array[5]);
					Building building = new Building(type, rotation, color, xCell, yCell);
					buildings.add(building);
					pane.getChildren().add(building.getBuilding(width / xGridCells, height / yGridCells));
				}

				else if (array[0].equalsIgnoreCase("RoadTile")) {
					int type = Integer.parseInt(array[1]);
					int rotation = Integer.parseInt(array[2]);
					int xCell = Integer.parseInt(array[3]);
					int yCell = Integer.parseInt(array[4]);
					RoadTile roadTile = new RoadTile(type, rotation, xCell, yCell, width / xGridCells,
							height / yGridCells);
					pane.getChildren().add(roadTile.getPane());
					roadTiles.add(roadTile);
				}

				else if (array[0].equalsIgnoreCase("TrafficLight")) {
					double x1 = Double.parseDouble(array[1]);
					double y1 = Double.parseDouble(array[2]);
					double x2 = Double.parseDouble(array[3]);
					double y2 = Double.parseDouble(array[4]);

					TrafficLight trafficLight = new TrafficLight(x1, y1, x2, y2);
					trafficLight.getCircle().setOnMouseClicked(event -> {
						trafficLight.setState();
					});
					pane.getChildren().addAll(trafficLight.getLine(), trafficLight.getCircle());
					trafficLights.add(trafficLight);
				}

				else if (array[0].equalsIgnoreCase("Path") && array[2].equalsIgnoreCase("MoveTo")) {
					double x = Double.parseDouble(array[3]);
					double y = Double.parseDouble(array[4]);

					Path path = new Path();
					
					/*PathTransition pt = new PathTransition();
					pt.setDuration(Duration.millis(4000));
				    pt.setPath(path);*/
					
					MoveTo moveTo = new MoveTo();
					moveTo.setX(x);
					moveTo.setY(y);
					path.getElements().add(moveTo);

					paths.add(Integer.parseInt(array[1]), path);
					//pathTransitions.add(Integer.parseInt(array[1]), pt);
					
					/*pt.setCycleCount(Timeline.INDEFINITE);
				    pt.setAutoReverse(false);*/
					
					//this.numberOfPaths++;
				}

				else if (array[0].equalsIgnoreCase("Path") && array[2].equalsIgnoreCase("LineTo")) {
					double x = Double.parseDouble(array[3]);
					double y = Double.parseDouble(array[4]);

					LineTo lineTo = new LineTo();
					lineTo.setX(x);
					lineTo.setY(y);
					paths.get(Integer.parseInt(array[1])).getElements().add(lineTo);
					//int lastIndex = paths.get(Integer.parseInt(array[1])).getElements().lastIndexOf(lineTo);
					
					
					
				}

			}
			scene = new Scene(pane, width, height);
		} catch (Exception e) {

		}

		 textString = "Score: " + score + "/" + numberOfCars + "\nCrashes: " + crashes + "/" + allowedAccidents;
		 text1 = new Text(width / 400, height / 40, textString);
		text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, height / 40));
		pane.getChildren().add(text1);

	}

	public Scene getScene() {
		return scene;
	}
	public void removeText() {
		pane.getChildren().remove(text1);
	}
	public void setText() {
		 textString = "Score: " + score + "/" + numberOfCars + "\nCrashes: " + crashes + "/" + allowedAccidents;
		 text1 = new Text(width / 400, height / 40, textString);
		text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, height / 40));
		pane.getChildren().add(text1);
	}
	
	public int getNumberOfPaths() {
		return numberOfPaths;
	}
	
	public ObservableList<Path> getPaths(){
		return this.paths;
	}
	public ObservableList<PathTransition> getPathTransitions(){
		return this.pathTransitions;
	}
	public ObservableList<TrafficLight> getTrafficLights(){
		return this.trafficLights;
	}
	public Pane getPane() {
		return this.pane;
	}
	public void setCrushes(int s) {
		this.crashes=s;
	}
	public int getCrushes () {
		return crashes;
	}
	public void upCrashes() {
		crashes++;
	}
	public void upScore() {
		this.score++;
	}
	public int getScores() {
		return score;
	}
	public int getNumberOfCars() {
		return this.numberOfCars;
	}
	public int getAllowedAccidents() {
		return this.allowedAccidents;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
}
