
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
public static int selectedColor;
public static Object selectedObject;
public static int rotation;
public static int unitWidth=800/15;
public static int unitHeight= 800/15;
public static boolean markersHidden;
private static ObservableList<Building> buildings;
private static ObservableList<RoadTile> roadTiles;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException,Exception {
		
		buildings = FXCollections.observableArrayList();
		roadTiles = FXCollections.observableArrayList();
    //File
	
	
   //Panes
	BorderPane pane = new BorderPane() ;
    Pane metaData = new Pane();
	HBox colors = new HBox();
	HBox buildings= new HBox();
	HBox roadTiles1= new HBox();
	HBox roadTiles2 = new HBox();
	VBox items = new VBox();  
	
	
	
	//COLOR SELECTING
	
	Rectangle color1 = new Rectangle(800,10,50,50);	
	Rectangle color2 = new Rectangle(875,10,50,50);
    Rectangle color3 = new Rectangle(950,10,50,50);
    Rectangle color4=  new Rectangle(1025,10,50,50);
	
	colors.getChildren().addAll(color1,color2,color3,color4);
	colors.setAlignment(Pos.CENTER);
	  
	   
		color1.setFill(Color.FIREBRICK ); color1.setStroke(Color.BLACK);
		color1.setStroke(Color.RED);
		color2.setFill(Color.LIGHTSEAGREEN); color2.setStroke(Color.BLACK);
		color3.setFill(Color.BROWN); color3.setStroke(Color.BLACK);
		color4.setFill(Color.YELLOWGREEN); color4.setStroke(Color.BLACK);
		
	
	    color1.setOnMouseClicked(e->{selectedColor= 0;
	    	color1.setStroke(Color.RED);color2.setStroke(Color.BLACK);color3.setStroke(Color.BLACK);color4.setStroke(Color.BLACK);});
	  
	    color2.setOnMouseClicked(e->{selectedColor= 1;
	    	color2.setStroke(Color.RED);color1.setStroke(Color.BLACK);color3.setStroke(Color.BLACK);color4.setStroke(Color.BLACK);});
	  
	    color3.setOnMouseClicked(e->{selectedColor= 2;
	    	color3.setStroke(Color.RED);color1.setStroke(Color.BLACK);color2.setStroke(Color.BLACK);color4.setStroke(Color.BLACK);});
	    
	    color4.setOnMouseClicked(e->{selectedColor= 3;
	    	color4.setStroke(Color.RED);color1.setStroke(Color.BLACK);color2.setStroke(Color.BLACK);color3.setStroke(Color.BLACK);});
	    	
	    
	    //BUTTONS
	    BackgroundFill bgf= new BackgroundFill(Color.RED,new CornerRadii(5), new Insets(0));
	    Background bg= new Background(bgf);
	    
	    Button saveMapBt= new Button();
	    saveMapBt.setText("Save Map");
	    saveMapBt.setTextFill(Color.WHITE);
	    saveMapBt.setBackground(bg);
	    
	 
	    Button rotateBt= new Button();
	    rotateBt.setText("Rotate");
	    rotateBt.setTextFill(Color.WHITE);
	    rotateBt.setBackground(bg);
	    
	    Button clearBt= new Button();
	    clearBt.setText("Clear Map");
	    clearBt.setTextFill(Color.WHITE);
	    clearBt.setBackground(bg);
	     Button hideMarkersBt= new Button();
	     hideMarkersBt.setText("Hide Markers");
	     hideMarkersBt.setTextFill(Color.WHITE);
	     hideMarkersBt.setBackground(bg);
	    
	   
	 
	    //BUILDING & ROADTILE SELECTING
 
	    TrafficLight tlight = new TrafficLight(70,50,130,50);
	    
	    Building type0 = new Building(0,rotation,0,1,1);
        Building type1 = new Building(1,rotation,1,1,1);
	    Building type2 = new Building(2,rotation,3,1,1);
	  
	    RoadTile typeZero= new RoadTile(0,rotation,1,1,40,40);
	    RoadTile typeOne= new RoadTile(1,rotation,1,1,40,40);
	    RoadTile typeTwo= new RoadTile(2,rotation,1,1,40,40);
	    RoadTile typeThree= new RoadTile(3,rotation,1,1,40,40);
	    
	    
	    //BUILDING THE MAP 
	    
	    tlight.getCircle().setOnMouseClicked(me->{
	    	tlight.getCircle().setStroke(Color.RED);
	        selectedObject=tlight;
	    });
       
	    
	    type0.getR().setOnMouseClicked(me -> {
	    	selectedObject=type0;
	    
	    });
	    type0.getR1().setOnMouseClicked(me -> {
	    	selectedObject=type0;
	     
	    });

	    
	    type1.getR().setOnMouseClicked(me -> {
	    	selectedObject=type1;
	     
	    });
	    type1.getC().setOnMouseClicked(me -> {
	    	selectedObject=type1;
	     
	    });
	    
	    
	    type2.getR().setOnMouseClicked(me -> {
	    	selectedObject=type2;
	        
	    });
	    
	    typeZero.setOnMouseClick(me -> {
	        selectedObject = typeZero;
	       
	    });
	    typeOne.setOnMouseClick(me -> {
	        selectedObject = typeOne;
	       
	    });
	    typeTwo.setOnMouseClick(me -> {
	        selectedObject = typeTwo;
	   
	    });
	    typeThree.setOnMouseClick(me -> {
	        selectedObject = typeThree;
	       
	    });
	    
	   
	  
	    //ITEM SELECTING CATALOGUE (ITEMS PANE)
	   
	    typeZero.addMarkers();
	    typeOne.addMarkers();
	    typeTwo.addMarkers();
	    typeThree.addMarkers();
	    buildings.getChildren().addAll(stack(type0),stack(type1));
	   buildings.setAlignment(Pos.CENTER_LEFT);
	   items.getChildren().addAll(colors,rotateBt,clearBt,saveMapBt,hideMarkersBt,stack(tlight),buildings,stack(type2));
	   roadTiles1.getChildren().addAll(typeZero.getPane(),typeOne.getPane());
	   roadTiles1.setAlignment(Pos.CENTER);
       roadTiles2.getChildren().addAll(typeTwo.getPane(),typeThree.getPane());
       roadTiles2.setAlignment(Pos.CENTER);
	   items.getChildren().addAll(roadTiles1,roadTiles2);
	
	   
	   //ROTATING
	   
	   rotateBt.setOnMouseClicked(me->{
		   
	    	rotation=(rotation==270)?0:rotation+90;
	    	
	    	TrafficLight newtLight =rotateTrafficLight(tlight);
	    	
	         items.getChildren().clear();
	         roadTiles1.getChildren().clear();
	         roadTiles2.getChildren().clear();
	         buildings.getChildren().clear();
	         
	        Building newType0 = new Building(0,rotation,0,1,1);
	        Building newType1 = new Building(1,rotation,1,1,1);
	 	    Building newType2 = new Building(2,rotation,3,1,1);
	 	    RoadTile newTypeZero= new RoadTile(0,rotation,1,1,40,40);
	 	    RoadTile newTypeOne= new RoadTile(1,rotation,1,1,40,40);
	 	    RoadTile newTypeTwo= new RoadTile(2,rotation,1,1,40,40);
	 	    RoadTile newTypeThree= new RoadTile(3,rotation,1,1,40,40);
	 	    
	 	    

		       
		    newType0.getR().setOnMouseClicked(e -> {
		    	selectedObject=newType0;
		    
		    });
		    newType0.getR1().setOnMouseClicked(e -> {
		    	selectedObject=newType0;
		     
		    });
		    
		    
		    newType1.getR().setOnMouseClicked(e -> {
		    	selectedObject=newType1;
		     
		    });
		    newType1.getC().setOnMouseClicked(e -> {
		    	selectedObject=newType1;
		     
		    });
		    
		    
		    newType2.getR().setOnMouseClicked(e -> {
		    	selectedObject=newType2;
		        
		    });
		    
		    newTypeZero.setOnMouseClick(e -> {
		        selectedObject = newTypeZero;
		       
		    });
		    newTypeOne.setOnMouseClick(e -> {
		        selectedObject = newTypeOne;
		       
		    });
		    newTypeTwo.setOnMouseClick(e -> {
		        selectedObject = newTypeTwo;
		   
		    });
		    newTypeThree.setOnMouseClick(e -> {
		        selectedObject = newTypeThree;
		       
		    });
		    newtLight .getCircle().setOnMouseClicked(e->{
		    	newtLight .getCircle().setStroke(Color.RED);
		        selectedObject=newtLight ;
		    });
		    
		
		       buildings.getChildren().addAll(stack(newType0),stack(newType1));
			   buildings.setAlignment(Pos.CENTER_LEFT);
			   items.getChildren().addAll(colors,rotateBt,clearBt,saveMapBt,hideMarkersBt,newtLight.getPane(),buildings,stack(newType2));
			   roadTiles1.getChildren().addAll(newTypeZero.getPane(),newTypeOne.getPane());
			   roadTiles1.setAlignment(Pos.CENTER);
		       roadTiles2.getChildren().addAll(newTypeTwo.getPane(),newTypeThree.getPane());
		       roadTiles2.setAlignment(Pos.CENTER);
			   items.getChildren().addAll(roadTiles1,roadTiles2);
	    	 
	  	   
	  	    
	    
	    });
	   
	   //MAP CLEARING
	   clearBt.setOnMouseClicked(me ->{
		   metaData.getChildren().clear();
		   
		    for(int i =1; (800/15)*i<800;i++) {
		        Line line = new Line(i*(800/15),0,i*(800/15),800);
		       
		        metaData.getChildren().add(line);
		
		        line.setStroke(Color.WHITE);
		        
		        for(int j =1;  (800/15)*j<800;j++) {
		     	   Line line1 = new Line(0,j*(800/15),800,j*(800/15));
		     	   line1.setStroke(Color.WHITE);
		     	   
		     	metaData.getChildren().add(line1);
		     }
		       
		     }});
	   //HIDE / SHOW MARKERS .
	   hideMarkersBt.setOnMouseClicked(me->{
		  if( hideMarkersBt.getText().equals("Hide Markers")) {
			  markersHidden =true;
			  hideMarkersBt.setText("Show Markers");
			  		items.getChildren().clear();
			  		roadTiles1.getChildren().clear();
			  		roadTiles2.getChildren().clear();
			  		buildings.getChildren().clear();
			  		
		typeZero.removeMarkers();
		typeOne.removeMarkers();
		typeTwo.removeMarkers();
		typeThree.removeMarkers();
			
			   buildings.getChildren().addAll(stack(type0),stack(type1));
			   buildings.setAlignment(Pos.CENTER_LEFT);
			   items.getChildren().addAll(colors,rotateBt,clearBt,saveMapBt,hideMarkersBt,tlight.getPane(),buildings,stack(type2));
			   roadTiles1.getChildren().addAll(typeZero.getPane(),typeOne.getPane());
			   roadTiles1.setAlignment(Pos.CENTER);
		       roadTiles2.getChildren().addAll(typeTwo.getPane(),typeThree.getPane());
		       roadTiles2.setAlignment(Pos.CENTER);
			   items.getChildren().addAll(roadTiles1,roadTiles2);
			   deletePaneMarkers(metaData);
			  
			  
			  
		  }
		  else if(hideMarkersBt.getText().equals("Show Markers")) {
			  hideMarkersBt.setText("Hide Markers");
			  markersHidden =false;
			  
			  		items.getChildren().clear();
			  		roadTiles1.getChildren().clear();
			  		roadTiles2.getChildren().clear();
			  		buildings.getChildren().clear();
			  		
		            typeZero.addMarkers();
	            	typeOne.addMarkers();
		            typeTwo.addMarkers();
		            typeThree.addMarkers();
		            
			   buildings.getChildren().addAll(stack(type0),stack(type1));
			   buildings.setAlignment(Pos.CENTER_LEFT);
			   items.getChildren().addAll(colors,rotateBt,clearBt,saveMapBt,hideMarkersBt,tlight.getPane(),buildings,stack(type2));
			   roadTiles1.getChildren().addAll(typeZero.getPane(),typeOne.getPane());
			   roadTiles1.setAlignment(Pos.CENTER);
		       roadTiles2.getChildren().addAll(typeTwo.getPane(),typeThree.getPane());
		       roadTiles2.setAlignment(Pos.CENTER);
			   items.getChildren().addAll(roadTiles1,roadTiles2);
			   reAddPaneMarkers(metaData);
			  
			  
		  }
		   
		   
		   
	   });
	   
	   //METADATA PANE ADJUSTMENTS
	   
    for(int i =1; (800/15)*i<800;i++) {
       Line line = new Line(i*(800/15),0,i*(800/15),800);
      
       line.setStroke(Color.WHITE);
       
       metaData.getChildren().add(line);
   
       for(int j =1;  (800/15)*j<800;j++) {
    	   
    	   Line line1 = new Line(0,j*(800/15),800,j*(800/15));
    	 
    	   line1.setStroke(Color.WHITE);
    	   
    	metaData.getChildren().add(line1);
    }
      
    }
   

  
    //Background and alignment adjustments

    BackgroundFill backgroundFill = new BackgroundFill(Color.SANDYBROWN, new CornerRadii(0), new Insets(0));
	Background background = new Background(backgroundFill);
	
    pane.setBackground(background);
    items.setStyle("-fx-border-color:black;");
   
    
	metaData.setBackground(background);
	 items.setBackground(background);
	items.setAlignment(Pos.CENTER);
	items.setPrefWidth(400);

    pane.setLeft(metaData);
    pane.setRight(items);
	
	
 
	   metaData.setOnMouseClicked(me->{
	    	handleClick(selectedObject,me,metaData);
	    });
	
    Scene scene = new Scene (pane,1200,800);
    
    stage.setScene(scene);
		stage.show();
	
	    
	}


	private void handleClick(Object o, MouseEvent event, Pane pane) {
		
	    if (selectedObject != null ) {
	    	
	        double x = event.getX();
	        double y = event.getY();
	        if (selectedObject instanceof TrafficLight) {
		        
	        	if(rotation%180==0) {
	            double x1 = x - 10; 
	            double x2 = x + 10; 
	 
	            TrafficLight newTrafficLight = new TrafficLight(x1, y, x2, y);
	            
	            pane.getChildren().add(newTrafficLight.getPane());
	        	}
	        	else {
	        		double y1= y-10;
	        		double y2= y+10;
	        		  TrafficLight newTrafficLight = new TrafficLight(x, y1, x, y2);
	  	            
	  	            pane.getChildren().add(newTrafficLight.getPane());
	        		
	        	}
	            
	         
	        }
	        
	        if (selectedObject instanceof Building) {
	        	
	            Building building = (Building) selectedObject;
	          
	            Building newBuilding = new Building(building.getType(), rotation, selectedColor, (int) (x / unitWidth), (int) (y / unitHeight));	   
	             if(!markersHidden) {
	            newBuilding.addHeaderMarker();
	             newBuilding.updateMarkerPositions();
	             }
	             else {
	            	 newBuilding.removeHeaderMarkers();
	             }
	             
	            pane.getChildren().add(newBuilding.getPane());
	            
	            buildings.add(newBuilding);
	            
	        }  if (selectedObject instanceof RoadTile) {

	            RoadTile roadTile = (RoadTile) selectedObject;
	            
	            
	            
	            RoadTile newRoadTile = new RoadTile(roadTile.getType(), rotation,  (int) (x / unitWidth), (int) (y / unitHeight), unitWidth, unitHeight);
	            if(markersHidden==false) {
	            newRoadTile.addMarkers();
	            newRoadTile.updateMarkerPositions();
	            }
	            else {
	            	newRoadTile.removeMarkers();
	            }
	            pane.getChildren().add(newRoadTile.getPane());
	            roadTiles.add(newRoadTile);
	     
	        } 
	        
	    }
	}


	
	public Pane stack(Object o) {
		
	    Pane stackPane = new Pane();
       
	      if(markersHidden==false) {
	    if (o instanceof Building) {
	        Building building = (Building) o;
	      {
	    	  if(!building.isMarkersAdded()) {
	            building.updateMarkerPositions();
	            
	    	  }
	    	
	         
	        }
	        switch (building.getType()) {
	            case 0:
	                stackPane.getChildren().addAll(building.getR(), building.getR1(),building.getHeaderMarkers().get(0).getCircle(),building.getHeaderMarkers().get(1).getCircle());
	                
	                return stackPane;
	            case 1:
	                stackPane.getChildren().addAll(building.getR(), building.getC(),building.getHeaderMarkers().get(0).getCircle(),
	                		building.getHeaderMarkers().get(1).getCircle());
	                
	                return stackPane;
	            case 2:
	                stackPane.getChildren().addAll(building.getR(),building.getHeaderMarkers().get(0).getCircle());
	                
	                return stackPane;
	        }
	        
	    } else if (o instanceof TrafficLight) {
	        TrafficLight tLight = (TrafficLight) o;
	        tLight.getCircle().setRadius(15);
	        stackPane.getChildren().addAll(tLight.getPane());
	        
	    } 
	    else if (o instanceof RoadTile) {
	    
	        RoadTile roadTile = (RoadTile) o;
	        roadTile.addMarkers();
	    	roadTile.updateMarkerPositions();

	    	stackPane.getChildren().add(roadTile.getPane());

	    	
	    	
	      return stackPane;
	    }
		
}


else if(markersHidden==true) {
	 if (o instanceof Building) {
	Building building = (Building) o;
	
	
	building.removeHeaderMarkers();
	  switch (building.getType()) {
	  
      case 0:
          stackPane.getChildren().addAll(building.getR(), building.getR1());
          
          return stackPane;
      case 1:
          stackPane.getChildren().addAll(building.getR(), building.getC());
          
          return stackPane;
      case 2:
          stackPane.getChildren().addAll(building.getR());
          
          return stackPane;
  }
	 }

} else if (o instanceof TrafficLight) {
    TrafficLight tLight = (TrafficLight) o;
    tLight.getCircle().setRadius(15);
    stackPane.getChildren().addAll(tLight.getPane());
    
} 
else if (o instanceof RoadTile) {

    RoadTile roadTile= (RoadTile)o;
    roadTile.removeMarkers();
    stackPane.getChildren().add(roadTile.getPane());

	return stackPane;

	
	

}
	
	

		return stackPane;
	    
	
	  
		
		}
	
	public static TrafficLight rotateTrafficLight(TrafficLight tLight) {
		TrafficLight newTLight;
		if(rotation==90||rotation==270) {
	
			double half = (tLight.getX2()-tLight.getX1())/2;
			double newX= (tLight.getX1()+tLight.getX2())/2;
			Line line = new Line(newX,tLight.getY1()-half,newX,tLight.getY1()+half);
			newTLight = new TrafficLight(newX,tLight.getY1()-half,newX,tLight.getY1()+half);
			newTLight.getCircle().setOnMouseClicked(me->{
				newTLight.getCircle().setStroke(Color.RED);
			});
			
		}
		else {
			
			 newTLight = new TrafficLight(tLight.getX1(),tLight.getY1(),tLight.getX2(),tLight.getY2());
		}
		
		
		return newTLight ;
	}
	
	public static void deletePaneMarkers(Pane pane) {
		if(!buildings.isEmpty())
		for(int i=0; i<buildings.size();i++) {
			pane.getChildren().remove(buildings.get(i).getPane());
			if(buildings.get(i).getHeaderMarkers()!=null)
			buildings.get(i).removeHeaderMarkers();
			pane.getChildren().add(buildings.get(i).getPane());
		}
		if(!roadTiles.isEmpty())
		for(int i=0; i<roadTiles.size();i++) {
			pane.getChildren().remove(roadTiles.get(i).getPane());
			if(roadTiles.get(i).getMarkers()!=null)
			roadTiles.get(i).removeMarkers();
			pane.getChildren().add(roadTiles.get(i).getPane());
			
			
		}
	}

	public static void reAddPaneMarkers(Pane pane) {
		
		if(!buildings.isEmpty())
		for(int i=0; i<buildings.size();i++) {
			pane.getChildren().remove(buildings.get(i).getPane());
			buildings.get(i).addHeaderMarker();
			buildings.get(i).updateMarkerPositions();
			pane.getChildren().add(buildings.get(i).getPane());
			
		}
		
		
		if(!roadTiles.isEmpty())
		for(int i=0; i<roadTiles.size();i++) {
			pane.getChildren().remove(roadTiles.get(i).getPane());
		 roadTiles.get(i).addMarkers();
			roadTiles.get(i).updateMarkerPositions();
			pane.getChildren().add(roadTiles.get(i).getPane());
		}
		
	}
	

	public static void instantiate (Pane pane) {
		
			for(int i=0; i<buildings.size();i++) {
				if(!buildings.get(i).getHeaderMarkers().isEmpty())
					for( int j=0; j<buildings.size();j++) {
						buildings.get(i).getHeaderMarkers().get(j).handleMouseClick(pane);
						
						
					}
				
			}
	

}

}







