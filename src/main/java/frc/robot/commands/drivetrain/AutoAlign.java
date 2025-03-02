package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.LimelightHelpers;

public class AutoAlign extends Command {
  private final Drivetrain drivetrain;
  boolean aligned;
  double currentSkew;
  private double[] t2darray;

  public AutoAlign(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    currentSkew = 0.0;
  }

  @Override
  public void initialize() {
    aligned = false;
  }

  public void execute() {
    if (LimelightHelpers.getTV(VisionConstants.LIMELIGHT_NAME)) {
      t2darray = LimelightHelpers.getT2DArray(VisionConstants.LIMELIGHT_NAME);

      currentSkew = t2darray[16];
      currentSkew = currentSkew % 90 - 45;
      // currentSkew *= -1;
      // System.out.println("longSide " + t2darray[12]);
      // System.out.println("shortSide " + t2darray[13]);
      // System.out.println("horizontal " + t2darray[14]);
      // System.out.println("vertical " + t2darray[15]);
      // System.out.println("skew " + t2darray[16]);
      // System.out.println("");

      // if (Math.abs(t2darray[16] - 90) > 5) {
      //   if (t2darray[16] > 45) {
      //     t2darray[16] = t2darray[16] - 90;

      if (Math.abs(currentSkew) - 45 > 1 || currentSkew == 0.0) {
        drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0, currentSkew * 0.01, 0.02));
      } else if (Math.abs(t2darray[4]) > 1) {
        drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, t2darray[4] * 0.1, 0, 0.02));
      } else {
        end(false);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {}
}
