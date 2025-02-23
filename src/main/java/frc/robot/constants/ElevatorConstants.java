package frc.robot.constants;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.system.LinearSystem;
import org.ejml.simple.SimpleMatrix;

public class ElevatorConstants {
  public static int CURRENT_LIMIT = 30;

  // TODO Put in the actual gearbox ratio.
  public static final double GEARBOX_RATIO = 40;

  // Diameter of the gear attached to the motor in meters.
  public static final double GEAR_DIAMETER = 0.0635;
  // Meters
  public static final double POSITION_CONVERSION_FACTOR = (Math.PI * GEAR_DIAMETER) / GEARBOX_RATIO;
  // Meters per second
  public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.0;
  // PID constants for position control.
  public static final double[] PID = {112.0884, 0.0, 0.0};

  // The linear model representing the elevator motor.
  public static final LinearSystem<N3, N1, N1> ELEVATOR_LINEAR_SYSTEM =
      new LinearSystem<>(
          new Matrix<>(
              new SimpleMatrix(
                  new double[][] {
                    {0.855756266573646, -0.156805459569392, -0.0324543982645198},
                    {-0.134435469949302, 0.780801045006736, -0.0108956132650232},
                    {-0.524581095331046, -0.744993024168507, 0.913010274068047}
                  })),
          new Matrix<>(
              new SimpleMatrix(
                  new double[][] {
                    {0.0825485311247870}, {0.0142345953800736}, {0.0629463029566337}
                  })),
          new Matrix<>(
              new SimpleMatrix(
                  new double[] {0.106176010725236, 0.127039700963015, -0.0513014816345014})),
          new Matrix<>(new SimpleMatrix(new double[] {0})));

  // The elevator height to score at each level.
  public static final double INTAKE_POSITION = 0.0; // this one should dbe right
  public static final double L1_HEIGHT = 0.0; // TODO
  public static final double L2_HEIGHT = 1.0; // TODO
  public static final double L3_HEIGHT = 2.0; // TODO
  public static final double L4_HEIGHT = 3.0; // TODO
}
