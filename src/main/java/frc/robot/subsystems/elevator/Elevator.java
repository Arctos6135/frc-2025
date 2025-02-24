package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Elevator extends SubsystemBase {
  public final ElevatorIO io;

  public final ElevatorInputsAutoLogged inputs = new ElevatorInputsAutoLogged();

  public Elevator(ElevatorIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    // io.setVoltage(pidController.calculate(inputs.leftPosition));

    Logger.processInputs("Elevator", inputs);
  }

  /**
   * @return returns the position of the left motor in radians
   */
  public double getLeftPosition() {
    return inputs.leftPosition;
  }

  /**
   * Sets the setpoint of the position PID controller.
   *
   * @param setpoint position setpoint
   */
  public void setPosition(double setpoint) {
    Logger.recordOutput("Elevator/TargetPosition", setpoint);
    io.setPosition(setpoint);
  }

  public void setVoltage(double voltage) {
    io.setVoltage(voltage);
  }
}
