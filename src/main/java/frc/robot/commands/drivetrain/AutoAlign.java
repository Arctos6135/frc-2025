package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.Vision;

public class AutoAlign extends Command {
  private final Drivetrain drivetrain;
  private final Vision vision;
  private Pose3d aprilTagPose;
  private double kP = 0.01;

  public AutoAlign(Drivetrain drivetrain, Vision vision) {
    this.drivetrain = drivetrain;
    this.vision = vision;
  }

  public void execute() {
    vision.updateInputs();
    aprilTagPose = vision.getAprilTagPose();

    drivetrain.swerveDrive.drive(
        ChassisSpeeds.discretize(
            aprilTagPose.getX() * kP,
            aprilTagPose.getY() * kP,
            aprilTagPose.getRotation().getZ() * kP,
            0.02));
  }
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

  // if (!aligned) {
  // if (Math.abs(Math.abs(currentSkew) - 45) > 1.0 || currentSkew == 0.0) {
  //   drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0, currentSkew * 0.01, 0.02));
  // } else if (Math.abs(t2darray[4]) > 0.5) {
  //   drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, t2darray[4] * 0.1, 0, 0.02));
  // } else {
  //   aligned = true;
  // }

  @Override
  public boolean isFinished() {
    return (Math.abs(aprilTagPose.getX()) < 0.1
        && Math.abs(aprilTagPose.getY()) < 0.5
        && Math.abs(aprilTagPose.getRotation().getZ()) < 0.5);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0.0, 0, 0.02));
  }
}
