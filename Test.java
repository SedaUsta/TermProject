import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import java.util.*;
import javafx.geometry.Bounds;


public class Test extends Application {
	
	private double safeCrushTime=200;
	private double safeDistance = 30;
	private double safeLightDistance = 20;
	private double crushDistance=20;
	private double carSpeed=100;
	
	private double time=0;
	private Level level;
	private int levelCounter = 1;
	
	private ArrayList<ObservableList<Car>> carLists; 
	private ArrayList<ArrayList<Double>> carXPositionLists; 
	private ArrayList<ArrayList<Double>> carYPositionLists; 
	private ArrayList<ObservableList<TrafficLight>> trafficLightLists;
	//stages(only a single stage) and scenes we use
	private Stage stage;
	
	private Scene mainMenu;
	private Pane paneMainMenu;
	private Button buttonStart;
	
	private Scene gameOver;
	private Pane paneGameOver;
	private Button buttonYes;
	private Button buttonNo;
	
	private Scene youWin;
	private Pane paneYouWin;
	private Button buttonMainMenu;
	
	private Scene nextLevel;
	private Pane paneNextLevel;
	private Button buttonNextLevel;
	private Button buttonMainMenuforNextLevel;
	

	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		//creating the scenes
		mainMenu = createMainMenu();
		gameOver = createGameOver();
		youWin = createYouWin();
		nextLevel = createNextLevel();
		
		Image icon = new Image("icon.jpg");
		stage.getIcons().add(icon);
		
		primaryStage.setTitle("Traffic Game");

		primaryStage.setScene(mainMenu);
		primaryStage.show();
		
	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}
	
	public Scene createMainMenu() {
		
		
		paneMainMenu = new Pane();
		mainMenu = new Scene(paneMainMenu,1380,460);
		
		
		Image image = new Image("Entrance1.jpg");
		
		//create background
 		BackgroundFill backgroundFill = new BackgroundFill(Color.LAVENDER, new CornerRadii(0), new Insets(10));
 		Background background = new Background(backgroundFill);
 		//set the background
 		paneMainMenu.setBackground(background);
		
		
		ImageView entrance = new ImageView(image);
		entrance.setFitHeight(460);
		entrance.setFitWidth(1380);
		//binding entrance property so it will always stay at the center when window resized 
		entrance.layoutXProperty().bind(mainMenu.widthProperty().subtract(entrance.prefWidth(-1)).divide(2));
		entrance.layoutYProperty().bind(mainMenu.heightProperty().subtract(entrance.prefHeight(-1)).divide(2));
		paneMainMenu.getChildren().add(entrance);
		
		
		//creating, designing and setting the start button
		this.buttonStart = new Button("Start Game");
		buttonStart.layoutXProperty().bind(mainMenu.widthProperty().subtract(buttonStart.prefWidth(-1)).divide(2).subtract(40));
		buttonStart.layoutYProperty().bind(mainMenu.heightProperty().subtract(buttonStart.prefHeight(-1)).divide(2).subtract(30));
		buttonStart.setMinHeight(50);
		buttonStart.setMinWidth(80);
		buttonStart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		buttonStart.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
		buttonStart.setOnAction(e -> {

			String s = "level" + levelCounter + ".txt";
			
			
            //create a new level object with specified file name
			level = new Level(s);
			
			
			//Initialize array lists
			this.carLists= new ArrayList<ObservableList<Car>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carLists.add(FXCollections.observableArrayList());
		 }
		 this.carXPositionLists= new ArrayList<ArrayList<Double>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carXPositionLists.add(new ArrayList<Double>());
		 }
		 this.carYPositionLists= new ArrayList<ArrayList<Double>> () ;
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carYPositionLists.add(new ArrayList<Double>());
		 }
		 this.trafficLightLists= new ArrayList<ObservableList<TrafficLight>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 trafficLightLists.add(FXCollections.observableArrayList());
		 }
		
		 //set the scene
		 switchScenes(level.getScene());
			//starting the game
			 gameplay();
			 
			 
		});
		
		paneMainMenu.getChildren().add(buttonStart);
		return mainMenu;
	}
	
	public Scene createYouWin() {
		
		paneYouWin = new Pane();
		youWin = new Scene(paneYouWin,1200,600);
		
	   buttonMainMenu = new Button("Main Menu");
	   buttonMainMenu.setOnAction(e->switchScenes(mainMenu));
	   paneYouWin.getChildren().add(buttonMainMenu);
	   
	   buttonMainMenu.layoutXProperty().bind(youWin.widthProperty().subtract(buttonMainMenu.prefWidth(-1)).divide(2).subtract(80));
	   buttonMainMenu.layoutYProperty().bind(youWin.heightProperty().subtract(buttonMainMenu.prefHeight(-1)).divide(2).add(100));
	   buttonMainMenu.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
	   buttonMainMenu.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
	   
	 //create background
	 		BackgroundFill backgroundFill = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(10));
	 		Background background = new Background(backgroundFill);
	 		//set the background
	 		paneYouWin.setBackground(background);
	 		// create the texts
	 		Text youWinText = new Text("YOU WIN!");
	 		youWinText.setFont(Font.font("black", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 150));
	 		youWinText.setFill(Color.DEEPSKYBLUE);
	 	    // set the texts
	 		paneYouWin.getChildren().add(youWinText);
	 		//adjust the text automatically when resizing the window
	 		youWinText.layoutXProperty().bind(youWin.widthProperty().subtract(youWinText.prefWidth(-1)).divide(2));
	 		youWinText.layoutYProperty().bind(youWin.heightProperty().subtract(youWinText.prefHeight(-1)).divide(2));
		
		return youWin;
	}
	public Scene createNextLevel() {
		
		paneNextLevel = new Pane();
		nextLevel = new Scene(paneNextLevel,1200,600);
		
		buttonNextLevel = new Button("Next Level");
		buttonNextLevel.setOnAction(e-> {
		
		String s = "level" + levelCounter + ".txt";

		level = new Level(s);
		
		this.carLists= new ArrayList<ObservableList<Car>>();
	 for(int i=0;i<level.getNumberOfPaths();i++) {
		 carLists.add(FXCollections.observableArrayList());
	 }
	 this.carXPositionLists= new ArrayList<ArrayList<Double>>();
	 for(int i=0;i<level.getNumberOfPaths();i++) {
		 carXPositionLists.add(new ArrayList<Double>());
	 }
	 this.carYPositionLists= new ArrayList<ArrayList<Double>> () ;
	 for(int i=0;i<level.getNumberOfPaths();i++) {
		 carYPositionLists.add(new ArrayList<Double>());
	 }
	 this.trafficLightLists= new ArrayList<ObservableList<TrafficLight>>();
	 for(int i=0;i<level.getNumberOfPaths();i++) {
		 trafficLightLists.add(FXCollections.observableArrayList());
	 }
		
		
		switchScenes(level.getScene());
		gameplay();
		});
		paneNextLevel.getChildren().add(buttonNextLevel);
		
		buttonNextLevel.layoutXProperty().bind(nextLevel.widthProperty().subtract(buttonNextLevel.prefWidth(-1)).divide(2).add(80));
		buttonNextLevel.layoutYProperty().bind(nextLevel.heightProperty().subtract(buttonNextLevel.prefHeight(-1)).divide(2).add(100));
		buttonNextLevel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		buttonNextLevel.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
		
		
		buttonMainMenuforNextLevel = new Button("Main Menu");
		buttonMainMenuforNextLevel.setOnAction(e->switchScenes(mainMenu));
		paneNextLevel.getChildren().add(buttonMainMenuforNextLevel);
		
		buttonMainMenuforNextLevel.layoutXProperty().bind(nextLevel.widthProperty().subtract(buttonMainMenuforNextLevel.prefWidth(-1)).divide(2).subtract(80));
		buttonMainMenuforNextLevel.layoutYProperty().bind(nextLevel.heightProperty().subtract(buttonMainMenuforNextLevel.prefHeight(-1)).divide(2).add(100));
		buttonMainMenuforNextLevel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		buttonMainMenuforNextLevel.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
		
		
		//create background
 		BackgroundFill backgroundFill = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(10));
 		Background background = new Background(backgroundFill);
 		//set the background
 		paneNextLevel.setBackground(background);
 		// create the texts
 		Text nextLevelText = new Text("NEXT LEVEL");
 		nextLevelText.setFont(Font.font("black", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 150));
 		nextLevelText.setFill(Color.DEEPSKYBLUE);
 	    // set the texts
 		paneNextLevel.getChildren().add(nextLevelText);
 		//adjust the text automatically when resizing the window
 		nextLevelText.layoutXProperty().bind(nextLevel.widthProperty().subtract(nextLevelText.prefWidth(-1)).divide(2));
 		nextLevelText.layoutYProperty().bind(nextLevel.heightProperty().subtract(nextLevelText.prefHeight(-1)).divide(2));
		
		
		return nextLevel;
	}

	
	public Scene createGameOver() {

		paneGameOver = new Pane();
        gameOver = new Scene(paneGameOver, 1200, 600);
		//create background
		BackgroundFill backgroundFill = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(10));
		Background background = new Background(backgroundFill);
		//set the background
		paneGameOver.setBackground(background);
		// create the texts
		Text gameOverText = new Text("GAME OVER");
		gameOverText.setFont(Font.font("grey", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 150));
		gameOverText.setFill(Color.DEEPSKYBLUE);
		
		Text wannaRetryText = new Text("Do you want to retry?");
		wannaRetryText.setFont(Font.font("black", FontWeight.THIN, FontPosture.REGULAR, 15));
		wannaRetryText.setFill(Color.DARKBLUE);
		
		// set the texts
		paneGameOver.getChildren().add(gameOverText);
		paneGameOver.getChildren().add(wannaRetryText);
		//adjust the text automatically when resizing the window
		gameOverText.layoutXProperty().bind(gameOver.widthProperty().subtract(gameOverText.prefWidth(-1)).divide(2));
		gameOverText.layoutYProperty().bind(gameOver.heightProperty().subtract(gameOverText.prefHeight(-1)).divide(2));
		wannaRetryText.layoutXProperty().bind(gameOver.widthProperty().subtract(wannaRetryText.prefWidth(-1)).divide(2));
		wannaRetryText.layoutYProperty().bind(gameOver.heightProperty().subtract(wannaRetryText.prefHeight(-1)).divide(2).add(120));
		
		// return main menu and start over button 
		buttonYes = new Button("Yes");
		buttonYes.setOnAction(e->{
			String s = "level" + levelCounter + ".txt";

			level = new Level(s);
			
			this.carLists= new ArrayList<ObservableList<Car>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carLists.add(FXCollections.observableArrayList());
		 }
		 this.carXPositionLists= new ArrayList<ArrayList<Double>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carXPositionLists.add(new ArrayList<Double>());
		 }
		 this.carYPositionLists= new ArrayList<ArrayList<Double>> () ;
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 carYPositionLists.add(new ArrayList<Double>());
		 }
		 this.trafficLightLists= new ArrayList<ObservableList<TrafficLight>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 trafficLightLists.add(FXCollections.observableArrayList());
		 }
			
			switchScenes(level.getScene());
			gameplay();	
		});
		
		buttonYes.layoutXProperty().bind(gameOver.widthProperty().subtract(buttonYes.prefWidth(-1)).divide(2).subtract(50));
		buttonYes.layoutYProperty().bind(gameOver.heightProperty().subtract(buttonYes.prefHeight(-1)).divide(2).add(140));
		buttonYes.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		buttonYes.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
		
		buttonNo = new Button("No");
		buttonNo.setOnAction(e->switchScenes(mainMenu));
		
		buttonNo.layoutXProperty().bind(gameOver.widthProperty().subtract(buttonNo.prefWidth(-1)).divide(2).add(20));
		buttonNo.layoutYProperty().bind(gameOver.heightProperty().subtract(buttonNo.prefHeight(-1)).divide(2).add(140));
		buttonNo.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		buttonNo.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");
		
		
		// add the buttons
		paneGameOver.getChildren().add(buttonYes);
		paneGameOver.getChildren().add(buttonNo);

		return gameOver;

	}

	public void gameplay() {

		
		createTraffic();

	}

	

	private void createTraffic() {
		
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				gameEnding();
			}
		
		//check the scores and decide win and lose
		public void gameEnding() {
			if(level.getScore()==level.getWinScore()) {
				stop();
				carLists.clear();
				carXPositionLists.clear(); 
				carYPositionLists.clear();
				trafficLightLists.clear();
				level.setScore(0);
				level.setCrashes(0);
				if(levelCounter==5) {
					levelCounter=1;
					switchScenes(youWin) ;
				}else {
					levelCounter++;
					switchScenes(nextLevel); 
				}
				
				
			}else if (level.getCrashes()==level.getAllowedAccidents()) {
			    stop();
			    carLists.clear();
				carXPositionLists.clear(); 
				carYPositionLists.clear();
				trafficLightLists.clear();
				level.setScore(0);
				level.setCrashes(0);
				levelCounter =1;
				switchScenes(gameOver) ;
				
			}
		}	
			
			
		};
		timer.start();
		
		
		
	}
	
	private void update() {
		
		time += 0.16; 
		//Implement the logic for cars checking other cars or lights here 
		    
		
		// car movement - turning
		setRotateAll();
		
		
		// check if there are crushes first
		checkCrashes();
		
		
		// check cars in crash statement
		checkCarsInCrashState();
		
		
		//check if they need to move
		setOnMoving();
		
		//check if they need to stop
		//check traffic light
		checkForTrafficLights();
		
		 
		//check the front car
		checkFrontCar();
		
		//check the other cars for a single car
		checkOtherCars();
		
		
		
		
		//update the x y locations of cars (it must be the last)
		uptadeXYLocations();
		
		
		
		if(time > 3) { 
		   if(Math.random() < 0.3) { 
			
		    spawnCar(); 
		    } 
		    time = 0; 
		  } 
		
		
	  }
	
	
	
	public void setOnMoving() {
		for(int i=0;i<carLists.size();i++) {
			//check if the list is empty
		if(!(carLists.get(i).isEmpty())) {	
			if(carLists.get(i).get(0).getState().equals("stopped")) {
				//check the first car on the path
				boolean trafficLightDetectedWithinASafeDistance = false;
							 if(!(level.getGroupedTrafficLightsList().get(i).isEmpty())) {
								 
							 for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
								//check the distance between the car and the light
									Bounds bounds = carLists.get(i).get(0).getCar().getBoundsInLocal();
							        Bounds screenBounds = carLists.get(i).get(0).getCar().localToScene(bounds);
							        double x1 =  screenBounds.getCenterX();
							        double y1 =  screenBounds.getCenterY();
							        
							        
							        
							        Bounds bounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().getBoundsInLocal();
							        Bounds screenBounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().localToScene(bounds1);
							        double x2 =  screenBounds1.getCenterX();
							        double y2 =  screenBounds1.getCenterY();
							        
							        
							        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
							        
							        if(distance<this.safeLightDistance) {
							        	trafficLightDetectedWithinASafeDistance=true;//if there is a traffic light infront of a car, keep that information 
									
						            if(level.getGroupedTrafficLightsList().get(i).get(k).getState()) {//check the state of the light, if its green go
						            	//System.out.println("Path ["+i+"] and its "+k+". traffic light is setted to green and 0. car is to go, distance:"+distance);
								carLists.get(i).get(0).setState("moving");
								carLists.get(i).get(0).getPt().play();
								
									break;
									
								} else if(!(level.getGroupedTrafficLightsList().get(i).get(k).getState())) {//if the state of the light is red set rotation 
									//System.out.println("Breaking the loop in order maintain the pause of path ["+i+"] and its ["+0+".] car, at "+k+". light. Distance is:"+distance);
									carLists.get(i).get(0).setRotate(carLists.get(i).get(0).getRotation());
									break;
								}else {
									carLists.get(i).get(0).setRotate(carLists.get(i).get(0).getRotation());
									
								}
						 }
					}		
							
							} else {//start moving if there is no traffic light on the path and 0. car stopped because of a crash
								carLists.get(i).get(0).setState("moving");
								carLists.get(i).get(0).getPt().play();
							}
							 if(!(trafficLightDetectedWithinASafeDistance)) {//if the car didn't start to move but there is no traffic light infront of it, start moving
								 
								 if(carLists.get(i).get(0).getState().equals("stopped")) {
										//System.out.println("Path ["+i+"] and its "+0+". car is to go because there are no traffic lights");
										carLists.get(i).get(0).setState("moving");
										carLists.get(i).get(0).getPt().play();
									}
								 
							 }
							
				}
			}			
			for (int j=1;j<carLists.get(i).size();j++) {//check the other car with same logic but also check if the car infront of them is moving 
						
						//check the front car
						if(carLists.get(i).get(j).getState().equals("stopped")) {
							
							
								//check the distance between the car and the light
									Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
							        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
							        double x1 =  screenBounds.getCenterX();
							        double y1 =  screenBounds.getCenterY();
							     
							        boolean trafficLightDetectedWithinASafeDistance=false;
							        
								if(!(level.getGroupedTrafficLightsList().get(i).isEmpty())) {
								for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
									
							        Bounds bounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().getBoundsInLocal();
							        Bounds screenBounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().localToScene(bounds1);
							        double x2 =  screenBounds1.getCenterX();
							        double y2 =  screenBounds1.getCenterY();
							        
							        
							        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
							        
									if(distance<this.safeLightDistance) {
										
										trafficLightDetectedWithinASafeDistance=true;// set it true if there is a light infront of the car
										
										if(level.getGroupedTrafficLightsList().get(i).get(k).getState()) {// if the light is green,go
											//System.out.println("Path ["+i+"] and its "+k+". traffic light is setted to green and "+j+". car is to go, distance:"+distance);
											carLists.get(i).get(j).setState("moving");
											carLists.get(i).get(j).getPt().play();
										} else if(!(level.getGroupedTrafficLightsList().get(i).get(k).getState())) {//if light is red, dont do anything, just set the rotation
											//System.out.println("Breaking the loop in order maintain the pause of path ["+i+"] and its ["+j+".] car, at "+k+". light. Distance is:"+distance);
											carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
											break;
										}
									} else {
									carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
								}
								
								 }
								
							}
								//if the car didn't start to move but there is no traffic light infront of it, check the car before it and start moving if its moving
								if(!(trafficLightDetectedWithinASafeDistance)) {
									Bounds bounds3 = carLists.get(i).get(j).getCar().getBoundsInLocal();
							        Bounds screenBounds3 = carLists.get(i).get(j).getCar().localToScene(bounds3);
							        double x3 =  screenBounds3.getCenterX();
							        double y3 =  screenBounds3.getCenterY();
							        
							        
							        Bounds bounds1 = carLists.get(i).get(j-1).getCar().getBoundsInLocal();
							        Bounds screenBounds1 = carLists.get(i).get(j-1).getCar().localToScene(bounds1);
							        double x2 =  screenBounds1.getCenterX();
							        double y2 =  screenBounds1.getCenterY();
						            
						            
						            double distance2 = Math.sqrt((x3-x2)*(x3-x2)+(y3-y2)*(y3-y2));
								if(carLists.get(i).get(j-1).getState().equals("moving")&&(distance2<this.safeDistance)) {
									//System.out.println("Path ["+i+"] and its "+j+". car is to go because there are no traffic lights");
									carLists.get(i).get(j).setState("moving");
									carLists.get(i).get(j).getPt().play();
								}
							}
				}
		
			}
		}
	}
	
	public void checkOtherCars() {
		//check all the cars one by one 
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				
				boolean isBreak=false;
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					//for each car, check its situation with other cars in other paths, if they are distance is close with one of them and that are is crashed, stop the original car
					for(int a=0;a<carLists.size();a++) {
						if(a==i) {
							continue;
						}
						
						for (int b=0;b<carLists.get(a).size();b++) {
							
							if(carLists.get(a).get(b).getState().equals("crushed")||carLists.get(a).get(b).getState().equals("stopped")) {
							
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        
					        
					        Bounds bounds1 = carLists.get(a).get(b).getCar().getBoundsInLocal();
					        Bounds screenBounds1 = carLists.get(a).get(b).getCar().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();
				            
				            
				            double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

				            if (distance<this.crushDistance) {
				                
				            	carLists.get(i).get(j).setState("stopped");
								carLists.get(i).get(j).setRotation(setRotateCar( i, j));
								carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
								carLists.get(i).get(j).getPt().pause();
								isBreak = true;
								break;
				            }
				     }       
				  }
				 	if(isBreak)
				 		break;
				}
					
				
				
				
				
			}
		  }
		}
		
	}
	
	public void checkFrontCar() {
		//check every car one by one, if its front car is in stopped state, stop the first car too
		for(int i=0;i<carLists.size();i++) {
			for (int j=1;j<carLists.get(i).size();j++) {
				
				
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					if(carLists.get(i).get(j-1).getState().equals("stopped")) {
						
						//check the distance between the cars
						Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
				        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
				        double x1 =  screenBounds.getCenterX();
				        double y1 =  screenBounds.getCenterY();
				        Bounds bounds1 = carLists.get(i).get(j-1).getCar().getBoundsInLocal();
				        Bounds screenBounds1 = carLists.get(i).get(j-1).getCar().localToScene(bounds1);
				        double x2 =  screenBounds1.getCenterX();
				        double y2 =  screenBounds1.getCenterY();
			            double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
						
			            if (distance<this.safeDistance) {
						
						carLists.get(i).get(j).setState("stopped");
						carLists.get(i).get(j).setRotation(setRotateCar( i, j));
						carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
						carLists.get(i).get(j).getPt().pause();
						
						}
					} 
				
				
				}
				
			}
			
			
		}
	}
	
	public void checkForTrafficLights() {
		//check all cars one by one
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					//check the traffic lights on its path
					for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
						
						if(!(level.getGroupedTrafficLightsList().get(i).get(k).getState())) {//only check the distance if the light is red
							//check the distance between the car and the light
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        
					        
					        Bounds bounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().getBoundsInLocal();
					        Bounds screenBounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();
					        
					        
					        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
							
				            if (/*14<distance&&*/distance<this.safeLightDistance) {
				            	
				            	//if((x1-x3>0&&x2-x1>0)||(x1-x3<0&&x2-x1<0)||(y1-y3>0&&y2-y1>0)||(y1-y3<0&&y2-y1<0)) {
				            	//System.out.println("Path ["+i+"] and its "+k+". traffic light is setted to red and "+j+". car is stopped");
				            	carLists.get(i).get(j).setState("stopped");
				            	carLists.get(i).get(j).setRotation(setRotateCar( i, j));
								carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
				            	carLists.get(i).get(j).getPt().pause();
				            	//}
				            }
							
							
						}
						
					}
					
					
					
				}
				
			}
		}
		
	}
	
	public void checkCarsInCrashState() {
		//check all the cars one by one, if it is crashed check its crash time,remove from the scene if it high
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				if(carLists.get(i).get(j).getState().equals("crushed")) {
					if(j==0) {
						if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
							
							carXPositionLists.get(i).remove(j);
							carYPositionLists.get(i).remove(j);
							level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
							carLists.get(i).remove(j);
							
						
						}else{
							carLists.get(i).get(j).updateCrushTime();
					        carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
							
					    }
					}else if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
						
						carXPositionLists.get(i).remove(j);
						carYPositionLists.get(i).remove(j);
						level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
						carLists.get(i).remove(j);
						
					
					}else {
							carLists.get(i).get(j).updateCrushTime();
							carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
							
							
					}
				}
			}
			
		}
	}
	
	public void checkCrashes() {
		//check if there are any crashes
		for(int i=0;i<carLists.size();i++) {
			
			for (int j=0;j<carLists.get(i).size();j++) {
			
				boolean isBreak=false;
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					for(int a=0;a<carLists.size();a++) {
						if(a==i) {
							continue;
						}
						
						for (int b=0;b<carLists.get(a).size();b++) {
							
							if(carLists.get(a).get(b).getState().equals("moving")) {}
							
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        
					        
					        Bounds bounds1 = carLists.get(a).get(b).getCar().getBoundsInLocal();
					        Bounds screenBounds1 = carLists.get(a).get(b).getCar().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();
				            
				            
				            double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

				            if (distance<this.crushDistance) {
				                
				                carLists.get(i).get(j).setState("crushed");
								carLists.get(a).get(b).setState("crushed");
								
								carLists.get(i).get(j).setRotation(setRotateCar( i, j));
								carLists.get(a).get(b).setRotation(setRotateCar( a, b));
								
								carLists.get(i).get(j).getPt().pause();
								carLists.get(a).get(b).getPt().pause();
								
								
								
								carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
								carLists.get(a).get(b).setRotate(carLists.get(a).get(b).getRotation());
								
								level.upCrashes(); level.removeText();level.setText();
								isBreak = true;
								break;
				            }
				            
				  }
				 	if(isBreak)
				 		break;
				}
					
				}
				
				
				
			}
		  }	
	}
	
	
	public void uptadeXYLocations() {
		for(int i=0;i<carLists.size();i++) {
			    for (int j=0;j<carLists.get(i).size();j++) {
			    	
			    	
				Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
			        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
			        double x =  screenBounds.getCenterX();
			        double y =  screenBounds.getCenterY();
				
				
				carXPositionLists.get(i).remove(j) ;
				carYPositionLists.get(i).remove(j) ;
				
				carXPositionLists.get(i).add(j,x) ;
				carYPositionLists.get(i).add(j,y) ;
			}
			
		}
		
	}
	
	
	
	public void setRotateAll() {
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				
				Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
		        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
		        double lastx =  screenBounds.getCenterX();
		        double lasty =  screenBounds.getCenterY();
				
				double firstx = carXPositionLists.get(i).get(j);
				double firsty = carYPositionLists.get(i).get(j);
				
				if((lasty-firsty)==0) {
					
					carLists.get(i).get(j).getCar().setRotate(90);
					
		
	            } else if ((lastx-firstx)==0) {
	            	carLists.get(i).get(j).getCar().setRotate(0);
	            	
		
	            }else if(((lasty-firsty)!=0)&&((lastx-firstx)!=0)){
	            	
	            	double radian=(-1)*Math.toDegrees(Math.atan2(lastx-firstx, lasty-firsty));
	            	carLists.get(i).get(j).getCar().setRotate(radian);
	            	
	            }
			}
		}
	}
	
	public double setRotateCar(int i,int j) {
		double rotation;
		Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
        double lastx =  screenBounds.getCenterX();
        double lasty =  screenBounds.getCenterY();
		
		double firstx = carXPositionLists.get(i).get(j);
		double firsty = carYPositionLists.get(i).get(j);
		
		if((lasty-firsty)==0) {
			
			carLists.get(i).get(j).getCar().setRotate(90);
			rotation=90;

        } else if ((lastx-firstx)==0) {
        	carLists.get(i).get(j).getCar().setRotate(0);
        	rotation=0;

        }else if(((lasty-firsty)!=0)&&((lastx-firstx)!=0)){
        	
        	double radian=(-1)*Math.toDegrees(Math.atan2(lastx-firstx, lasty-firsty));
        	rotation=radian;
        } else
        	rotation =90;
		return rotation;
	}
	

		private void spawnCar() {

			int pathNumber = (int) (Math.random() * level.getNumberOfPaths());//generate random path number
			
			if (isCarSpawnPointFree(pathNumber)) { //check if its free
				
				Car car = new Car();
				car.setState("moving");
				car.getCar().setRotate(0);
				
				//set cars starting point equal to paths starting point
				car.getCar().setTranslateX(((MoveTo)(level.getPaths().get(pathNumber).getElements().get(0))).getX());
				car.getCar().setTranslateY(((MoveTo)(level.getPaths().get(pathNumber).getElements().get(0))).getY());
				
				
				PathTransition pt = new PathTransition();
				
			    //adds the path
			    pt.setPath(level.getPaths().get(pathNumber)); //level.getPaths().get(pathNumber);
			    //sets the car to the path
			    pt.setNode(car.getCar());
			    //adds car to the pane
				level.getPane().getChildren().add(car.getCar());
				
				//sets the speed
				
				double length =calculatePathLength(level.getPaths().get(pathNumber));
				double durationInMillis = (((double)length)/ ((double)this.carSpeed)) ; 
				pt.setDuration(Duration.seconds(durationInMillis));
				
				
				pt.setInterpolator(Interpolator.LINEAR);
				
			    
			    // removes the car when it has arrived at the end of the path
			    pt.setOnFinished(e->{
			    	level.getPane().getChildren().remove(car.getCar());
			    	if((!carLists.isEmpty())) {
			    	carLists.get(pathNumber).remove(car);}
			    	level.upScore();level.removeText();level.setText();});
			    
				//add the car to the list of path its in
			    carLists.get(pathNumber).add(car);
			    car.setPt(pt);
			    
			    
			    
			    Bounds bounds = car.getCar().getBoundsInLocal();
		        Bounds screenBounds = car.getCar().localToScene(bounds);
		        double x =  screenBounds.getCenterX();
		        double y =  screenBounds.getCenterY();
			    carXPositionLists.get(pathNumber).add(x);
			    carYPositionLists.get(pathNumber).add(y);
			    
			    
			    pt.play();
			}

		}
		
		public static double calculatePathLength(Path path) {
	        double length = 0;
	        for (int i = 0; i < path.getElements().size() - 1; i++) {
	            if (path.getElements().get(i) instanceof MoveTo && path.getElements().get(i + 1) instanceof LineTo) {
	                MoveTo move = (MoveTo) path.getElements().get(i);
	                LineTo lineTo = (LineTo) path.getElements().get(i + 1);
	                double deltaX = lineTo.getX() - move.getX();
	                double deltaY = lineTo.getY() - move.getY();
	                length += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	            } else if (path.getElements().get(i) instanceof LineTo && path.getElements().get(i + 1) instanceof LineTo) {
	                LineTo line1 = (LineTo) path.getElements().get(i);
	                LineTo line2 = (LineTo) path.getElements().get(i + 1);
	                double deltaX = line2.getX() - line1.getX();
	                double deltaY = line2.getY() - line1.getY();
	                length += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	            }
	        }
	        return length;
	    }
		
		public boolean isCarSpawnPointFree(int pathNumber) {
			boolean isFree = true;
			
			//check distance between the last car on the paths and the paths starting point
			if(!(carLists.get(pathNumber).isEmpty())) {
			Car car = carLists.get(pathNumber).get(carLists.get(pathNumber).size()-1);
			Bounds bounds = car.getCar().getBoundsInLocal();
	        Bounds screenBounds = car.getCar().localToScene(bounds);
	        double x =  screenBounds.getCenterX();
	        double y =  screenBounds.getCenterY();
	        double x1 = level.getPathBeginPointsX().get(pathNumber);
	        double y1 = level.getPathBeginPointsY().get(pathNumber);
	        
	        double distance = Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1)) ;
			
			    if(distance<this.safeDistance) {
				isFree=false;
			    }
			}
			return isFree;
		}

}
