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

  public static double clampVoltage(double voltage) {
    if (voltage > 12) {
      return 12;
    } else if (voltage < -12) {
      return -12;
    } else {
      return voltage;
    }
  }
}
