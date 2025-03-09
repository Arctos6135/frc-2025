package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.SwerveConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utils.MathUtils;
import frc.robot.utils.SlewRateLimiter;
import swervelib.SwerveDrive;

public class TeleopDrive extends Command {
  private XboxController controller;
  public Drivetrain drivetrain;
  public SwerveDrive swerveDrive;
  public SlewRateLimiter rateLimiter;
  public double[] rates;

  private final double maxSpeed;
  private final double maxRotationalSpeed;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;
    this.swerveDrive = drivetrain.swerveDrive;
    this.rateLimiter = new SlewRateLimiter(3.0);

    if (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue) {
      this.maxSpeed = SwerveConstants.MAX_SPEED * -1;
    } else {
      this.maxSpeed = SwerveConstants.MAX_SPEED;
    }
    this.maxRotationalSpeed = swerveDrive.getMaximumChassisAngularVelocity();

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    rates =
        rateLimiter.limit(
            MathUtils.nearZero(controller.getLeftY()) * maxSpeed,
            MathUtils.nearZero(controller.getLeftX()) * maxSpeed);
    drivetrain.swerveDrive.driveFieldOriented(
        new ChassisSpeeds(
            rates[0], rates[1], MathUtils.nearZero(controller.getRightX()) * maxRotationalSpeed));
  }
}
