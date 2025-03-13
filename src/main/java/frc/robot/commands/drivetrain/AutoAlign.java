package frc.robot.commands.drivetrain;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.PositionConstants;
import frc.robot.constants.SwerveConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import java.util.List;
import swervelib.SwerveDrive;

public class AutoAlign extends Command {
  private XboxController controller;
  private SwerveDrive swerveDrive;
  private Pose2d targetPose;
  private HolonomicDriveController swerveController;
  private Trajectory trajectory;
  private double initialTime;

  public AutoAlign(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.swerveDrive = drivetrain.swerveDrive;

    swerveController = SwerveConstants.TELEOP_SWERVE_DRIVE_CONTROLLER;
  }

  @Override
  public void initialize() {
    if (DriverStation.getAlliance().get() == Alliance.Red) {
      targetPose =
          swerveDrive
              .swerveDrivePoseEstimator
              .getEstimatedPosition()
              .nearest(PositionConstants.RED_SCORING_POSES);
    } else {
      targetPose =
          swerveDrive
              .swerveDrivePoseEstimator
              .getEstimatedPosition()
              .nearest(PositionConstants.BLUE_SCORING_POSES);
    }

    if (controller.getLeftBumperButton() == true) {
      targetPose = targetPose.plus(new Transform2d(0.0, 0.15, Rotation2d.fromDegrees(0)));
    } else {
      targetPose = targetPose.plus(new Transform2d(0.0, -0.15, Rotation2d.fromDegrees(0)));
    }
    trajectory =
        TrajectoryGenerator.generateTrajectory(
            swerveDrive.getPose(),
            List.of(
                new Translation2d(
                    (swerveDrive.swerveDrivePoseEstimator.getEstimatedPosition().getX()
                            + targetPose.getX())
                        / 2,
                    (swerveDrive.swerveDrivePoseEstimator.getEstimatedPosition().getY()
                            + targetPose.getY())
                        / 2)),
            targetPose,
            new TrajectoryConfig(swerveDrive.getMaximumChassisVelocity(), 1.0));
    initialTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    swerveDrive.drive(
        swerveController.calculate(
            swerveDrive.swerveDrivePoseEstimator.getEstimatedPosition(),
            trajectory.sample(Timer.getFPGATimestamp() - initialTime),
            targetPose.getRotation()));
  }

  @Override
  public boolean isFinished() {
    return swerveController.atReference();
  }

  @Override
  public void end(boolean interrupted) {
    swerveDrive.drive(new ChassisSpeeds(0, 0, 0));
  }
}
