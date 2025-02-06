package frc.robot.constants;

public class ElevatorConstants {
  public static int CURRENT_LIMIT = 30;

  public static final double GEARBOX_RATIO = 0;

  // Rads
  public static final double POSITION_CONVERSION_FACTOR = 2 * Math.PI * GEARBOX_RATIO;
  // Rads per sec
  public static final double VELOCITY_CONVERSION_FACTOR = 2 * Math.PI * GEARBOX_RATIO / 60.0;
  // PID constants for position control.
  public static final double[] PID = {1.0, 0.0, 0.0};
}
