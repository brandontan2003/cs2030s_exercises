/**
 * The Point class encapsulates a point on a 2D plane.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public String toString() {
	  return "(" + this.x + ", " + this.y + ")";
	}
}
