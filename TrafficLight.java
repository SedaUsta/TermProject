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
//		In order to see the borders of the pane.
//		pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		
//		The pane must be resized since different traffic lights may overlap.
//		To position the pane correctly, we need to use the setLayoutX and setLayoutY methods.
//		With this code, we assign the top-left coordinates of the pane.
		if (x1 == x2) {
			pane.setPrefSize(11, 28);
			pane.setLayoutX(x1 - 6.5);
			pane.setLayoutY(y2 > y1 ? y1 : y2);
		} else if (y1 == y2) {
			pane.setPrefSize(28, 11);
			pane.setLayoutX(x2 > x1 ? x1 : x2);
			pane.setLayoutY(y1 - 6.5);
		}
		
//		When we resize the pane, the x and y coordinates of the objects within the pane become relative to it.
//		Therefore, we need to remeasure the position of the line and circle.
		line = new Line(0, 0, x2 > x1 ? x2 - x1 : x1 - x2, y2 > y1 ? y2 - y1 : y1 - y2);
		line.setStrokeWidth(2);

		circle = new Circle(x2 > x1 ? (x2 - x1) / 2 : (x1 - x2) / 2, y2 > y1 ? (y2 - y1) / 2 : (y1 - y2) / 2, radius);
//		When the TrafficLight object is created, we have to set the state to true and the color to green.
		setState();

//		line and circle must be added to the pane.
		pane.getChildren().addAll(line, circle);

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean getState() {
		return state;
	}

//	To setState(), we need to check if the state is true or false, then make it the opposite and change the color accordingly.
	public void setState() {
		if (state) {
			circle.setFill(Color.RED);
			state = false;
		} else {
			circle.setFill(Color.GREEN);
			state = true;
		}
	}
	
//	This method will be used in level class.
	public Pane getPane() {
		return pane;
	}

//	This method is going to be used for setOnMouseClicked() method in level class.
	public Circle getCircle() {
		return circle;
	}

	public Line getLine() {
		return line;
	}

	public double getX1() {
		return x1;
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
