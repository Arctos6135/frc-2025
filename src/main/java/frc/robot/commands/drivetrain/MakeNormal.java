package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.Vision;

public class MakeNormal extends Command {
  private final Drivetrain drivetrain;
  private final Vision vision;
  private Pose3d aprilTagPose;
  private double kP = 0.01;

  public MakeNormal(Drivetrain drivetrain, Vision vision) {
    this.drivetrain = drivetrain;
    this.vision = vision;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.swerveDrive.drive(
        ChassisSpeeds.discretize(0, 0, (Math.abs(vision.getSkew() % 90 - 45) - 45) * 0.1, 0.02));
  }

  @Override
  public boolean isFinished() {
    return ((Math.abs(Math.abs(vision.getSkew() % 90 - 45) - 45) < 1.0)
        && Math.abs(Math.abs(vision.getSkew() % 90 - 45) - 45) != 0.0); // TODO maybe flips angles
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
  public void end(boolean interrupted) {
    drivetrain.swerveDrive.drive(ChassisSpeeds.discretize(0, 0.0, 0, 0.02));
  }
}
