package frc.robot.commands.drivetrain;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utils.MathUtils;
import swervelib.SwerveDrive;

public class TeleopDrive extends Command {
  private XboxController controller;
  public Drivetrain drivetrain;
  public SwerveDrive swerveDrive;
  public SlewRateLimiter xLimiter;
  public SlewRateLimiter yLimiter;
  public SlewRateLimiter thetaLimiter;

  private final double maxSpeed;
  private final double maxRotationalSpeed;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;
    this.swerveDrive = drivetrain.swerveDrive;
    this.xLimiter = new SlewRateLimiter(3.0);
    this.yLimiter = new SlewRateLimiter(3.0);

    this.maxSpeed = swerveDrive.getMaximumChassisVelocity();
    this.maxRotationalSpeed = swerveDrive.getMaximumChassisAngularVelocity();

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    swerveDrive.driveFieldOriented(
         ChassisSpeeds.fromFieldRelativeSpeeds(
            xLimiter.calculate(MathUtils.nearZero(controller.getLeftY()) * maxSpeed),
            yLimiter.calculate(MathUtils.nearZero(controller.getLeftX()) * maxSpeed),
            MathUtils.nearZero(controller.getRightX()) * maxRotationalSpeed,
            drivetrain.swerveDrive.getOdometryHeading()));
  }
}
