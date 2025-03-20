package frc.robot.subsystems.outtake;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.OuttakeConstants;
import org.littletonrobotics.junction.Logger;

public class Outtake extends SubsystemBase {
  private final OuttakeIO io;
  private final OuttakeInputsAutoLogged inputs = new OuttakeInputsAutoLogged();

  private final SimpleMotorFeedforward feedforward;

  private double targetVelocity;
  private double lastTargetVelocity;

  private final MedianFilter filter = new MedianFilter(OuttakeConstants.MEDIAN_FILTER_SIZE);
  private double medianCurrent;

  public Outtake(OuttakeIO io) {
    this.io = io;
    this.feedforward =
        new SimpleMotorFeedforward(OuttakeConstants.kS, 0.15398061338, 0.03178134435);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    medianCurrent = filter.calculate(inputs.current);

    io.setVoltage(
        feedforward.calculate(targetVelocity, (targetVelocity - lastTargetVelocity) / 0.02));

    Logger.processInputs("Outtake", inputs);
    lastTargetVelocity = targetVelocity;
  }

  /**
   * Get the maximum velocity between the two outtake motors.
   *
   * @return in meters of tread per second
   */
  public double getVelocity() {
    return inputs.velocity;
  }

  /**
   * Set the target rotational speed of the outtake motors.
   *
   * @param rps rotations per second.
   */
  public void setRPS(double rps) {
    lastTargetVelocity = targetVelocity;
    targetVelocity = rps;
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
    io.setVoltage(voltage);
  }

  /** Get the filtered current. */
  public double getFilteredCurrent() {
    return medianCurrent;
  }

  public boolean getBeambreak() {
    return io.getBeambreak();
  }
}
