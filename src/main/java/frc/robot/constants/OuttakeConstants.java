package frc.robot.constants;

import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;

public class OuttakeConstants {
  public static final int CURRENT_LIMIT = 10;
  public static final int MEDIAN_FILTER_SIZE = 12;

  public static final int VELOCITY_CONVERSION_FACTOR = 1;
  public static final int POSITION_CONVERSION_FACTOR = 1;

  public static final double OUTTAKE_RPS = 60;
  public static final double QUICK_OUTTAKE_DURATION = 3; // in seconds

  public static final double[] PID_CONSTANTS = {1.0, 0.0, 0.0};
  public static final LinearSystem<N2, N1, N2> OUTTAKE_LINEAR_SYSTEM = null;
}
