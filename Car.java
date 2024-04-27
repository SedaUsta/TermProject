
import javafx.scene.shape.Rectangle;
import javafx.animation.*;


public class Car {
	
	private Rectangle rectangle;
	private String state;
	private int crushedTime = 0;
	private PathTransition pt;
	private double rotation;
	
	Car(){
		
		this.rectangle = new Rectangle(0, 0, 10, 20);
		
	}
	
	public Rectangle getCar() {
		return rectangle;
	}
	
	public void setRotate(double angle) {
		this.rectangle.setRotate(angle);
	}
	
	public void updateCrushTime() {
		crushedTime++;
	}
	public int getCrushTime() {
		return this.crushedTime;
	}
	public void setCrushTime(int dur) {
		this.crushedTime=dur;
	}
	public void setState(String s) {
		this.state=s;
	}
	public String getState() {
		return this.state;
	}

	public int getCrushedTime() {
		return crushedTime;
	}

	public void setCrushedTime(int crushedTime) {
		this.crushedTime = crushedTime;
	}

	public PathTransition getPt() {
		return pt;
	}

	public void setPt(PathTransition pt) {
		this.pt = pt;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	
	
	
	

}
