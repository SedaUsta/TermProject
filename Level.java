import java.io.File;
import java.util.ArrayList;
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
import javafx.scene.shape.Shape;


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
	
	
	
	//in order to calculate path length
	private ArrayList<Double> firstXPositionLists = new ArrayList<Double>();
	private ArrayList<Double> firstYPositionLists= new ArrayList<Double>();
	private ArrayList<Double> XPositionLists = new ArrayList<Double>();
	private ArrayList<Double> YPositionLists= new ArrayList<Double>();
	private ArrayList<Double> pathLengths= new ArrayList<Double>();
	
	
	//add these in order to classify traffic lights
	private ArrayList<ObservableList<TrafficLight>> trafficLightsLists= new ArrayList<ObservableList<TrafficLight>>();
	

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
					
					
					//create pathLengths list
					for(int i=0;i<this.numberOfPaths;i++) {
						pathLengths.add((double)0);
					 }
					
					

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
					
					
					
					firstXPositionLists.add(Integer.parseInt(array[1]),x);
					firstYPositionLists.add(Integer.parseInt(array[1]),y);
					
					XPositionLists.add(Integer.parseInt(array[1]),x);
					YPositionLists.add(Integer.parseInt(array[1]),y);

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
					//to determine total lengths of paths
					double x1 = XPositionLists.get(Integer.parseInt(array[1]));
					double y1 = YPositionLists.get(Integer.parseInt(array[1]));
					double totalLength=0;
					if(!(pathLengths.isEmpty())) {
					totalLength+=pathLengths.get(Integer.parseInt(array[1]));
					pathLengths.remove(Integer.parseInt(array[1]));
					}
					totalLength+=Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
					pathLengths.add(Integer.parseInt(array[1]),totalLength);
					
				}

			}
			scene = new Scene(pane, width, height);
		} catch (Exception e) {

		}
		
		
		
		groupTrafficLigths();

		 textString = "Score: " + score + "/" + numberOfCars + "\nCrashes: " + crashes + "/" + allowedAccidents;
		 text1 = new Text(width / 400, height / 40, textString);
		text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, height / 40));
		pane.getChildren().add(text1);

	}
	
	public void groupTrafficLigths() {
		for(int i=0;i<this.numberOfPaths;i++) {
			trafficLightsLists.add(FXCollections.observableArrayList());
		 }
		//this.paths;
		//this.trafficLights
		for(int i=0;i<trafficLights.size();i++) {
			for(int j=0;j<paths.size();j++) {
			
			
			Shape intersection = Shape.intersect(trafficLights.get(i).getCircle(), paths.get(j));
	       
	    
			
			if(intersection.getBoundsInLocal().getWidth() != -1) {
				System.out.println("trafficLights["+i+"] is intersects with path["+j+"]");
				//System.out.println("path("+j+") bounds in parent: "+paths.get(j).getBoundsInParent());
				//System.out.println("traffic light("+i+") bounds in parent: "+trafficLights.get(i).getCircle().getBoundsInLocal());
				trafficLightsLists.get(j).add(trafficLights.get(i));
				
				break;
			}
			}
			
			
		}
		
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
	public ArrayList<Double> getPathLengths(){
		return pathLengths;
	}
	public ArrayList<Double> getPathBeginPointsX(){
		return this.firstXPositionLists;
	}
	public ArrayList<Double> getPathBeginPointsY(){
		return this.firstYPositionLists;
	}
	public ArrayList<ObservableList<TrafficLight>> getGroupedTrafficLightsList(){
		return trafficLightsLists;
	}
}

