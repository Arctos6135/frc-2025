package frc.robot.utils;

public class SlewRateLimiter {
  private double prevX = 0.0;
  private double prevY = 0.0;
  private final double maxRate;

  public SlewRateLimiter(double maxRate) {
    this.maxRate = maxRate;
  }

  public double[] limit(double x, double y) {
    // Calculate the change in x and y
    double deltaX = x - prevX;
    double deltaY = y - prevY;

    // Calculate the magnitude of the change
    double magnitude = Math.hypot(deltaX, deltaY);

    // If the magnitude exceeds the max rate, scale down the changes
    if (magnitude > maxRate) {
      double scale = maxRate / magnitude;
      deltaX *= scale;
      deltaY *= scale;
    }

    // Update the previous values
    prevX += deltaX;
    prevY += deltaY;

    return new double[] {prevX, prevY};
  }
}
