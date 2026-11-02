/**
 * This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */

class Ex0 {

  public static double estimatePi(int numOfPoints, int seed) {
	  Point centerCircle = new Point(0.5, 0.5);
	  double radius = 0.5;
	  
	  Circle circle = new Circle(centerCircle, radius);
	  double pointsInCircle = 0.0;

	  double minX = centerCircle.getX() - radius;
	  double minY= centerCircle.getY() - radius;
	  double maxX = centerCircle.getX() + radius;
	  double maxY= centerCircle.getY() + radius;
	  
	  RandomPoint.setSeed(seed);
	  for (int i=0; i < numOfPoints; i++) {
		  Point p = new RandomPoint(minX, maxX, minY, maxY);
		  if (circle.contains(p)) {
			  pointsInCircle++;
		  }
	  }
	  return calculatePi(pointsInCircle, numOfPoints);
  }

  private static double calculatePi(double pointsInCircle, int numOfPoints) {
	  return 4.0 * (pointsInCircle / numOfPoints);
  }
}
