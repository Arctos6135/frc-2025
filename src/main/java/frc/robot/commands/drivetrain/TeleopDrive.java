package frc.robot.commands.drivetrain;

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

  @Override
  public void execute() {
    swerveDrive.driveFieldOriented(
        new ChassisSpeeds(
            MathUtils.nearZero(controller.getLeftY()) * maxSpeed,
            MathUtils.nearZero(controller.getLeftX()) * maxSpeed,
            MathUtils.nearZero(controller.getRightX()) * maxRotationalSpeed));
  }
}
