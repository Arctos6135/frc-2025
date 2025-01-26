package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public class ElevatorIO {
  @AutoLog
  public static class ElevatorInputs {
    public double leftPosition;
    public double rightPosition;

    // Radians per second
    public double leftVelocity;
    public double rightVelocity;

    // Amps
    public double rightCurrent;
    public double leftCurrent;

    // Celsius
    public double rightTemperature;
    public double leftTemperature;

    // % Max Volts
    public double rightVoltage;
    public double leftVoltage; 
  }

  public void updateInputs(ElevatorInputs inputs) {}

  public void setVoltage(double voltage) {}
}
