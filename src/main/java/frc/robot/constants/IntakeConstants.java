package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import org.ejml.simple.SimpleMatrix;

public class IntakeConstants {
  public static final int CURRENT_LIMIT = 30;

  public static final double GEARBOX_RATIO = 0.0; // TODO
  // Rotations.
  public static final double POSITION_CONVERSION_FACTOR = 1 / GEARBOX_RATIO;
  // Rotations per second.
  public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.0;

  public static final double[] PID_CONSTANTS = {1.0, 0.0, 0.0};

  public static final int MEDIAN_FILTER_SIZE = 12;

  public static final double INTAKE_RPS = 70.0;

  public static final LinearSystem<N2, N1, N2> INTAKE_LINEAR_SYSTEM =
      new LinearSystem<>(
          new Matrix<>(new SimpleMatrix(new double[][] {{0.0, 0.0}, {1.0, 0.0}})),
          new Matrix<>(new SimpleMatrix(new double[][] {{0.015}, {0.0}})),
          new Matrix<>(new SimpleMatrix(new double[][] {{1.0, 0.0}, {0.0, 1.0}})),
          new Matrix<>(
              new SimpleMatrix(new double[][] {{0.0}, {0.0}}))); // TODO Use actual numbers.
}
