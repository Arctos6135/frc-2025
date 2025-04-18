package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import org.ejml.simple.SimpleMatrix;

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
  public static final double[] PID = {61.6385, 0.0, 0.0};

  // Feedforward values
  public static final double ks = -0.1;
  public static final double kg = 0.15;
  public static final double kv = 25.0;
  public static final double ka = 1.2;

  // Hopefully m/s
  public static final double ELEVATOR_MAX_SPEED = 1;

  // The linear model representing the elevator motor.
  public static final LinearSystem<N2, N1, N2> ELEVATOR_LINEAR_SYSTEM =
      new LinearSystem<>(
          new Matrix<>( // State matrix.
              new SimpleMatrix(
                  new double[][] {
                    {-7.853, 0.0},
                    {1.057, -0.01674} // TODO: Controls
                  })),
          new Matrix<>( // Input matrix.
              new SimpleMatrix(
                  new double[][] {
                    {0.3055}, {-0.003065} // TODO: More controls
                  })),
          new Matrix<>( // Output matrix.
              new SimpleMatrix(new double[][] {{1, 0}, {0, 1}})), // Assuming canonical form.
          new Matrix<>( // Feedthrough matrix.
              new SimpleMatrix(
                  new double[][] {{0.0}, {0.0}}))); // There shouldn't be a feedthrough component.

  // The elevator height to score at each level.
  public static final double ZERO = 0.0;
  public static final double HANDOFF_HEIGHT = 0.056;
  public static final double L2_HEIGHT = 0.12;
  public static final double L3_HEIGHT = 0.256;
  public static final double L4_HEIGHT = 0.45; // Its really 0.47 but didnt want to hit hardstop

  // min and max for soft stop
  public static final double ELEVATOR_MAX = 0.52; // hopefully this is right
  public static final double ELEVATOR_MIN = -0.01; // TODO: MAke sure softstops work properly

  public static final double NOISE_STD = 0;
}
