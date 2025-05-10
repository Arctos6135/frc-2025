package frc.robot.constants;

import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

public class SwerveConstants {
  public static final double MAX_SPEED =
      0.4; // TODO we might need to change this or pathplanners top speed so they match

  public static final double RESET_GYRO_DELAY = 3; // in seconds

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
