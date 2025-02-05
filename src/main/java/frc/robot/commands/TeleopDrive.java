package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import swervelib.SwerveDrive;

public class TeleopDrive extends Command {
  private XboxController controller;
  public Drivetrain drivetrain;
  public SwerveDrive swerveDrive;

  private final double maxSpeed;
  private final double maxRotationalSpeed;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;
    this.swerveDrive = drivetrain.swerveDrive;

    this.maxSpeed = swerveDrive.getMaximumChassisVelocity();
    this.maxRotationalSpeed = swerveDrive.getMaximumChassisAngularVelocity();

    addRequirements(drivetrain);
  }

  public double nearZero(double num) {
    return Math.abs(num) > 0.05 ? num : 0;
  }

  @Override
  public void execute() {
    swerveDrive.driveFieldOriented(
        new ChassisSpeeds(
            nearZero(controller.getLeftX()) * maxSpeed,
            nearZero(controller.getLeftY()) * maxSpeed,
            nearZero(controller.getRightX()) * maxRotationalSpeed));
    //   swerveDrive.drive(
    //       new Translation2d(
    //           nearZero(controller.getLeftX()) * swerveDrive.getMaximumChassisVelocity(),
    //           nearZero(controller.getLeftY()) * swerveDrive.getMaximumChassisVelocity()),
    //       nearZero(controller.getRightX() * swerveDrive.getMaximumChassisAngularVelocity()),
    //       true,
    //       false);
    //
  }
}
