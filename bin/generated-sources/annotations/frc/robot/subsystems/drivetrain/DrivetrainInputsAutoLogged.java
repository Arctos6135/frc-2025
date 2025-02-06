package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class DrivetrainInputsAutoLogged extends DrivetrainIO.DrivetrainInputs
    implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("MeasuredStates", measuredStates);
    table.put("DesiredStates", desiredStates);
    table.put("MeasuredChassisSpeeds", measuredChassisSpeeds);
    table.put("DesiredChassisSpeeds", desiredChassisSpeeds);
    table.put("RobotRotation", robotRotation);
  }

  @Override
  public void fromLog(LogTable table) {
    measuredStates = table.get("MeasuredStates", measuredStates);
    desiredStates = table.get("DesiredStates", desiredStates);
    measuredChassisSpeeds = table.get("MeasuredChassisSpeeds", measuredChassisSpeeds);
    desiredChassisSpeeds = table.get("DesiredChassisSpeeds", desiredChassisSpeeds);
    robotRotation = table.get("RobotRotation", robotRotation);
  }

  public DrivetrainInputsAutoLogged clone() {
    DrivetrainInputsAutoLogged copy = new DrivetrainInputsAutoLogged();
    copy.measuredStates = this.measuredStates.clone();
    copy.desiredStates = this.desiredStates.clone();
    copy.measuredChassisSpeeds = this.measuredChassisSpeeds;
    copy.desiredChassisSpeeds = this.desiredChassisSpeeds;
    copy.robotRotation = this.robotRotation;
    return copy;
  }
}
