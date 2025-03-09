package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public class ElevatorIO {
  @AutoLog
  public static class ElevatorInputs {
    public double position;

    // Radians per second
    public double velocity;

    // Amps
    public double current;

    // Celsius
    public double temperature;

    // % Max Volts
    public double voltage;
  }

  public void updateInputs(ElevatorInputs inputs) {}

  public void setVoltage(double voltage) {}

  public void zeroEncoderPosition() {}
}
