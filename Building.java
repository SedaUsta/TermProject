
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class Building extends Node{

	private int type;
	private int rotation;
	private int colorIndex;
	private int xCell;
	private int yCell;
	private Pane pane;
	private Rectangle r;
	private Rectangle r1;
	private Circle c;
	private List<HeaderMarker> headerMarkers = new ArrayList<>();
	private boolean isMarkersAdded;
	


	Color [] colorList = {Color.FIREBRICK ,Color.LIGHTSEAGREEN, Color.BROWN, Color.YELLOWGREEN};

	Building(int type, int rot, int color, int xCell, int yCell) {
		this.type = type;
		this.rotation = rot;
		this.colorIndex = color;
		this.xCell = xCell;
		this.yCell = yCell;
		pane = getBuilding(Main.unitWidth,Main.unitHeight);
		
	}

	public Pane getBuilding(double unitLengthX, double unitLengthY) {
		double x = xCell * unitLengthX;
		double y = yCell * unitLengthY;

		Pane pane = new Pane();
		if (this.type == 0) {
			if (this.rotation == 0) {
              	r = new Rectangle(x, y, (2 * unitLengthX), (3 * unitLengthY));
				r.setArcHeight(15);
				
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
					r1 = new Rectangle((x + 7), (y + 7), (2 * unitLengthX - 15), (2 * unitLengthY - 15));
				r1.setArcHeight(20);
				r1.setArcWidth(20);
				r1.setStroke(colorList[colorIndex]);
				r1.setFill(colorList[colorIndex]);
				
				  pane.setPrefSize(2 * unitLengthX+unitLengthX/2, 3 * unitLengthY);
          

				pane.getChildren().add(r);
				pane.getChildren().add(r1);
			} else if (this.rotation == 90) {
			    r = new Rectangle(x, y, (3 * unitLengthX), (2 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
					r1 = new Rectangle((x + 7), (y + 7), (2 * unitLengthX - 15), (2 * unitLengthY - 15));
				r1.setArcHeight(20);
				r1.setArcWidth(20);
				r1.setStroke(colorList[colorIndex]);
				r1.setFill(colorList[colorIndex]);
				
			     pane.setPrefSize(3 * unitLengthX+unitLengthX/2, 2 * unitLengthY);

				pane.getChildren().add(r);
				pane.getChildren().add(r1);
			} else if (this.rotation == 180) {
				 r = new Rectangle(x, y, (2 * unitLengthX), (3 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
					r1 = new Rectangle(x + 7, y + unitLengthY + 5, (2 * unitLengthX - 15),
						(2 * unitLengthY - 15));
				r1.setArcHeight(20);
				r1.setArcWidth(20);
				r1.setStroke(colorList[colorIndex]);
				r1.setFill(colorList[colorIndex]);

				 pane.setPrefSize(2 * unitLengthX+unitLengthX/2, 3 * unitLengthY);
				 
				pane.getChildren().add(r);
				pane.getChildren().add(r1);
			} else {
				 r = new Rectangle(x, y, (3 * unitLengthX), (2 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
				 r1 = new Rectangle(x + 7 + unitLengthX, y + 7, (2 * unitLengthX - 15),
						(2 * unitLengthY - 15));
				r1.setArcHeight(20);
				r1.setArcWidth(20);
				r1.setStroke(colorList[colorIndex]);
				r1.setFill(colorList[colorIndex]);

				 pane.setPrefSize(3 * unitLengthX+unitLengthX/2, 2 * unitLengthY);
				 
				pane.getChildren().add(r);
				pane.getChildren().add(r1);
			}
		} else if (this.type == 1) {
			if (this.rotation == 0) {
				r = new Rectangle(x, y, (2 * unitLengthX), (3 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
				
				pane.setPrefSize(2 * unitLengthX+unitLengthX/2, 3 * unitLengthY);
				pane.getChildren().add(r);

					c = new Circle();
				c.setCenterX(x + unitLengthX);
				c.setCenterY(y + unitLengthY);
				c.setRadius((unitLengthX) - 10);
				c.setStroke(colorList[colorIndex]);
				c.setFill(colorList[colorIndex]);
 
				pane.getChildren().add(c);

			} else if (this.rotation == 90) {
				r = new Rectangle(x, y, (3 * unitLengthX), (2 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
                
			    pane.setPrefSize(3 * unitLengthX+unitLengthX/2, 2 * unitLengthY);
				pane.getChildren().add(r);

					c = new Circle();
				c.setCenterX(x + unitLengthX);
				c.setCenterY(y + unitLengthY + 2);
				c.setRadius((unitLengthX) - 10);
				c.setStroke(colorList[colorIndex]);
				c.setFill(colorList[colorIndex]);

				pane.getChildren().add(c);
			} else if (this.rotation == 180) {
				r = new Rectangle(x, y, (2 * unitLengthX), (3 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);
				  
				pane.setPrefSize(2 * unitLengthX+unitLengthX/2, 3 * unitLengthY);
				pane.getChildren().add(r);

					c = new Circle();
				c.setCenterX(x + unitLengthX);
				c.setCenterY(y + 2 * unitLengthY + 2);
				c.setRadius((unitLengthX) - 10);
				c.setStroke(colorList[colorIndex]);
				c.setFill(colorList[colorIndex]);

				pane.getChildren().add(c);
			} else {
				r = new Rectangle(x, y, (3 * unitLengthX), (2 * unitLengthY));
				r.setArcHeight(15);
				r.setArcWidth(15);
				r.setStroke(Color.BLACK);
				r.setFill(Color.WHITESMOKE);

				pane.setPrefSize(3* unitLengthX+unitLengthX/2, 2* unitLengthY);
				pane.getChildren().add(r);

					c = new Circle();
				c.setCenterX(x + 2 * unitLengthX);
				c.setCenterY(y + unitLengthY + 2);
				c.setRadius((unitLengthX) - 10);
				c.setStroke(colorList[colorIndex]);
				c.setFill(colorList[colorIndex]);

				pane.getChildren().add(c);
			}

		} else {
			r = new Rectangle(x, y, unitLengthX, unitLengthY);
			r.setArcHeight(15);
			r.setArcWidth(15);
			r.setStroke(colorList[colorIndex]);
			r.setFill(colorList[colorIndex]);
			  pane.setPrefSize( unitLengthX+unitLengthX/2,  unitLengthY);
			pane.getChildren().add(r);
		}
		return pane;
	}

	public Rectangle getR() {
		return r;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public int getType() {
		return type;
	}

	public int getRotation() {
		return rotation;
	}

	public int getColorIndex() {
		return colorIndex;
	}

	public int getXCell() {
		return xCell;
	}

	public int getYCell() {
		return yCell;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setRotation(int rot) {
		this.rotation = rot;
	}

	public void setColorIndex(int colorIndex) {
		this.colorIndex = colorIndex;
	}

	public void setXCell(int x) {
		this.xCell = x;
	}

	public void setYCell (int y) {
		this.yCell = y;
	}

	
	public Rectangle getR1() {
		if(r1!=null) 
		return r1;
		else 
			return null;
	}

	public void setR1(Rectangle r1) {
		this.r1 = r1;
	}

	public Circle getC() {
		if(c!=null)
		return c;
		else 
			return null;
	}

	public void setC(Circle c) {
		this.c = c;
	}

    public void addHeaderMarker() {
    	  isMarkersAdded=true;
    	  headerMarkers = new ArrayList<HeaderMarker>();
    	  double centerX = xCell * Main.unitWidth + Main.unitWidth ;
          double centerY = yCell * Main.unitHeight + 2 * Main.unitHeight + Main.unitHeight / 2;
          double centerX1= xCell*Main.unitWidth+Main.unitWidth/2;
          double centerY1= yCell*Main.unitHeight+Main.unitHeight/2;
    	    if (headerMarkers.isEmpty()) {
    	    switch (type) {
    	    case 0:
    	    	

                HeaderMarker marker1 = new HeaderMarker(centerX + Main.unitWidth/2 , centerY);
                HeaderMarker marker2 = new HeaderMarker(centerX - Main.unitWidth/2 , centerY);
                marker2.getCircle().setFill(Color.WHITE);
    	        
    	        headerMarkers.add(marker1);
    	        headerMarkers.add(marker2);
    	        pane.getChildren().addAll(marker1.getCircle(),marker2.getCircle());

    	       
    	       
    	        break;
    	        
    	    case 1:
    	    	HeaderMarker marker3 = new HeaderMarker(centerX + Main.unitWidth/2 , centerY);
     	        HeaderMarker marker4 = new HeaderMarker(centerX - Main.unitWidth/2 , centerY);
     	       marker4.getCircle().setFill(Color.WHITE);
     	        
     	        headerMarkers.add(marker3);
     	        headerMarkers.add(marker4);
     	       pane.getChildren().addAll(marker3.getCircle(),marker4.getCircle());
     	        

     	       
     	        
     	        
     	        
    	    case 2:
    	    	HeaderMarker marker5 = new HeaderMarker(centerX1,centerY1);
    	    	marker5.getCircle().setFill(Color.DARKSEAGREEN);
    	    	headerMarkers.add(marker5);
     	        pane.getChildren().add(marker5.getCircle());
     	        
    	    	break;
    	    }
    	    }

     
    }
    public boolean isMarkersAdded() {
		return isMarkersAdded;
	}

	public void setMarkersAdded(boolean isMarkersAdded) {
		this.isMarkersAdded = isMarkersAdded;
	}

	public void updateMarkerPositions() {
    	
        double centerX, centerY, centerX1, centerY1;

        if (this.rotation == 0) {
            centerX = xCell * Main.unitWidth + Main.unitWidth;
            centerY = yCell * Main.unitHeight + 2 * Main.unitHeight + Main.unitHeight / 2;
            centerX1 = xCell * Main.unitWidth + Main.unitWidth / 2;
            centerY1 = yCell * Main.unitHeight + Main.unitHeight / 2;
            switch (type) {
            case 0:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker1 = new HeaderMarker(centerX + Main.unitWidth / 2, centerY);
                HeaderMarker marker2 = new HeaderMarker(centerX - Main.unitWidth / 2, centerY);
                marker2.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker1);
                headerMarkers.add(marker2);
                pane.getChildren().addAll(r,r1,marker1.getCircle(), marker2.getCircle());
                break;

            case 1:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker3 = new HeaderMarker(centerX + Main.unitWidth / 2, centerY);
                HeaderMarker marker4 = new HeaderMarker(centerX - Main.unitWidth / 2, centerY);
                marker4.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker3);
                headerMarkers.add(marker4);
                pane.getChildren().addAll(r,c,marker3.getCircle(), marker4.getCircle());
                break;

            case 2:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker5 = new HeaderMarker(centerX1, centerY1);
                marker5.getCircle().setFill(Color.DARKSEAGREEN);
                headerMarkers.add(marker5);
                pane.getChildren().addAll(r,marker5.getCircle());
                break;
        }
        } else if(this.rotation == 90) {
        	
            centerX = xCell * Main.unitWidth + 2*Main.unitWidth +Main.unitWidth/ 2;
            centerY = yCell * Main.unitHeight + Main.unitHeight ;
            centerX1 = xCell * Main.unitWidth + Main.unitHeight / 2;
            centerY1 = yCell * Main.unitHeight + Main.unitHeight / 2;
            
            switch (type) {
            case 0:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker1 = new HeaderMarker(centerX , centerY+Main.unitHeight/2);
                HeaderMarker marker2 = new HeaderMarker(centerX , centerY-Main.unitHeight/2);
                marker2.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker1);
                headerMarkers.add(marker2);
                pane.getChildren().addAll(r,r1,marker1.getCircle(), marker2.getCircle());
                break;

            case 1:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker3 = new HeaderMarker(centerX , centerY+Main.unitHeight/2);
                HeaderMarker marker4 = new HeaderMarker(centerX , centerY-Main.unitHeight/2);
                marker4.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker3);
                headerMarkers.add(marker4);
                pane.getChildren().addAll(r,c,marker3.getCircle(), marker4.getCircle());
                break;

            case 2:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker5 = new HeaderMarker(centerX1, centerY1);
                marker5.getCircle().setFill(Color.DARKSEAGREEN);
                headerMarkers.add(marker5);
                pane.getChildren().addAll(r,marker5.getCircle());
                break;
        }
        } else if (this.rotation == 180) {
            centerX = xCell * Main.unitWidth + Main.unitWidth;
            centerY = yCell * Main.unitHeight + Main.unitHeight / 2;
            centerX1 = xCell * Main.unitWidth + Main.unitWidth / 2;
            centerY1 = yCell * Main.unitHeight + Main.unitHeight / 2;
            switch (type) {
            case 0:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker1 = new HeaderMarker(centerX + Main.unitWidth / 2, centerY);
                HeaderMarker marker2 = new HeaderMarker(centerX - Main.unitWidth / 2, centerY);
                marker2.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker1);
                headerMarkers.add(marker2);
                pane.getChildren().addAll(r,r1,marker1.getCircle(), marker2.getCircle());
                break;

            case 1:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker3 = new HeaderMarker(centerX + Main.unitWidth / 2, centerY);
                HeaderMarker marker4 = new HeaderMarker(centerX - Main.unitWidth / 2, centerY);
                marker4.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker3);
                headerMarkers.add(marker4);
                pane.getChildren().addAll(r,c,marker3.getCircle(), marker4.getCircle());
                break;

            case 2:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker5 = new HeaderMarker(centerX1, centerY1);
                marker5.getCircle().setFill(Color.DARKSEAGREEN);
                headerMarkers.add(marker5);
                pane.getChildren().addAll(r,marker5.getCircle());
                break;
        }
        } else {
            centerX = xCell * Main.unitWidth + Main.unitWidth / 2;
            centerY = yCell * Main.unitHeight + Main.unitHeight ;
            centerX1 = xCell * Main.unitWidth + Main.unitWidth / 2;
            centerY1 = yCell * Main.unitHeight + Main.unitHeight / 2;
            switch (type) {
            case 0:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker1 = new HeaderMarker(centerX , centerY+Main.unitHeight/2);
                HeaderMarker marker2 = new HeaderMarker(centerX , centerY-Main.unitHeight/2);
                marker2.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker1);
                headerMarkers.add(marker2);
                pane.getChildren().addAll(r,r1,marker1.getCircle(), marker2.getCircle());
                break;

            case 1:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker3 = new HeaderMarker(centerX , centerY+Main.unitHeight/2);
                HeaderMarker marker4 = new HeaderMarker(centerX , centerY-Main.unitHeight/2);
                marker4.getCircle().setFill(Color.WHITE);

                headerMarkers.add(marker3);
                headerMarkers.add(marker4);
                pane.getChildren().addAll(r,c,marker3.getCircle(), marker4.getCircle());
                break;

            case 2:
            	headerMarkers.clear();
            	pane.getChildren().clear();
                HeaderMarker marker5 = new HeaderMarker(centerX1, centerY1);
                marker5.getCircle().setFill(Color.DARKSEAGREEN);
                headerMarkers.add(marker5);
                pane.getChildren().addAll(r,marker5.getCircle());
                break;
        }
        }

    }
	public List<HeaderMarker> getHeaderMarkers() {
		if(!headerMarkers.isEmpty())
		return headerMarkers;
		else
			return null;
	}

	public void setHeaderMarkers(List<HeaderMarker> headerMarkers) {
		this.headerMarkers = headerMarkers;
	}

	public void removeHeaderMarkers() {
		
	if(!headerMarkers.isEmpty()) {
		isMarkersAdded=false;
		if(type==0||type==1) {
			
			pane.getChildren().remove(headerMarkers.get(0).getCircle());
			pane.getChildren().remove(headerMarkers.get(1).getCircle());
			headerMarkers.clear();
		
			
		} else {
			pane.getChildren().remove(headerMarkers.get(0).getCircle());
			headerMarkers.clear();
			
		}
		
	}
	

	}

}

	
