package frc.robot.constants;

import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public class SwerveConstants {
  // This is a very good max speed
  public static final double MAX_SPEED =
      4; // TODO we might need to change this or pathplanners top speed so they match

  public static final double RESET_GYRO_DELAY = 3; // in seconds

  public static final HolonomicDriveController TELEOP_SWERVE_DRIVE_CONTROLLER =
      new HolonomicDriveController(
          new PIDController(1, 0, 0),
          new PIDController(1, 0, 0),
          new ProfiledPIDController(1, 0, 0, new Constraints(SwerveConstants.MAX_SPEED, 1)));

  public static final PPHolonomicDriveController AUTO_SWERVE_DRIVE_CONTROLLER =
      new PPHolonomicDriveController(
          // PPHolonomicController is the built in path following controller for holonomic drive
          // trains
          new PIDConstants(5.0, 0.0, 0.0),
          // Translation PID constants
          new PIDConstants(5.0, 0.0, 0.0)
          // Rotation PID constants
          );
}
