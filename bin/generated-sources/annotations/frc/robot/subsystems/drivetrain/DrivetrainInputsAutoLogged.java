package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class DrivetrainInputsAutoLogged extends Drivetrain.DrivetrainInputs
    implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("DriveVoltage", driveVoltage);
    table.put("AngleVoltage", angleVoltage);
  }

  @Override
  public void fromLog(LogTable table) {
    driveVoltage = table.get("DriveVoltage", driveVoltage);
    angleVoltage = table.get("AngleVoltage", angleVoltage);
  }

  public DrivetrainInputsAutoLogged clone() {
    DrivetrainInputsAutoLogged copy = new DrivetrainInputsAutoLogged();
    copy.driveVoltage = this.driveVoltage;
    copy.angleVoltage = this.angleVoltage;
    return copy;
  }
}
