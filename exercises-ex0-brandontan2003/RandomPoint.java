import java.util.Random;

/**
 * The RandomPoint class encapsulates a point 
 * on a 2D plane that is generated randomly.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class RandomPoint extends Point {

	private static Random random = new Random(1);
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;

	public RandomPoint(double minX, double maxX, double minY, double maxY) {
		super(getValue(minX, maxX), getValue(minY, maxY));
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	private static double getValue(double minVal, double maxVal) {
		return minVal + random.nextDouble() * (maxVal - minVal);
	}

	public static void setSeed(int seed) {
		RandomPoint.random = new Random(seed);
	}

}

