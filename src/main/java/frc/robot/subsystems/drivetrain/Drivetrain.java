package frc.robot.subsystems.drivetrain;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.SwerveConstants;
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
    double frontLeftAbsolutePosition;
    double frontRightAbsolutePosition;
    double backLeftAbsolutePosition;
    double backRightAbsolutePosition;
  }

  public void updateInputs(DrivetrainInputs inputs) {
    inputs.frontLeftAbsolutePosition = swerveDrive.getModules()[0].getAbsolutePosition();
    inputs.frontRightAbsolutePosition = swerveDrive.getModules()[1].getAbsolutePosition();
    inputs.backLeftAbsolutePosition = swerveDrive.getModules()[2].getAbsolutePosition();
    inputs.backRightAbsolutePosition = swerveDrive.getModules()[3].getAbsolutePosition();
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

    setupPathPlanner();
  }

  @Override
  public void periodic() {
    updateInputs(inputs);

    // if (LimelightHelpers.getTV(VisionConstants.LIMELIGHT_NAME)) {
    //   swerveDrive.addVisionMeasurement(
    //       LimelightHelpers.getBotPose2d(VisionConstants.LIMELIGHT_NAME),
    // Timer.getFPGATimestamp());
    // }

    Logger.processInputs("Drivetrain", inputs);
  }

  // You can tell I stole this code because its commented
  public void setupPathPlanner() {
    // Load the RobotConfig from the GUI settings. You should probably
    // store this in your Constants file
    RobotConfig config;
    try {
      config = RobotConfig.fromGUISettings();

      final boolean enableFeedforward = true;
      // Configure AutoBuilder last
      AutoBuilder.configure(
          swerveDrive::getPose,
          // Robot pose supplier
          swerveDrive::resetOdometry,
          // Method to reset odometry (will be called if your auto has a starting pose)
          swerveDrive::getRobotVelocity,
          // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
          (speedsRobotRelative, moduleFeedForwards) -> {
            if (enableFeedforward) {
              swerveDrive.drive(
                  speedsRobotRelative,
                  swerveDrive.kinematics.toSwerveModuleStates(speedsRobotRelative),
                  moduleFeedForwards.linearForces());
            } else {
              swerveDrive.setChassisSpeeds(speedsRobotRelative);
            }
          },
          // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally
          // outputs individual module feedforwards
          new PPHolonomicDriveController(
              // PPHolonomicController is the built in path following controller for holonomic drive
              // trains
              new PIDConstants(5.0, 0.0, 0.0),
              // Translation PID constants
              new PIDConstants(5.0, 0.0, 0.0)
              // Rotation PID constants
              ),
          config,
          // The robot configuration
          () -> {
            // Boolean supplier that controls when the path will be mirrored for the red alliance
            // This will flip the path being followed to the red side of the field.
            // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

            var alliance = DriverStation.getAlliance();
            if (alliance.isPresent()) {
              return alliance.get() == DriverStation.Alliance.Red;
            }
            return false;
          },
          this
          // Reference to this subsystem to set requirements
          );

    } catch (Exception e) {
      // Handle exception as needed
      e.printStackTrace();
    }
  }

  public Command getAutonomousCommand(String name) {
    return new PathPlannerAuto(name);
  }
}
