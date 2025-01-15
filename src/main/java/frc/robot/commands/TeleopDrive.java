package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import java.util.function.DoubleSupplier;
import swervelib.math.SwerveMath;

public class TeleopDrive extends Command {
  private DoubleSupplier translationX;
  private DoubleSupplier translationY;
  private DoubleSupplier angularRotationX;
  private Drivetrain drivetrain;

  public TeleopDrive(Drivetrain drivetrain, DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX) {
    this.translationX = translationX;
    this.translationY = translationY;
    this.angularRotationX = angularRotationX;
    this.drivetrain = drivetrain;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.swerveDrive.drive(
        SwerveMath.scaleTranslation(
            new Translation2d(
                translationX.getAsDouble() * drivetrain.swerveDrive.getMaximumChassisVelocity(),
                translationY.getAsDouble() * drivetrain.swerveDrive.getMaximumChassisVelocity()),
            0.8),
        Math.pow(angularRotationX.getAsDouble(), 3) * drivetrain.swerveDrive.getMaximumChassisAngularVelocity(),
        true,
        false);
  }
}
