package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public class IntakeIO {
  @AutoLog
  public static class IntakeInputs {
    public double current; //amps
    public double temperature; // celsius
    public double voltage; // % max voltage
    public double speed; // in meters of tread / second
  }

  public void updateInputs(IntakeInputs inputs) {}

  public void setVoltage(double voltage) {}
}
