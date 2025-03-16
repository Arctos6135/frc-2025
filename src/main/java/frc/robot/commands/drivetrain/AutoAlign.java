package frc.robot.commands.drivetrain;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.PositionConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.LimelightHelpers;
import frc.robot.subsystems.vision.Vision;
import java.util.List;
import swervelib.SwerveDrive;

public class AutoAlign extends Command {
  private Boolean left;
  private SwerveDrive swerveDrive;
  private Vision vision;
  private Pose2d targetPose;
  private HolonomicDriveController swerveController;
  private Trajectory trajectory;
  private double initialTime;

  public AutoAlign(Drivetrain drivetrain, Vision vision, Boolean left) {
    this.left = left;
    this.swerveDrive = drivetrain.swerveDrive;
    this.vision = vision;

    swerveController =
        new HolonomicDriveController(
            new PIDController(5, 0, 0),
            new PIDController(5, 0, 0),
            new ProfiledPIDController(
                5, 0, 0, new Constraints(swerveDrive.getMaximumChassisAngularVelocity(), 2.0)));
    swerveController.setTolerance(new Pose2d(0.01, 0.01, Rotation2d.fromDegrees(0.01)));
  }

  @Override
  public void initialize() {
    vision.update();
    if (!LimelightHelpers.getTV("")) {
      cancel();
    }

    // if (LimelightHelpers.getBotPose2d_wpiBlue("") != Pose2d.kZero) {
    //   swerveDrive.resetOdometry(LimelightHelpers.getBotPose2d_wpiBlue(""));
    // } else {
    //   cancel();
    // }
    // I'm commenting this out because resetting odometry means were facing the wrong way after.
    // There should be a way to do this this that works though.

    if (DriverStation.getAlliance().get() == Alliance.Red) {
      targetPose = swerveDrive.getPose().nearest(PositionConstants.RED_SCORING_POSES);
    } else {
      targetPose = swerveDrive.getPose().nearest(PositionConstants.BLUE_SCORING_POSES);
    }

    if (left) {
      targetPose = targetPose.plus(new Transform2d(0.0, 0.18, Rotation2d.fromDegrees(0)));
    } else {
      targetPose = targetPose.plus(new Transform2d(0.0, -0.18, Rotation2d.fromDegrees(0)));
    }

    trajectory =
        TrajectoryGenerator.generateTrajectory(
            swerveDrive.getPose(),
            List.of(
                new Translation2d(
                    (swerveDrive.getPose().getX() + targetPose.getX()) / 2,
                    (swerveDrive.getPose().getY() + targetPose.getY()) / 2)),
            targetPose,
            new TrajectoryConfig(swerveDrive.getMaximumChassisVelocity(), 2.0));
    initialTime = Timer.getFPGATimestamp();
    swerveDrive.postTrajectory(trajectory);
  }

  @Override
  public void execute() {
    swerveDrive.drive(
        swerveController.calculate(
            swerveDrive.getPose(),
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
