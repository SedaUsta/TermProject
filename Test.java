import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import java.util.*;
import javafx.geometry.Bounds;


public class Test extends Application {
	
	public double safeCrushTime=200;
	public double safeDistance = 30;
	public double safeLightDistance = 20;
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
		setRotateAll();
		
		// car movement, crush and stop
		// check if there are crushes first
		checkCrashes();
		
		// check crush statement
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				if(carLists.get(i).get(j).getState().equals("crushed")) {
					if(j==0) {
						if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
							
							pathTransitions.get(i).remove(j);
							carXPositionLists.get(i).remove(j);
							carYPositionLists.get(i).remove(j);
							level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
							carLists.get(i).remove(j);
							
						
						}else{
							carLists.get(i).get(j).updateCrushTime();
					        //pathTransitions.get(i).get(j).pause();
					       // System.out.println("Paused");
					        carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
							
					    }
					}else if(carLists.get(i).get(j).getCrushTime()>this.safeCrushTime) {
						
						pathTransitions.get(i).remove(j);
						carXPositionLists.get(i).remove(j);
						carYPositionLists.get(i).remove(j);
						level.getPane().getChildren().remove(carLists.get(i).get(j).getCar());
						carLists.get(i).remove(j);
						
					
					}else {
							carLists.get(i).get(j).updateCrushTime();
					        //pathTransitions.get(i).get(j).pause();
							carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
							
							
					        //System.out.println("Paused");
					}
				}
			}
			
		}
		
		//check if they need to stop
		
		
		//check traffic light
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
				
				if(carLists.get(i).get(j).getState().equals("moving")) {
					
					
					for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
						
						if(!(level.getGroupedTrafficLightsList().get(i).get(k).getState())) {
							//check the distance between the car and the light
							Bounds bounds = carLists.get(i).get(j).getCar().getBoundsInLocal();
					        Bounds screenBounds = carLists.get(i).get(j).getCar().localToScene(bounds);
					        double x1 =  screenBounds.getCenterX();
					        double y1 =  screenBounds.getCenterY();
					        
					        double x3 = this.carXPositionLists.get(i).get(j);
					        double y3 =this.carYPositionLists.get(i).get(j);
					        
					        Bounds bounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().getBoundsInLocal();
					        Bounds screenBounds1 = level.getGroupedTrafficLightsList().get(i).get(k).getCircle().localToScene(bounds1);
					        double x2 =  screenBounds1.getCenterX();
					        double y2 =  screenBounds1.getCenterY();
					        
					        
					        double distance = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
							
				            if (14<distance&&distance<this.safeLightDistance) {
				            	
				            	if((x1-x3>0&&x2-x1>0)||(x1-x3<0&&x2-x1<0)||(y1-y3>0&&y2-y1>0)||(y1-y3<0&&y2-y1<0)) {
				            	
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
		}
		
		
		
		//check the front car
		for(int i=0;i<carLists.size();i++) {
			for (int j=1;j<carLists.get(i).size();j++) {
				
				
				
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
						carLists.get(i).get(j).setRotation(setRotateCar( i, j));
						carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
						carLists.get(i).get(j).getPt().pause();
						
						}
					} 
				
				
				}
				
			}
		}
		//check if they need to move
		for(int i=0;i<carLists.size();i++) {
			for (int j=0;j<carLists.get(i).size();j++) {
						
						//check the front car
						if(carLists.get(i).get(j).getState().equals("stopped")) {
							
							if(j!=0) {
								
								for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
									
									
						            if(carLists.get(i).get(j-1).getState().equals("moving")&&level.getGroupedTrafficLightsList().get(i).get(k).getState()) {
								carLists.get(i).get(j).setState("moving");
								carLists.get(i).get(j).getPt().play();;
									
									
								} else {
									carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
								}
								
								/*
								//check traffic light with if
								 * 
								carLists.get(i).get(j).setState("moving");
								pathTransitions.get(i).get(j).play();
								
							} else*/ 
							}
						
						 }else {
							 if(!(level.getGroupedTrafficLightsList().get(i).isEmpty())) {
							 for(int k=0;k<level.getGroupedTrafficLightsList().get(i).size();k++) {
									
									
						            if(level.getGroupedTrafficLightsList().get(i).get(k).getState()) {
								carLists.get(i).get(j).setState("moving");
								carLists.get(i).get(j).getPt().play();
									
									
								} else {
									carLists.get(i).get(j).setRotate(carLists.get(i).get(j).getRotation());
								}
						 }
							
							} else {
								carLists.get(i).get(j).setState("moving");
								carLists.get(i).get(j).getPt().play();
							}
							
							
						}
						
					}
		
				}
		
		}
		
		//update the x y locations of cars (it must be the last)
		uptadeXYLocations();
		
		
		
		if(time > 3) { 
		   if(Math.random() < 0.3) { 
			
		    spawnCar(); 
		    } 
		    time = 0; 
		  } 
	  }
	
	public void checkCrashes() {
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
			    
			    car.setPt(pt);
			    
			    
			    
			    
			
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
