package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class DrivetrainInputsAutoLogged extends DrivetrainIO.DrivetrainInputs
    implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {}

  @Override
  public void fromLog(LogTable table) {}

  public DrivetrainInputsAutoLogged clone() {
    DrivetrainInputsAutoLogged copy = new DrivetrainInputsAutoLogged();
    return copy;
  }
}
