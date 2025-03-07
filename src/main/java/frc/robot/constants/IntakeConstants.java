package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import org.ejml.simple.SimpleMatrix;

public class IntakeConstants {
  public static final int CURRENT_LIMIT = 30;

  public static final double GEARBOX_RATIO =
      1.0; // TODO: Uhhhh this should have been done by now...

  // Rotations.
  public static final double POSITION_CONVERSION_FACTOR = 1 / GEARBOX_RATIO;
  // Rotations per second.
  public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.0;

  public static final double[] PID_CONSTANTS = {0.0, 0.0, 0.0};

  public static final int MEDIAN_FILTER_SIZE = 12;

  public static final double INTAKE_RPS = 30;

  public static final double INTAKE_PIECE_TIME = 2; // Seconds

  public static final LinearSystem<N1, N1, N1> INTAKE_LINEAR_SYSTEM =
      new LinearSystem<>(
          new Matrix<>(new SimpleMatrix(new double[][] {{-6.423}})),
          new Matrix<>(new SimpleMatrix(new double[][] {{52.8}})),
          new Matrix<>(new SimpleMatrix(new double[][] {{1.0}})),
          new Matrix<>(new SimpleMatrix(new double[][] {{0.0}})));

  public static final double kS = 0.0; // TODO: Controls
  public static final double kV = 0.121646340539224;
  public static final double kA = 0.020180871963097;

  public static final String MAX_RPS = null;

  public static final String MAX_ACC = null;
}
