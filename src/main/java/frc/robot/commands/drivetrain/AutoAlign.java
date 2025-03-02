package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.LimelightHelpers;

public class AutoAlign extends Command {
  private final Drivetrain drivetrain;

  public AutoAlign(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
  }

  @Override
  public void initialize() {}

  public void execute() {
    while (Math.abs(LimelightHelpers.getTX(VisionConstants.LIMELIGHT_NAME)) > 1) {
      drivetrain.swerveDrive.drive(
          ChassisSpeeds.discretize(
              0, 0, LimelightHelpers.getTX(VisionConstants.LIMELIGHT_NAME) * 0.1, 0.02));
    }
    

  }
}
