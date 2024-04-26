
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
//import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.transform.Rotate;
import java.util.*;
import javafx.geometry.Bounds;
;


public class Test extends Application {
	
	public double safeCrushTime=80;
	public double safeDistance = 40;
	public double crushDistance=20;
	public double carSpeed=0.4;
	
	public static double time=0;
	
	
	
	static Level level;static int levelCounter = 1;
	
	
	
	
	ArrayList<ObservableList<Car>> carLists; //= new ArrayList<ObservableList<Car>>(level.getNumberOfPaths());
	ArrayList<ArrayList<Double>> carXPositionLists; //= new ArrayList<ArrayList<Double>>(level.getNumberOfPaths());
	ArrayList<ArrayList<Double>> carYPositionLists; //= new ArrayList<ArrayList<Double>> (level.getNumberOfPaths()) ;
	ArrayList<ObservableList<PathTransition>> pathTransitions;
	ArrayList<ObservableList<TrafficLight>> trafficLightLists;
	

	public void start(Stage primaryStage) {

		Pane pane = new Pane();

		Image image = new Image("Entrance1.jpg");
		ImageView entrance = new ImageView(image);
		entrance.setFitHeight(600);
		entrance.setFitWidth(1200);
		pane.getChildren().add(entrance);

		Button startButton = new Button("Start Game");
		// StartButtonHandlerClass startButtonHandler = new StartButtonHandlerClass();

		startButton.setOnAction(e -> {

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
		 this.pathTransitions= new ArrayList<ObservableList<PathTransition>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 pathTransitions.add(FXCollections.observableArrayList());
		 }
		 this.trafficLightLists= new ArrayList<ObservableList<TrafficLight>>();
		 for(int i=0;i<level.getNumberOfPaths();i++) {
			 trafficLightLists.add(FXCollections.observableArrayList());
		 }
		 /*for(int i=0;i<level.getNumberOfPaths();i++) {
			 
			 for(int j=0;j<level.getTrafficLights().size();j++) {
				// checks if the traffic light is on that path
			    boolean isIntersects = level.getPaths().get(i).intersects(level.getTrafficLights().get(j).getX1(), level.getTrafficLights().get(j).getY1(), level.getTrafficLights().get(j).getX2(), level.getTrafficLights().get(j).getY2());
		  
				if(isIntersects) {
					trafficLightLists.get(i).add(level.getTrafficLights().get(j));
					continue;
				} 
			 }
			 
		 
		 }*/
		 

			primaryStage.setScene(level.getScene());
			

			 int hasWin = gameplay();
			 if(hasWin==1) {
				 levelCounter++;
			 } else {
				 //primaryStage.setScene(sceneLose());
				 levelCounter = 1;
			 }

		});

		startButton.setMaxWidth(150);
		startButton.setMaxHeight(75);
		startButton.setMinWidth(150);
		startButton.setMinHeight(75);
		startButton.setTranslateX(550);
		startButton.setTranslateY(100);
		startButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		;
		startButton.setStyle(
				"-fx-background-color: violet ;  -fx-text-fill: white;-fx-border-color: white; -fx-border-width: 2px;");

		pane.getChildren().add(startButton);

		/*Button loseScreen = new Button("Lose Screen");
		loseScreen.setOnAction(e -> {
			primaryStage.setScene(sceneLose());
		});
		pane.getChildren().add(loseScreen);*/

		Scene scene = new Scene(pane, 1200, 600);

		primaryStage.setTitle("Test");

		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

	/*
	 * class StartButtonHandlerClass implements EventHandler<ActionEvent>{
	 * 
	 * public void handle(ActionEvent e) {
	 * System.out.println("Start game button clicked");
	 * 
	 * String s = "level" + Counter.levelCounter ;
	 * 
	 * Level level = new Level(s);
	 * 
	 * 
	 * 
	 * } }
	 */
	public Scene sceneLose() {

		Pane pane = new Pane();

		BackgroundFill backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(0), new Insets(10));

		Background background = new Background(backgroundFill);

		pane.setBackground(background);

		Text loseText = new Text("YOU LOST :(");

		loseText.setFont(Font.font("grey", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 150));
		loseText.setFill(Color.CORAL);

		pane.setBackground(background);

		pane.getChildren().add(loseText);
		Scene scene = new Scene(pane, 1200, 600);
		loseText.layoutXProperty().bind(scene.widthProperty().subtract(loseText.prefWidth(-1)).divide(2));
		loseText.layoutYProperty().bind(scene.heightProperty().subtract(loseText.prefHeight(-1)).divide(2));
		
		// gonna add return main menu and start over button here 

		return scene;

	}

	public int gameplay() {

		int hasWon = 0;
		
		
		
		
		createTraffic();

		return hasWon;
	}

	

	private void createTraffic() {
		
		//Create new arraylist's which holds other arraylist, create these arraylists too, number of arraylists determined by number of paths
		
		
		 
		 
		 
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}

	private void update() { 
		time += 0.16; 
		//Implement the logic for cars checking other cars or lights here 
		    
		
		// car movement - turning
		// using nested loops to check all the cars one by one
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				
				//Car car = carLists.get(i).get(j);
				
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
		
	            }else {
	            	
	            	carLists.get(i).get(j).getCar().setRotate(0);
		
	            }
			}
		}
		
		// car movement, crush and stop
		// check if there are crushes first
		for(int i=carLists.size()-1;i>=0;i--) {
			//System.out.println("got in i");
			/*if(!(carLists.get(i).isEmpty())) {
				//check for traffic light
				System.out.println("got in isEmpty");
				
				
			}*/
			
			for(int j=carLists.get(i).size()-1;j>=1;j--) {
				//System.out.println("got in j");
				boolean isBreak=false;
				if(carLists.get(i).get(j).getState().equals("moving")) {
					//System.out.println(" if moving");
					for(int a=0;a<carLists.size();a++) {
						if(a==i) {
							continue;
						}
						//System.out.println("got in a");
						for (int b=0;b<carLists.get(a).size();b++) {
							//System.out.println("got in b");
							/*System.out.println("got in a,b");
							Bounds bounds1 = carLists.get(a).get(b).getCar().getBoundsInLocal();
		    		        Bounds screenBounds1 = carLists.get(a).get(b).getCar().localToScene(bounds1);
		    		        double xa =  screenBounds1.getCenterX();
		    		        double ya =  screenBounds1.getCenterY();
		    		        
		    		        Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
		                    Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
		    		        double x1 =  screenBounds.getCenterX();
		    		        double y1 =  screenBounds.getCenterY();
		    		        
		    		        
		    		        double distance = Math.sqrt((x1-xa)*(x1-xa)+(y1-ya)*(y1-ya));
		    		       
							if(distance<this.crushDistance) {
								System.out.println("got crushed");
							carLists.get(i).get(j).setState("crushed");
						carLists.get(a).get(b).setState("crushed");
						pathTransitions.get(i).get(j).pause();
						pathTransitions.get(a).get(b).pause();
						level.upCrashes(); level.removeText();level.setText();
						break;
						}*/
							
							//Shape intersect = Shape.intersect(carLists.get(i).get(j).getCar(), carLists.get(a).get(b).getCar());
		    		        //intersect.getBoundsInLocal().getWidth() != -1
							
							/*Bounds bounds1 = carLists.get(a).get(b).getCar().getBoundsInLocal();
		    		        Bounds screenBounds1 = carLists.get(a).get(b).getCar().localToScene(bounds1);
		    		        double xa =  screenBounds1.getCenterX();
		    		        double ya =  screenBounds1.getCenterY();
		    		        
		    		        Bounds bounds2 = carLists.get(i).get(j).getCar().getBoundsInLocal();
		                    Bounds screenBounds2 = carLists.get(i).get(j).getCar().localToScene(bounds2);
		    		        double x1 =  screenBounds2.getCenterX();
		    		        double y1 =  screenBounds2.getCenterY();
							
							Bounds bounds = carLists.get(a).get(b).getCar().getBoundsInParent();
		    		        Bounds screenBounds = carLists.get(a).get(b).getCar().localToScene(bounds);
		    		        Bounds bounds3 = carLists.get(i).get(j).getCar().getBoundsInParent();
		    		        
		    		        carLists.get(a).get(b).getCar().setTranslateX(xa);
		    		        carLists.get(a).get(b).getCar().setTranslateY(ya);
		    		        carLists.get(i).get(j).getCar().setTranslateX(x1);
		    		        carLists.get(i).get(j).getCar().setTranslateY(y1);
		    		        
							carLists.get(i).get(j).getCar().intersects(carLists.get(i).get(j).getCar().sceneToLocal(screenBounds));
					if(bounds3.intersects(bounds)) {
					System.out.println("got crushed");
						carLists.get(i).get(j).setState("crushed");
						carLists.get(a).get(b).setState("crushed");
						pathTransitions.get(i).get(j).pause();
						
						
						
						
						pathTransitions.get(a).get(b).pause();
						level.upCrashes(); level.removeText();level.setText();
						break;
					}*/
							
							
							
							/*
							double x1 = this.carLists.get(i).get(j).getCar().getTranslateX();
				            double y1 = this.carLists.get(i).get(j).getCar().getTranslateY();

				            double x2 = this.carLists.get(a).get(b).getCar().getTranslateX();
				            double y2 = this.carLists.get(a).get(b).getCar().getTranslateY();*/
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        Bounds bounds1 = carLists.get(a).get(b).getCar().getBoundsInLocal();
					        Bounds screenBounds1 = carLists.get(a).get(b).getCar().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();
				            //System.out.println(" i = " + i +", j = " + j+", a = " + a+", b = " + b);
				            //System.out.println(" x1 = " + x1 +", y1 = " + y1+", x2 = " + x2+", y2 = " + y2);
				            
				            double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
				            
				            //System.out.println("Distance: " + distance);

				            if (distance<this.crushDistance) {
				                //System.out.println("Intersection detected at i = " + i +", j = " + j+", a = " + a+", b = " + b);
				                
				                
				                carLists.get(i).get(j).setState("crushed");
								carLists.get(a).get(b).setState("crushed");
								pathTransitions.get(i).get(j).pause();
								pathTransitions.get(a).get(b).pause();
								
								/*pathTransitions.get(i).remove(j);
								carXPositionLists.get(i).remove(j);
								carYPositionLists.get(i).remove(j);
								level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
								carLists.get(i).remove(j);
								
								pathTransitions.get(a).remove(b);
								carXPositionLists.get(a).remove(b);
								carYPositionLists.get(a).remove(b);
								level.getPane().getChildren().remove(carLists.get(a).get(b).getCar());
								carLists.get(a).remove(b);
								*/
								
								
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
		// check crush statement
		for(int i=carLists.size()-1;i>=0;i--) {
			for(int j=carLists.get(i).size()-1;j>=0;j--) {
				if(carLists.get(i).get(j).getState().equals("crushed")) {
					if(j==0) {
						if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
							
							pathTransitions.get(i).remove(j);
							carXPositionLists.get(i).remove(j);
							carYPositionLists.get(i).remove(j);
							level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
							carLists.get(i).remove(j);
							
						
						}else
								carLists.get(i).get(j).updateCrushTime();
					}else if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
						
						pathTransitions.get(i).remove(j);
						carXPositionLists.get(i).remove(j);
						carYPositionLists.get(i).remove(j);
						level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
						carLists.get(i).remove(j);
						
					
					}else
							carLists.get(i).get(j).updateCrushTime();
					        pathTransitions.get(i).get(j).pause();
					        System.out.println("Paused");
					
				}
			}
			
		}
		
		//check if they need to stop
		
		
		//check traffic light
		for(int i=carLists.size()-1;i>=0;i--) {
			for(int j=carLists.get(i).size()-1;j>=1;j--) {
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					level.getGroupedTrafficLightsList().get(i).size();
					for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
						
						if(!(level.getGroupedTrafficLightsList().get(i).get(k).getState())) {
							//check the distance between the car and the light
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        
					        
					        /*Bounds bounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().getBoundsInLocal();
					        Bounds screenBounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();*/
					        double x2 = (level.getGroupedTrafficLightsList().get(i).get(k).getX1()+level.getGroupedTrafficLightsList().get(i).get(k).getX2())/2 ;
					        double y2 =  (level.getGroupedTrafficLightsList().get(i).get(k).getY1()+level.getGroupedTrafficLightsList().get(i).get(k).getY2())/2;
					        
					        
					        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
							
				            if (distance<this.safeDistance) {
				            	
				            	carLists.get(i).get(j).setState("stopped");
								pathTransitions.get(i).get(j).pause();
				            	
				            }
							
							
						}
						
					}
					
					
					
				}
				
			}
		}
		
		
		
		//check the front car
		for(int i=carLists.size()-1;i>=0;i--) {
			for(int j=carLists.get(i).size()-1;j>=1;j--) {
				
				
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					if(carLists.get(i).get(j-1).getState().equals("stopped")||carLists.get(i).get(j-1).getState().equals("crushed")) {
						
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
						pathTransitions.get(i).get(j).pause();
						}
					} 
				
				
				}
				
			}
		}
		//check if they need to move
				for(int i=carLists.size()-1;i>=0;i--) {
					for(int j=carLists.get(i).size()-1;j>=0;j--) {
						
						//check the front car
						if(carLists.get(i).get(j).getState().equals("stopped")) {
							
							if(j==0) {
								//check traffic light with if
								carLists.get(i).get(j).setState("moving");
								pathTransitions.get(i).get(j).play();
								
							} else if(carLists.get(i).get(j-1).getState().equals("moving")) {
								carLists.get(i).get(j).setState("moving");
								pathTransitions.get(i).get(j).play();
							}
						
						
						}
						
					}
		
				}
		
		
		
		//update the x y locations of cars (it must be the last)
		
		for(int i=0;i<carLists.size();i++) {
			
			
			
			
			
					
						
			        
			    for (int j=0;j<carLists.get(i).size();j++) {
				Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
			        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
			        double x =  screenBounds.getCenterX();
			        double y =  screenBounds.getCenterY();
				if(i==0) {
					if(j==0) {
						if(pathTransitions.get(i).get(j).getCurrentTime().greaterThanOrEqualTo(Duration.millis(2000))) {
							//pathTransitions.get(i).get(j).pause();
						}
				//System.out.print("x:"+ x+"  y:" +y );
				//System.out.println();
				}
			}    
			        
			        
					
				//double x = carLists.get(i).get(j).getCar().getX();
				//double y = carLists.get(i).get(j).getCar().getY();
				
				carXPositionLists.get(i).remove(j) ;
				carYPositionLists.get(i).remove(j) ;
				
				carXPositionLists.get(i).add(j,x) ;
				carYPositionLists.get(i).add(j,y) ;
				
				
				
				
			}
			
			
			
		}
		
		
		
		if(time > 3) { 
		   if(Math.random() < 0.3) { 
			
		    spawnCar(); 
		    } 
		    time = 0; 
		  } 
	  }
	
	

		private void spawnCar() {

			int pathNumber = (int) (Math.random() * level.getNumberOfPaths());
			
			
			

			if (isCarSpawnPointFree(pathNumber)) {
				
				Car car = new Car();
				car.setState("moving");
				car.getCar().setRotate(0);
				
				
				
				PathTransition pt = new PathTransition();
				
				//sets the speed
				double length = level.getPathLengths().get(pathNumber);
			    pt.setDuration(Duration.millis(length/this.carSpeed));
			    
			    //adds the path
			    pt.setPath(level.getPaths().get(pathNumber)); //level.getPaths().get(pathNumber);
			    //sets the car to the path
			    pt.setNode(car.getCar());
			    //adds car to the pane
				level.getPane().getChildren().add(car.getCar());
			    pt.play();
			    
			    
			    // removes the car when it has arrived at the end of the path
			    pt.setOnFinished(e->{level.getPane().getChildren().remove(car.getCar());carLists.get(pathNumber).remove(car);level.upScore();level.removeText();level.setText();});
				//add the car to the list of path its in
			    carLists.get(pathNumber).add(car);
			    pathTransitions.get(pathNumber).add(pt);
			    
			    
			    
			    
			    
			    
			
			    Bounds bounds = car.getCar().getBoundsInLocal();
		        Bounds screenBounds = car.getCar().localToScene(bounds);
		        double x =  screenBounds.getCenterX();
		        double y =  screenBounds.getCenterY();
		        
			    carXPositionLists.get(pathNumber).add(x);
			    carYPositionLists.get(pathNumber).add(y);
			    
			    
			    
			    
			    
			    
				
				
			      
			     
				
				//level.getPathTransitions().get(pathNumber).setNode(car.getCar());
				//level.getPathTransitions().get(pathNumber).play();

			}

		}
		
		private boolean isCarSpawnPointFree(int pathNumber) {
			boolean isFree = true;
			if(!(carLists.get(pathNumber).isEmpty())) {
			Car car = carLists.get(pathNumber).get(carLists.get(pathNumber).size()-1);
			Bounds bounds = car.getCar().getBoundsInLocal();
	        Bounds screenBounds = car.getCar().localToScene(bounds);
	        double x =  screenBounds.getCenterX();
	        double y =  screenBounds.getCenterY();
	        double x1 = level.getPathBeginPointsX().get(pathNumber);
	        double y1 = level.getPathBeginPointsY().get(pathNumber);
	        
	        //level.getPaths().get(pathNumber).getElements().
	        
	        double distance = Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1)) ;
			
			if(distance<this.safeDistance) {
				//System.out.println("isFree is setted to false");
				isFree=false;
			}
			
			}
			
			
			
			return isFree;
		}

}
