package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.LimelightHelpers;
import frc.robot.subsystems.vision.Vision;
import swervelib.SwerveDrive;

public class CenterDrivetrain extends Command {
    private final Drivetrain drivetrain;
  private final Vision vision;
  private Pose3d aprilTagPose;
  private double kP = 0.01;

  public CenterDrivetrain(Drivetrain drivetrain, Vision vision) {
    this.drivetrain = drivetrain;
    this.vision = vision;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, vision.getTX() * kP, 0, 0.02));
  }

  @Override
  public boolean isFinished() {
    return (Math.abs(vision.getTX()) < 1);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0, 0, 0.02));
  }
}
