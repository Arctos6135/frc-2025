package frc.robot.constants;

import edu.wpi.first.math.util.Units;

public class IntakeConstants {
  public static final int CURRENT_LIMIT = 30;

  public static final double GEARBOX_RATIO = 0.0; //TODO
  // Rotations.
  public static final double POSITION_CONVERSION_FACTOR = 1 / GEARBOX_RATIO; 
  // Rotations per second.
  public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.0; 

  public static final int MEDIAN_FILTER_SIZE = 12;
}
