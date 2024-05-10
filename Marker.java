


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Marker {
private double centerX;
private double centerY;
private Circle circle;

	
	Marker(double centerX, double centerY ){
	this.centerX=centerX;
	this.centerY=centerY;

	
		
	circle = new Circle(centerX, centerY,3.7);
		circle.setFill(Color.DARKGRAY);
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
