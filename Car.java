import javafx.scene.shape.Rectangle;


public class Car {
	
	private Rectangle rectangle;
	private String state;
	private int crushedTime = 0;
	
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
	
	
	

}
