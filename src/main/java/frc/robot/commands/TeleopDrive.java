package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import java.util.function.DoubleSupplier;
import swervelib.math.SwerveMath;

public class TeleopDrive extends Command {
  private XboxController controller;
  private Drivetrain drivetrain;

  public TeleopDrive(Drivetrain drivetrain, XboxController controller) {
    this.controller = controller;
    this.drivetrain = drivetrain;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.swerveDrive.drive(
        SwerveMath.scaleTranslation(
            new Translation2d(
                controller.getRawAxis(XboxController.Axis.kLeftX.value) * drivetrain.swerveDrive.getMaximumChassisVelocity(),
                controller.getRawAxis(XboxController.Axis.kLeftY.value) * drivetrain.swerveDrive.getMaximumChassisVelocity()),
            0.8),
        Math.pow(controller.getRawAxis(XboxController.Axis.kRightX.value), 3) * drivetrain.swerveDrive.getMaximumChassisAngularVelocity(),
        true,
        false);
  }
}
