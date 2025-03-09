package frc.robot.utils;

public class MathUtils {
  /**
   * Return zero if a number is less than 0.05.
   *
   * @param num double to evaluate
   * @return rounded double
   */
  public static double nearZero(double num) {
    return Math.abs(num) > 0.05 ? num : 0;
  }

  /**
   * Clamp a number to be in between min and max.
   * @param number The number to be clamped.
   * @param min The minimum value.
   * @param max The maximum value.
   * @return The clamped number.
   */
  public static double clamp(double number, double min, double max) {
    if (number > max) {
      return max;
    } else if (number < min) {
      return min;
    } else {
      return number;
    }
  }

  public static double clampVoltage(double voltage) {
    return clamp(voltage, -12, 12);
  }
}
