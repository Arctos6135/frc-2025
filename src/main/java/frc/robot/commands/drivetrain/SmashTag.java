package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.Vision;

public class SmashTag extends Command {
  private final Drivetrain drivetrain;
  private final Vision vision;
  private Pose3d aprilTagPose;
  private double startTime;
  private double kP = 0.01;

  public SmashTag(Drivetrain drivetrain, Vision vision) {
    this.drivetrain = drivetrain;
    this.vision = vision;

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0.05, 0, 0, 0.02));
  }

  @Override
  public boolean isFinished() {
    return drivetrain.swerveDrive.getRobotVelocity().vxMetersPerSecond < 0.01
        && Timer.getFPGATimestamp() > startTime + 2;
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0, 0, 0.02));
  }
}
