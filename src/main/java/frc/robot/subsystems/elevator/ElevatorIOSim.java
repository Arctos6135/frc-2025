package frc.robot.subsystems.elevator;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import frc.robot.constants.ElevatorConstants;
import frc.robot.utils.MathUtils;
import org.ejml.simple.SimpleMatrix;

public class ElevatorIOSim extends ElevatorIO {
  private double voltage;
  private Matrix<N2, N1> state;
  private Matrix<N2, N1> output;

  private final LinearSystem<N2, N1, N2> motor;

  public ElevatorIOSim() {
    motor = ElevatorConstants.ELEVATOR_LINEAR_SYSTEM;
    // Should be no difference between the two.

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
    output = motor.calculateY(state, VecBuilder.fill(MathUtils.clampVoltage(voltage)));

    inputs.leftPosition = output.get(1, 0);
    inputs.rightPosition = output.get(1, 0);

    inputs.leftVelocity = output.get(0, 0);
    inputs.rightVelocity = output.get(0, 0);

    inputs.leftVoltage = voltage;
    inputs.rightVoltage = voltage;
  }
}
