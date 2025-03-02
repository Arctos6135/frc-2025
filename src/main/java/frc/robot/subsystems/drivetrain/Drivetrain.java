package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.SwerveConstants;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.vision.LimelightHelpers;
import java.io.File;
import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.Logger;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class Drivetrain extends SubsystemBase {
  public final SwerveDrive swerveDrive;

  @AutoLog
  public static class DrivetrainInputs {
    /** The voltage of the front left drive motor. */
    public double driveVoltage;
    /** The voltage fo the front left angle motor. */
    public double angleVoltage;

    public double frontLeftEncoderPosition;
    public double frontRightEncoderPosition;
    public double backLeftEncoderPosition;
    public double backRightEncoderPosition;
  }

  public void updateInputs(DrivetrainInputs inputs) {
    inputs.driveVoltage =
        swerveDrive.swerveDriveConfiguration.modules[0].getDriveMotor().getVoltage();
    inputs.angleVoltage =
        swerveDrive.swerveDriveConfiguration.modules[0].getAngleMotor().getVoltage();

    inputs.frontLeftEncoderPosition =
        swerveDrive.swerveDriveConfiguration.modules[0].getAbsolutePosition();
    inputs.frontRightEncoderPosition =
        swerveDrive.swerveDriveConfiguration.modules[1].getAbsolutePosition();
    inputs.backLeftEncoderPosition =
        swerveDrive.swerveDriveConfiguration.modules[2].getAbsolutePosition();
    inputs.backRightEncoderPosition =
        swerveDrive.swerveDriveConfiguration.modules[3].getAbsolutePosition();

    // swerveDrive.addVisionMeasurement(
    // LimelightHelpers.getBotPose2d(VisionConstants.LIMELIGHT_NAME), Timer.getFPGATimestamp());
  }

  private final DrivetrainInputsAutoLogged inputs = new DrivetrainInputsAutoLogged();

  public Drivetrain(File directory) {
    SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
    try {
      this.swerveDrive = new SwerveParser(directory).createSwerveDrive(SwerveConstants.MAX_SPEED);
      // Alternative method if you don't want to supply the conversion factor via JSON files.
      // swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed,
      // angleConversionFactor, driveConversionFactor);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    swerveDrive.setHeadingCorrection(false);
    swerveDrive.setCosineCompensator(false);

    LimelightHelpers.setCameraPose_RobotSpace(
        VisionConstants.LIMELIGHT_NAME,
        VisionConstants.FORWARD_OFFSET,
        VisionConstants.SIDE_OFFSET,
        VisionConstants.UP_OFFSET,
        VisionConstants.ROLL_OFFSET,
        VisionConstants.PITCH_OFFSET,
        VisionConstants.YAW_OFFSET);
  }

  @Override
  public void periodic() {
    updateInputs(inputs);

    Logger.processInputs("Drivetrain", inputs);
  }
}
