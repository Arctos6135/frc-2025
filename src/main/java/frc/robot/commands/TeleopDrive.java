package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import swervelib.SwerveDrive;

public class TeleopDrive extends Command {
  private XboxController controller;
  public Drivetrain drivetrain;
  public SwerveDrive swerveDrive;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;
    this.swerveDrive = drivetrain.swerveDrive;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    swerveDrive.drive(
        new Translation2d(
            controller.getLeftX() * swerveDrive.getMaximumChassisVelocity(),
            controller.getRightX() * swerveDrive.getMaximumChassisVelocity()),
        controller.getRightX() * swerveDrive.getMaximumChassisAngularVelocity(),
        true,
        false);
  }
}
