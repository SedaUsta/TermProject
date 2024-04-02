import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TrafficLight {
	private double radius = 6.5;
	private boolean state;

	private Circle circle;
	private Line line;

	public TrafficLight(double x1, double y1, double x2, double y2) {
		line = new Line(x1, y1, x2, y2);
		line.setStrokeWidth(2);

		circle = new Circle((x1 + x2) / 2, (y1 + y2) / 2, radius);
		circle.setFill(Color.GREEN);
	}

	public boolean isState() {
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

	public Circle getCircle() {
		return circle;
	}

	public Line getLine() {
		return line;
	}

}