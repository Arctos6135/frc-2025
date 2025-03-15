package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utils.SlewRateLimiter;
import swervelib.SwerveDrive;

public class TeleopDrive extends Command {
  private XboxController controller;
  public Drivetrain drivetrain;
  public SwerveDrive swerveDrive;
  public SlewRateLimiter rateLimiter;
  public double[] rates;

  public double maxSpeed;
  public double maxRotationalSpeed;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;
    this.swerveDrive = drivetrain.swerveDrive;
    this.rateLimiter = new SlewRateLimiter(1.0);
    this.maxSpeed = 4.0;
    this.maxRotationalSpeed = -swerveDrive.getMaximumChassisAngularVelocity();

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    rates =
        rateLimiter.limit(
            Math.pow(controller.getLeftY(), 3) * maxSpeed,
            Math.pow(controller.getLeftX(), 3) * maxSpeed);
    drivetrain.swerveDrive.driveFieldOriented(
        new ChassisSpeeds(
            rates[0], rates[1], Math.pow(controller.getRightX(), 3) * maxRotationalSpeed));
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.swerveDrive.drive(new ChassisSpeeds(0, 0, 0));
  }
}
