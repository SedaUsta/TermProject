
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
public class Building {
	
	
	private int type ;
	private int rotation;
	private int colorIndex;
	private int xCell ;
	private int yCell ;
	Color [] colorList = {Color.FIREBRICK ,Color.LIGHTSEAGREEN, Color.BROWN, Color.YELLOWGREEN};
	
	
	Building(int type, int rot, int color, int xCell,int yCell){
		this.type = type;
		this.rotation = rot;		
		this.colorIndex = color;
		this.xCell = xCell;
		this.yCell = yCell;
	}
	
	public Pane getBuilding(double unitLengthX,double unitLengthY) {
		double x = xCell * unitLengthX;
		double y = yCell * unitLengthY;
		
		Pane pane =new Pane();
		if(this.type ==0) {
			if(this.rotation ==0) {
		    Rectangle r = new Rectangle (x,y,(2*unitLengthX) ,(3*unitLengthY));
		    r.setArcHeight(15);
		    r.setArcWidth(15);
		    r.setStroke(Color.BLACK);
		    r.setFill(Color.WHITESMOKE);
		    Rectangle r1 = new Rectangle ((x+7),(y+7),(2*unitLengthX-15),(2*unitLengthY-15));
		    r1.setArcHeight(20);
		    r1.setArcWidth(20);
		    r1.setStroke(colorList[colorIndex]);
		    r1.setFill(colorList[colorIndex]);
		    
		    pane.getChildren().add(r);
		    pane.getChildren().add(r1);
		    } else if(this.rotation == 90){
		    	Rectangle r = new Rectangle (x,y,(3*unitLengthX) ,(2*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    Rectangle r1 = new Rectangle ((x+7),(y+7),(2*unitLengthX-15),(2*unitLengthY-15));
			    r1.setArcHeight(20);
			    r1.setArcWidth(20);
			    r1.setStroke(colorList[colorIndex]);
			    r1.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(r);
			    pane.getChildren().add(r1);
		    }else if(this.rotation == 180){
		    	Rectangle r = new Rectangle (x,y,(2*unitLengthX) ,(3*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    Rectangle r1 = new Rectangle (x+7,y+unitLengthY+5,(2*unitLengthX-15),(2*unitLengthY-15));
			    r1.setArcHeight(20);
			    r1.setArcWidth(20);
			    r1.setStroke(colorList[colorIndex]);
			    r1.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(r);
			    pane.getChildren().add(r1);
		    } else {
		    	Rectangle r = new Rectangle (x,y,(3*unitLengthX) ,(2*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    Rectangle r1 = new Rectangle (x+7+unitLengthX,y+7,(2*unitLengthX-15),(2*unitLengthY-15));
			    r1.setArcHeight(20);
			    r1.setArcWidth(20);
			    r1.setStroke(colorList[colorIndex]);
			    r1.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(r);
			    pane.getChildren().add(r1);
		    }
		}
		else if (this.type == 1) {
			if(this.rotation ==0){
			Rectangle r = new Rectangle (x,y,(2*unitLengthX) ,(3*unitLengthY));
		    r.setArcHeight(15);
		    r.setArcWidth(15);
		    r.setStroke(Color.BLACK);
		    r.setFill(Color.WHITESMOKE);
		    
		    pane.getChildren().add(r);
		    
		    Circle c = new Circle ();
		    c.setCenterX(x+unitLengthX);
		    c.setCenterY(y+unitLengthY);
		    c.setRadius((unitLengthX)-10);
		    c.setStroke(colorList[colorIndex]);
		    c.setFill(colorList[colorIndex]);
		    
		    pane.getChildren().add(c);
		    
			} else if(this.rotation == 90){
				Rectangle r = new Rectangle (x,y,(3*unitLengthX) ,(2*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    
			    pane.getChildren().add(r);
			    
			    Circle c = new Circle ();
			    c.setCenterX(x+unitLengthX);
			    c.setCenterY(y+unitLengthY+2);
			    c.setRadius((unitLengthX)-10);
			    c.setStroke(colorList[colorIndex]);
			    c.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(c);
			} else if(this.rotation == 180){
				Rectangle r = new Rectangle (x,y,(2*unitLengthX) ,(3*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    
			    pane.getChildren().add(r);
			    
			    Circle c = new Circle ();
			    c.setCenterX(x+unitLengthX);
			    c.setCenterY(y+2*unitLengthY+2);
			    c.setRadius((unitLengthX)-10);
			    c.setStroke(colorList[colorIndex]);
			    c.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(c);
			} else {
				Rectangle r = new Rectangle (x,y,(3*unitLengthX) ,(2*unitLengthY));
			    r.setArcHeight(15);
			    r.setArcWidth(15);
			    r.setStroke(Color.BLACK);
			    r.setFill(Color.WHITESMOKE);
			    
			    pane.getChildren().add(r);
			    
			    Circle c = new Circle ();
			    c.setCenterX(x+2*unitLengthX);
			    c.setCenterY(y+unitLengthY+2);
			    c.setRadius((unitLengthX)-10);
			    c.setStroke(colorList[colorIndex]);
			    c.setFill(colorList[colorIndex]);
			    
			    pane.getChildren().add(c);
			}
			
		} else {
			Rectangle r = new Rectangle (x, y,unitLengthX ,unitLengthY);
			r.setArcHeight(15);
		    r.setArcWidth(15);
		    r.setStroke(colorList[colorIndex]);
		    r.setFill(colorList[colorIndex]);
		    
		    pane.getChildren().add(r);
		}
		return pane;
	}
	
	
	
	public int getType() {
		return type ;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public int getColorIndex() {
		return colorIndex ;
	}
	
	public int getXCell() {
		return xCell;
	}
	
	public int getYCell() {
		return yCell;
	}
	
	
	
	public void setType (int type) {
		this.type = type;	
	}
	
	public void setRotation (int rot) {
		this.rotation = rot;
	}
	
	public void setColorIndex(int  colorIndex) {
		this.colorIndex = colorIndex;
	}
	
	public void setXCell (int x) {
		this.xCell = x;
	}
	
	public void setYCell (int y) {
		this.yCell = y;
	}

}
