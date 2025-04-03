package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import org.ejml.simple.SimpleMatrix;

public class OuttakeConstants {
  public static final int CURRENT_LIMIT = 40;
  public static final int MEDIAN_FILTER_SIZE = 12;

  public static final double VELOCITY_CONVERSION_FACTOR = 1.0 / 60.0;
  public static final double POSITION_CONVERSION_FACTOR = 1;

  public static final double OUTTAKE_RPS = 20.0;
  public static final double QUICK_OUTTAKE_DURATION = 3; // in seconds

  public static final LinearSystem<N1, N1, N1> OUTTAKE_LINEAR_SYSTEM =
  new Matrix<>(new SimpleMatrix(new double[] {-5.17})),
  new LinearSystem<>( 
          new Matrix<>(new SimpleMatrix(new double[] {35.62})),
          new Matrix<>(new SimpleMatrix(new double[] {1})),
          new Matrix<>(new SimpleMatrix(new double[] {0.0})));

  public static final double kV = 0.145135786465285;
  public static final double kA = 0.029551708522453;
  public static final double kS = 0; // TODO: Controls again
}
