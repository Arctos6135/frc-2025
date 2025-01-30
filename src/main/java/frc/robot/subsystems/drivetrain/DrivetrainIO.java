package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.AutoLog;
import swervelib.SwerveDrive;

public class DrivetrainIO {
  public SwerveDrive swerveDrive;

  @AutoLog
  public static class DrivetrainInputs {
    /** An array of the measured state of each swerve module. */
    public SwerveModuleState[] measuredStates;
    /** An array of the desired state of each swerve module. */
    public SwerveModuleState[] desiredStates;
    /** The measured chassis speed of the robot. */
    public ChassisSpeeds measuredChassisSpeeds;
    /** The desired chassis speed of the robot. */
    public ChassisSpeeds desiredChassisSpeeds;
    /** The rotation of the robot. */
    public Rotation2d robotRotation;
  }

  public void updateInputs(DrivetrainInputs inputs) {}
}
