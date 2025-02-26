package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public class IntakeIO {
  @AutoLog
  public static class IntakeInputs {
    public double leftCurrent; // amps
    public double leftTemperature; // celsius
    public double leftVoltage; // % max voltage
    public double leftSpeed; // in meters of tread / second

    public double rightCurrent;
    public double rightTemperature;
    public double rightVoltage;
    public double rightSpeed;
  }

  public void updateInputs(IntakeInputs inputs) {}

  public void setVoltage(double voltage) {}
}
