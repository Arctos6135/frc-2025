package frc.robot.constants;

public class ElevatorConstants {
  public static int CURRENT_LIMIT = 30;

  public static final double GEARBOX_RATIO = 40;

  // Diameter of the gear attached to the motor in meters.
  public static final double GEAR_DIAMETER = 0.0635;
  // Meters
  public static final double POSITION_CONVERSION_FACTOR = (Math.PI * GEAR_DIAMETER) / GEARBOX_RATIO;
  // Meters per second
  public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.0;
  // PID constants for position control.
  public static final double[] PID = {14.0, 0.0, 0.001};

  // The elevator height to score at each level.
  public static final double INTAKE_POSITION = 0.0; // this one should be right
  public static final double L1_HEIGHT = 0.0; // TODO
  public static final double L2_HEIGHT = 0.2; // TODO
  public static final double L3_HEIGHT = 0.3; // TODO
  public static final double L4_HEIGHT = 0.4; // TODO
}
