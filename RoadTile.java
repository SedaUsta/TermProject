import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RoadTile {
	private int type;
	private int rotationDegree;
	private int xCell;
	private int yCell;
	private double unitWidth;
	private double unitHeight;
	private Pane pane;
	private Color color;

	private List<Marker> markers = new ArrayList<>();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

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
				arc1.setFill(Color.ORANGE);

				pane.getChildren().addAll(arc, arc1);

			} else if (rotation == 90) {
				Arc arc = new Arc((xCell + 1) * unitWidth, (yCell + 1) * unitHeight, unitWidth - 5, unitHeight - 5, 90,
						90);
				Arc arc1 = new Arc((xCell + 1) * unitWidth, (yCell + 1) * unitHeight, 5, 5, 90, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.ORANGE);

				pane.getChildren().addAll(arc, arc1);

			} else if (rotation == 180) {
				Arc arc = new Arc((xCell + 1) * unitWidth, yCell * unitHeight, unitWidth - 5, unitHeight - 5, 180, 90);
				Arc arc1 = new Arc((xCell + 1) * unitWidth, yCell * unitHeight, 5, 5, 180, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.ORANGE);

				pane.getChildren().addAll(arc, arc1);

			}

			else if (rotation == 270) {
				Arc arc = new Arc(xCell * unitWidth, yCell * unitHeight, unitWidth - 5, unitHeight - 5, 270, 90);
				Arc arc1 = new Arc(xCell * unitWidth, yCell * unitHeight, 5, 5, 270, 90);

				arc.setType(ArcType.ROUND);
				arc1.setType(ArcType.ROUND);
				arc.setFill(Color.WHITE);
				arc1.setFill(Color.ORANGE);

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
     // REGULATING MARKERS
	private void addMarker(double x, double y) {
		Marker marker = new Marker(x, y);
		markers.add(marker);
		pane.getChildren().add(marker.getCircle());
	}

	// Method to add markers
	public void addMarkers() {
		double centerX = xCell * unitWidth + unitWidth / 2;
		double centerY = yCell * unitHeight + unitHeight / 2;
		double offset = 13; // offset to adjust marker position

		switch (type) {
		case 0:
		case 2:
		case 3:
			addMarker(centerX + offset, centerY - offset);
			addMarker(centerX - offset, centerY + offset);
			addMarker(centerX - offset, centerY - offset);
			addMarker(centerX + offset, centerY + offset);
			break;
		case 1:
			centerX = (xCell) * unitWidth;
			centerY = (yCell + 1) * unitHeight;

			for (int i = 0; i < 3; i++) {
				double angle = 40 * i - 85;
				double markerX = centerX + 0.54 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
				double markerX1 = centerX + 1.63 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
				double markerY = centerY + 0.54 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
				double markerY1 = centerY + 1.63 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
				Marker marker = new Marker(markerX, markerY);
				Marker marker1 = new Marker(markerX1, markerY1);
				markers.add(marker);
				markers.add(marker1);
				pane.getChildren().addAll(marker.getCircle(), marker1.getCircle());
			}
			break;
		default:
			break;
		}
	}

	public void updateMarkerPositions() {
		if (!markers.isEmpty()) {
			switch (type) {
			case 0:
			case 2:
			case 3:
				pane.getChildren().removeAll(markers.get(0).getCircle(), markers.get(1).getCircle(),
						markers.get(2).getCircle(), markers.get(3).getCircle());
				markers.clear();
				break;

			case 1:
				pane.getChildren().removeAll(markers.get(0).getCircle(), markers.get(1).getCircle(),
						markers.get(2).getCircle(), markers.get(3).getCircle(), markers.get(4).getCircle(),
						markers.get(5).getCircle());
				markers.clear();

				break;
			}
		}

		double centerX, centerY;

		switch (rotationDegree) {
		case 0:
			centerX = xCell * unitWidth + unitWidth / 2;
			centerY = yCell * unitHeight + unitHeight / 2;

			double offset = 13;

			switch (type) {
			case 0:
			case 2:
			case 3:
				addMarker(centerX + offset, centerY - offset);
				addMarker(centerX - offset, centerY + offset);
				addMarker(centerX - offset, centerY - offset);
				addMarker(centerX + offset, centerY + offset);
				break;
			case 1:

				centerX = (xCell) * unitWidth;
				centerY = (yCell + 1) * unitHeight;

				for (int i = 0; i < 3; i++) {
					double angle = 40 * i - 85;
					double markerX = centerX + 0.54 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerX1 = centerX + 1.63 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerY = centerY + 0.54 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					double markerY1 = centerY + 1.63 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					Marker marker = new Marker(markerX, markerY);
					Marker marker1 = new Marker(markerX1, markerY1);
					markers.add(marker);
					markers.add(marker1);
					pane.getChildren().addAll(marker.getCircle(), marker1.getCircle());
				}
				break;
			default:
				break;
			}
			break;
		case 90:
			centerX = xCell * unitWidth + unitWidth / 2;
			centerY = yCell * unitHeight + unitHeight / 2;
			offset = 13;

			switch (type) {
			case 0:
			case 2:
			case 3:

				addMarker(centerX + offset, centerY - offset);
				addMarker(centerX - offset, centerY + offset);
				addMarker(centerX + offset, centerY + offset);
				addMarker(centerX - offset, centerY - offset);
				break;
			case 1:

				centerX = (xCell + 1) * unitWidth;
				centerY = (yCell + 1) * unitHeight;
				for (int i = 0; i < 3; i++) {
					double angle = 40 * i - 175;
					double markerX = centerX + 0.54 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerX1 = centerX + 1.63 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerY = centerY + 0.54 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					double markerY1 = centerY + 1.63 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					Marker marker = new Marker(markerX, markerY);
					Marker marker1 = new Marker(markerX1, markerY1);
					markers.add(marker);
					markers.add(marker1);
					pane.getChildren().addAll(marker.getCircle(), marker1.getCircle());
				}
				break;
			default:
				break;
			}
			break;
		case 180:
			centerX = xCell * unitWidth + unitWidth / 2;
			centerY = yCell * unitHeight + unitHeight / 2;
			offset = 13; 

			switch (type) {
			case 0:
			case 2:
			case 3:

				addMarker(centerX + offset, centerY - offset);
				addMarker(centerX - offset, centerY + offset);
				addMarker(centerX - offset, centerY - offset);
				addMarker(centerX + offset, centerY + offset);
				break;

			case 1:

				centerX = (xCell + 1) * unitWidth;
				centerY = yCell * unitHeight;

				for (int i = 0; i < 3; i++) {
					double angle = 40 * i - 265;
					double markerX = centerX + 0.54 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerX1 = centerX + 1.63 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerY = centerY + 0.54 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					double markerY1 = centerY + 1.63 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					Marker marker = new Marker(markerX, markerY);
					Marker marker1 = new Marker(markerX1, markerY1);
					markers.add(marker);
					markers.add(marker1);
					pane.getChildren().addAll(marker.getCircle(), marker1.getCircle());
				}
				break;
			default:
				break;
			}
			break;
		case 270:
			centerX = xCell * unitWidth + unitWidth / 2;
			centerY = yCell * unitHeight + unitHeight / 2;
			offset = 13; // Offset to adjust marker position

			switch (type) {
			case 0:
			case 2:
			case 3:

				addMarker(centerX + offset, centerY - offset);
				addMarker(centerX - offset, centerY + offset);
				addMarker(centerX + offset, centerY + offset);
				addMarker(centerX - offset, centerY - offset);
				break;
			case 1:
				centerX = xCell * unitWidth;
				centerY = yCell * unitHeight;

				for (int i = 0; i < 3; i++) {
					double angle = 40 * i + 5;
					double markerX = centerX + 0.54 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerX1 = centerX + 1.63 * (unitWidth - 5) / 2 * Math.cos(Math.toRadians(angle));
					double markerY = centerY + 0.54 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					double markerY1 = centerY + 1.63 * (unitHeight - 5) / 2 * Math.sin(Math.toRadians(angle));
					Marker marker = new Marker(markerX, markerY);
					Marker marker1 = new Marker(markerX1, markerY1);
					markers.add(marker);
					markers.add(marker1);
					pane.getChildren().addAll(marker.getCircle(), marker1.getCircle());
				}
				break;
			default:
				break;
			}
			break;
		}
	}

	public void setOnMouseClick(EventHandler<MouseEvent> event) {//indirectly sets the pane on action
		if (pane != null) {
			pane.setOnMouseClicked(event);
		}

	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	public void removeMarkers() {

		if (!markers.isEmpty()) {
			if (type == 0 || type == 2 || type == 3) {
				pane.getChildren().remove(markers.get(0).getCircle());
				pane.getChildren().remove(markers.get(1).getCircle());
				pane.getChildren().remove(markers.get(2).getCircle());
				pane.getChildren().remove(markers.get(3).getCircle());
				markers.clear();

			} else {
				pane.getChildren().remove(markers.get(0).getCircle());
				pane.getChildren().remove(markers.get(1).getCircle());
				pane.getChildren().remove(markers.get(2).getCircle());
				pane.getChildren().remove(markers.get(3).getCircle());
				pane.getChildren().remove(markers.get(4).getCircle());
				pane.getChildren().remove(markers.get(5).getCircle());
			}
			markers.clear();
		}
	}

}
