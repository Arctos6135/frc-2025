package frc.robot.subsystems.elevator;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import frc.robot.constants.ElevatorConstants;
import frc.robot.utils.GaussianNoiseGenerator;
import frc.robot.utils.MathUtils;
import org.ejml.simple.SimpleMatrix;

public class ElevatorIOSim extends ElevatorIO {
  private double voltage;
  private Matrix<N2, N1> state;
  private Matrix<N2, N1> output;
  private GaussianNoiseGenerator noiseGenerator;

  private double maxPos = ElevatorConstants.ELEVATOR_MAX;
  private double minPos = ElevatorConstants.ELEVATOR_MIN;

  private final LinearSystem<N2, N1, N2> motor;

  public ElevatorIOSim() {
    motor = ElevatorConstants.ELEVATOR_LINEAR_SYSTEM;
    // Should be no difference between the two.

    noiseGenerator = new GaussianNoiseGenerator(ElevatorConstants.NOISE_STD);
    state = new Matrix<>(new SimpleMatrix(new double[][] {{0}, {0}}));
    output = motor.calculateY(state, VecBuilder.fill(0));
  }

  @Override
  public void setVoltage(double voltage) {
    this.voltage = voltage;
  }

  @Override
  public void updateInputs(ElevatorInputs inputs) {
    state = motor.calculateX(state, VecBuilder.fill(MathUtils.clampVoltage(voltage)), 0.02);
    normalize();
    output = motor.calculateY(state, VecBuilder.fill(MathUtils.clampVoltage(voltage)));
    inputs.position = output.get(1, 0);
    inputs.velocity = output.get(0, 0);
    inputs.voltage = voltage;
  }

  // Accounts for the hard stops.
  private void normalize() {
    if (state.get(1, 0) <= minPos) {
      state.set(1, 0, minPos);
      state.set(0, 0, 0);
    } else if (state.get(1, 0) >= maxPos) {
      state.set(1, 0, maxPos);
      state.set(0, 0, 0);
    }
  }
}
