package frc.robot.subsystems.elevator;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ElevatorConstants;
import frc.robot.utils.MathUtils;

import org.littletonrobotics.junction.Logger;

public class Elevator extends SubsystemBase {
  public final ElevatorIO io;
  private final double kG = ElevatorConstants.kg;
  public final PIDController pidController;
  public final ElevatorFeedforward feedForward;
  public final ElevatorInputsAutoLogged inputs = new ElevatorInputsAutoLogged();

  public Elevator(ElevatorIO io) {
    this.io = io;
    pidController =
        new PIDController(
            ElevatorConstants.PID[0], ElevatorConstants.PID[1], ElevatorConstants.PID[2]);

    feedForward =
        new ElevatorFeedforward(ElevatorConstants.ks, ElevatorConstants.kg, ElevatorConstants.kv);
    
        pidController.setTolerance(0.05);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    io.setVoltage(-pidController.calculate(inputs.position) - kG);

    Logger.processInputs("Elevator", inputs);
  }

  /**
   * @return returns the position of the left motor in radians
   */
  public double getPosition() {
    return inputs.position;
  }

  /**
   * Sets the setpoint of the position PID controller.
   *
   * @param setpoint position setpoint
   */
  public void setPosition(double setpoint) {
    pidController.setSetpoint(MathUtils.clamp(setpoint, ElevatorConstants.ELEVATOR_MIN, ElevatorConstants.ELEVATOR_MAX));
  }

  public boolean atSetpoint() {
    return pidController.atSetpoint();
  }

  /**
   * Resets the internal encoder position of the elevator.
   *
   * @note this does NOT move the elevator to the default position. To do that, you must call
   *     setPosition.
   */
  public void zeroEncoderPosition() {}

  public void setVoltage(double voltage) {
    io.setVoltage(voltage);
  }
}
