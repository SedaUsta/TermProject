import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class RoadTile {
	private int type;
	private int rotationDegree;
	private int xCell;
	private int yCell;
	private double unitWidth;
	private double unitHeight;
	private Pane pane;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;
	}

	public int getxCell() {
		return xCell;
	}

	public void setxCell(int xCell) {
		this.xCell = xCell;
	}

	public int getyCell() {
		return yCell;
	}

	public void setyCell(int yCell) {
		this.yCell = yCell;
	}

	public double getUnitWidth() {
		return unitWidth;
	}

	public void setUnitWidth(double unitWidth) {
		this.unitWidth = unitWidth;
	}

	public double getUnitHeight() {
		return unitHeight;
	}

	public void setUnitHeight(double unitHeight) {
		this.unitHeight = unitHeight;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public RoadTile(int type, int rotation, int xCell, int yCell, double unitWidth, double unitHeigth) {
		this.type = type;
		this.rotationDegree = rotation;
		this.xCell = xCell;
		this.yCell = yCell;
		this.unitWidth = unitWidth;
		this.unitHeight = unitHeigth;

		pane = new Pane();

		switch (type) {

		case 0:

			if (rotation == 0 || rotation == 180) {
				Rectangle rectangle = new Rectangle(xCell * unitWidth, yCell * unitHeight + 5, unitWidth,
						unitHeight - 10);
				rectangle.setFill(Color.WHITE);

				pane.getChildren().add(rectangle);
			} else if (rotation == 90 || rotation == 270) {
				Rectangle rectangle = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight, unitWidth - 10,
						unitHeight);
				rectangle.setFill(Color.WHITE);
				pane.getChildren().add(rectangle);
			}

			break;

		case 1:

			if (rotation == 0) {
				Arc arc = new Arc(xCell * unitWidth, (yCell + 1) * unitHeight, unitWidth - 5, unitHeight - 5, 0, 90);
				Arc arc1 = new Arc(xCell * unitWidth, (yCell + 1) * unitHeight, 5, 5, 0, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.MEDIUMTURQUOISE);

				pane.getChildren().addAll(arc, arc1);

			} else if (rotation == 90) {
				Arc arc = new Arc((xCell + 1) * unitWidth, (yCell + 1) * unitHeight, unitWidth - 5, unitHeight - 5, 90,
						90);
				Arc arc1 = new Arc((xCell + 1) * unitWidth, (yCell + 1) * unitHeight, 5, 5, 90, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.MEDIUMTURQUOISE);

				pane.getChildren().addAll(arc, arc1);

			} else if (rotation == 180) {
				Arc arc = new Arc((xCell + 1) * unitWidth, yCell * unitHeight, unitWidth - 5, unitHeight - 5, 180, 90);
				Arc arc1 = new Arc((xCell + 1) * unitWidth, yCell * unitHeight, 5, 5, 180, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.MEDIUMTURQUOISE);

				pane.getChildren().addAll(arc, arc1);

			}

			else if (rotation == 270) {
				Arc arc = new Arc(xCell * unitWidth, yCell * unitHeight, unitWidth - 5, unitHeight - 5, 270, 90);
				Arc arc1 = new Arc(xCell * unitWidth, yCell * unitHeight, 5, 5, 270, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.MEDIUMTURQUOISE);

				pane.getChildren().addAll(arc, arc1);

			}
			break;

		case 2:

			Rectangle rectangle = new Rectangle(xCell * unitWidth, yCell * unitHeight + 5, unitWidth, unitHeight - 10);
			Rectangle rectangle1 = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight, unitWidth - 10, unitHeight);

			rectangle.setFill(Color.WHITE);
			rectangle1.setFill(Color.WHITE);

			pane.getChildren().addAll(rectangle, rectangle1);
			break;

		case 3:

			if (rotation == 0) {
				Rectangle rect = new Rectangle(xCell * unitWidth, yCell * unitHeight + 5, unitWidth, unitHeight - 10);
				Rectangle rect1 = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight + 5, unitWidth - 10,
						unitHeight - 5);

				rect.setFill(Color.WHITE);
				rect1.setFill(Color.WHITE);

				pane.getChildren().addAll(rect, rect1);

			} else if (rotation == 90) {
				Rectangle rect1 = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight, unitWidth - 10, unitHeight);
				Rectangle rect = new Rectangle(xCell * unitWidth, yCell * unitHeight + 5, unitWidth - 5,
						unitHeight - 10);

				rect.setFill(Color.WHITE);
				rect1.setFill(Color.WHITE);

				pane.getChildren().addAll(rect, rect1);

			} else if (rotation == 180) {
				Rectangle rect = new Rectangle(xCell * unitWidth, yCell * unitHeight + 5, unitWidth, unitHeight - 10);
				Rectangle rect1 = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight, unitWidth - 10,
						unitHeight - 5);

				rect.setFill(Color.WHITE);
				rect1.setFill(Color.WHITE);

				pane.getChildren().addAll(rect, rect1);

			} else if (rotation == 270) {
				Rectangle rect1 = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight, unitWidth - 10, unitHeight);
				Rectangle rect = new Rectangle(xCell * unitWidth + 5, yCell * unitHeight + 5, unitWidth - 5,
						unitHeight - 10);

				rect.setFill(Color.WHITE);
				rect1.setFill(Color.WHITE);

				pane.getChildren().addAll(rect, rect1);
			}
			break;
		}

	}

}
