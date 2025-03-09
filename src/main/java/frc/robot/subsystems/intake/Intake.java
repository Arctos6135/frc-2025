package frc.robot.subsystems.intake;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;
import org.littletonrobotics.junction.Logger;

public class Intake extends SubsystemBase {
  private final IntakeIO io;
  private final SimpleMotorFeedforward feedforward;
  // private final TrapezoidProfile trapezoidProfile;
  private double rps;

  private final IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();

  // Reduce impact of noise and sudden spikes
  private final MedianFilter filter = new MedianFilter(IntakeConstants.MEDIAN_FILTER_SIZE);
  private double medianCurrent;

  public Intake(IntakeIO io) {
    this.io = io;
    feedforward =
        new SimpleMotorFeedforward(IntakeConstants.kS, IntakeConstants.kV, IntakeConstants.kA);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    medianCurrent = filter.calculate(inputs.leftCurrent);

    io.setVoltage(feedforward.calculate(rps));
    Logger.processInputs("Intake", inputs);
  }

  /**
   * @return in meters of tread per second
   */
  public double getVelocity() {
    return inputs.leftSpeed;
  }

  /**
   * Set the target rotational speed of the intake.
   *
   * @param rps rotations per second.
   */
  public void setRPS(double rps) {
    this.rps = rps;
  }

  /**
   * Set the target rotational speed of the intake.
   *
   * @param rpm rotations per minute.
   */
  public void setRPM(double rpm) {
    setRPS(rpm / 60);
  }

  /**
   * Set the voltage of the intake motor.
   *
   * @param voltage voltage
   */
  public void setVoltage(double voltage) {
    io.setVoltage(voltage);
  }

  /** Get the most up-to-date filtered current value. */
  public double getFilteredCurrent() {
    return medianCurrent;
  }
}
