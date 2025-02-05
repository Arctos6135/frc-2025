package frc.robot.subsystems.drivetrain;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class DrivetrainInputsAutoLogged extends Drivetrain.DrivetrainInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("DriveVoltages", driveVoltages);
    table.put("AngleVoltages", angleVoltages);
  }

  @Override
  public void fromLog(LogTable table) {
    driveVoltages = table.get("DriveVoltages", driveVoltages);
    angleVoltages = table.get("AngleVoltages", angleVoltages);
  }

  public DrivetrainInputsAutoLogged clone() {
    DrivetrainInputsAutoLogged copy = new DrivetrainInputsAutoLogged();
    copy.driveVoltages = this.driveVoltages.clone();
    copy.angleVoltages = this.angleVoltages.clone();
    return copy;
  }
}
