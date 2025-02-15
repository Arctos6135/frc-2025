package frc.robot.subsystems.outtake;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.OuttakeConstants;
import org.littletonrobotics.junction.Logger;

public class Outtake extends SubsystemBase {
  private final OuttakeIO io;
  private final OuttakeInputsAutoLogged inputs = new OuttakeInputsAutoLogged();

  private final PIDController pidController;

  private final MedianFilter filterLeft = new MedianFilter(OuttakeConstants.MEDIAN_FILTER_SIZE);
  private final MedianFilter filterRight = new MedianFilter(OuttakeConstants.MEDIAN_FILTER_SIZE);
  private double leftMedianCurrent;
  private double rightMedianCurrent;

  public Outtake(OuttakeIO io) {
    this.io = io;
    pidController =
        new PIDController(
            OuttakeConstants.PID_CONSTANTS[0],
            OuttakeConstants.PID_CONSTANTS[1],
            OuttakeConstants.PID_CONSTANTS[2]);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    leftMedianCurrent = filterLeft.calculate(inputs.leftCurrent);
    rightMedianCurrent = filterRight.calculate(inputs.rightCurrent);

    io.setVoltage(pidController.calculate(getVelocity()));

    Logger.processInputs("Outtake", inputs);
    Logger.recordOutput("Outtake/Filtered Current Left", leftMedianCurrent);
    Logger.recordOutput("Outtake/Filtered Current Right", rightMedianCurrent);
  }

  /**
   * Get the maximum velocity between the two outtake motors.
   *
   * @return in meters of tread per second
   */
  public double getVelocity() {
    return Math.max(inputs.leftVelocity, inputs.rightVelocity);
  }

  /**
   * Set the target rotational speed of the outtake motors.
   *
   * @param rps rotations per second.
   */
  public void setRPS(double rps) {
    pidController.setSetpoint(rps);
  }

  /**
   * Set the target rotational speed of the outtake motors.
   *
   * @param rpm rotations per minute.
   */
  public void setRPM(double rpm) {
    setRPS(rpm / 60);
  }

  /**
   * Set the voltage of both outtake motors.
   *
   * @param voltage voltage
   */
  public void setVoltage(double voltage) {
    Logger.recordOutput("Outtake/Voltage", voltage);
    io.setVoltage(voltage);
  }

  /** Get the average of the most most up-to-date filtered for both motors. */
  public double getFilteredCurrent() {
    return (leftMedianCurrent + rightMedianCurrent) / 2;
  }
}
