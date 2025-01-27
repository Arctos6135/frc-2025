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

    Logger.processInputs("Elevator", inputs);
  }

  /**
   * @return returns the position of the left motor in radians
   */
  public double getLeftPosition() {
    return inputs.leftPosition;
  }

  public void setVoltage(double voltage) {
    io.setVoltage(voltage);
  }
}
