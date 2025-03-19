package frc.robot.constants;

import edu.wpi.first.math.util.Units;

public class VisionConstants {
  public static final String LIMELIGHT_NAME = "";
  public static final double FORWARD_OFFSET = 0.11375;
  public static final double SIDE_OFFSET = 0;
  public static final double UP_OFFSET = 0;
  public static final double ROLL_OFFSET = 0;
  public static final double PITCH_OFFSET = 25;
  public static final double YAW_OFFSET = 0;

  public static final double APRILTAG_HEIGHT = Units.inchesToMeters(6.875 + 5.25);

  // meters and degrees
  public static final double VISION_X_TOLERANCE = 0.05;
  public static final double VISION_Y_TOLERANCE = 0.05;
  public static final double VISION_ANGLE_TOLERANCE = 2;
}
