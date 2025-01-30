package frc.robot.subsystems.intake;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IntakeConstants;
import org.littletonrobotics.junction.Logger;

public class Intake extends SubsystemBase {
  private final IntakeIO io;

  private final IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();

  // Reduce impact of noise and sudden spikes
  private final MedianFilter filter = new MedianFilter(IntakeConstants.MEDIAN_FILTER_SIZE);
  private double medianCurrent;

  public Intake(IntakeIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    medianCurrent = filter.calculate(inputs.current);

    Logger.processInputs("Intake", inputs);
    Logger.recordOutput("Intake/Filtered Current", medianCurrent);
  }

  /**
   * @return in meters of tread per second
   */
  public double getVelocity() {
    return inputs.speed;
  }

  public void setVoltage(double voltage) {
    Logger.recordOutput("Intake/Voltage", voltage);
    io.setVoltage(voltage);
  }

  public double getFilteredCurrent() {
    return medianCurrent;
  }
}
