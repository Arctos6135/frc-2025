package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;
import swervelib.SwerveDrive;

public class DrivetrainIO {
  public SwerveDrive swerveDrive;

  @AutoLog
  public static class DrivetrainInputs {}

  public void updateInputs(DrivetrainInputs inputs) {}
}
