package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import swervelib.SwerveDrive;

public class Drivetrain extends SubsystemBase {
  private final DrivetrainIO io;
  public final SwerveDrive swerveDrive;

  private final DrivetrainInputsAutoLogged inputs = new DrivetrainInputsAutoLogged();

  public Drivetrain(DrivetrainIO io) {
    this.io = io;
    this.swerveDrive = io.swerveDrive;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("Drivetrain", inputs);
  }
}
