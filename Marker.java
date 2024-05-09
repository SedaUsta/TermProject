
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Marker {
private double centerX;
private double centerY;
private Circle circle;

private boolean selected = false;
private Line connectingLine;
private final DoubleProperty mouseXProperty;
private final DoubleProperty mouseYProperty;
	
	
	Marker(double centerX, double centerY ){
	this.centerX=centerX;
	this.centerY=centerY;
	mouseXProperty=null;
	mouseYProperty=null;
	
		
	circle = new Circle(centerX, centerY,3.7);
		circle.setFill(Color.DARKGRAY);
	}

	 

	    public Marker(DoubleProperty mouseXProperty, DoubleProperty mouseYProperty) {
	        this.mouseXProperty = mouseXProperty;
	        this.mouseYProperty = mouseYProperty;
	    }

	    // Method to handle mouse click on the marker
	    public void handleMouseClick(Pane pane) {
	        if (!selected) {
	            select();
	            if (connectingLine == null) {
	                connectingLine = new Line();
	                pane.getChildren().add(connectingLine);
	            }
	            // Set the starting point of the line to the marker's center
	            double startX = getCenterX() + getCircle().getRadius();
	            double startY = getCenterY() + getCircle().getRadius();
	            connectingLine.setStartX(startX);
	            connectingLine.setStartY(startY);
	            // Bind the end point of the line to the mouse movement
	            connectingLine.endXProperty().bind(pane.getScene().widthProperty().subtract(pane.getScene().widthProperty().subtract(mouseXProperty)));
	            connectingLine.endYProperty().bind(pane.getScene().heightProperty().subtract(pane.getScene().heightProperty().subtract(mouseYProperty)));
	        } else {
	            deselect();
	            // Remove the line
	            pane.getChildren().remove(connectingLine);
	            connectingLine = null;
	        }
	    }

	    // Method to select the marker
	    private void select() {
	        selected = true;
	        // Change the appearance of the marker when selected
	    }

	    // Method to deselect the marker
	    private void deselect() {
	        selected = false;
	        // Restore the appearance of the marker when deselected
	    }




	public double getCenterX() {
		return centerX;
	}




	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}




	public double getCenterY() {
		return centerY;
	}




	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}




	public Circle getCircle() {
		return circle;
	}




	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	
	

	
}


class HeaderMarker extends Marker{
 
	HeaderMarker(double centerX, double centerY) {
		super(centerX, centerY);
		
		Circle circle = new Circle(centerX,centerY,7);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.RED);
		super.setCircle(circle);
		
	}
	
	
	
	
}
