import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TrafficLight {
	private double radius = 6.5;
	private boolean state;
	
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	private Circle circle;
	private Line line;
	private Pane pane;

	public TrafficLight(double x1, double y1, double x2, double y2) {
		pane = new Pane();

		line = new Line(x1, y1, x2, y2);
		line.setStrokeWidth(2);

		circle = new Circle((x1 + x2) / 2, (y1 + y2) / 2, radius);
		circle.setFill(Color.GREEN);

		pane.getChildren().addAll(line, circle);
		
		
		this.x1 = x1 ;
		this.y1 = y1  ;
		this.x2 = x2 ;
		this.y2 = y2 ;
	}

	public boolean getState() {
		return state;
	}

	public void setState() {
		if (state) {
			circle.setFill(Color.RED);
			state = false;
		} else {
			circle.setFill(Color.GREEN);
			state = true;
		}
	}

	public Pane getPane() {
		return pane;
	}

	public Circle getCircle() {
		return circle;
	}
	
	public Line getLine() {
		return line;
	}
	
	public double getX1() {
		return x1 ;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}
	
	
	
	
}
