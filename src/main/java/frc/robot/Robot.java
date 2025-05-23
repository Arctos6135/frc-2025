// Copyright 2021-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.net.WebServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.vision.LimelightHelpers;
import frc.robot.subsystems.vision.LimelightHelpers.LimelightResults;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends LoggedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private LimelightResults limelightResults;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    LimelightHelpers.setCameraPose_RobotSpace(
        "",
        VisionConstants.FORWARD_OFFSET,
        VisionConstants.SIDE_OFFSET,
        VisionConstants.UP_OFFSET,
        VisionConstants.ROLL_OFFSET,
        VisionConstants.PITCH_OFFSET,
        VisionConstants.YAW_OFFSET);
    LimelightHelpers.setLEDMode_ForceOff("");
    // Set up data receivers & replay source
    if (isReal()) {
      // Running on a real robot, log to a USB stick ("/U/logs")
      // Logger.addDataReceiver(new WPILOGWriter());
      // Logger.addDataReceiver(new NT4Publisher());
    } else if (isSimulation()) {
      // Running a physics simulator, log to NT
      Logger.addDataReceiver(new NT4Publisher());
    } else {
      // Replaying a log, set up replay source
      setUseTiming(false); // Run as fast as possible
      String logPath = LogFileUtil.findReplayLog();
      Logger.setReplaySource(new WPILOGReader(logPath));
      Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));
    }

    WebServer.start(5801, Filesystem.getDeployDirectory().getPath());
    // Start AdvantageKit logger
    Logger.start();
  }

  /** This function is called periodically during all modes. */
  @Override
  public void robotPeriodic() {
    // m_robotContainer.vision.update();
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Match Time", Timer.getMatchTime());
    // m_robotContainer.drivetrain.swerveDrive.addVisionMeasurement(
    //     m_robotContainer.vision.getVisionPose(), m_robotContainer.vision.getTimestamp());
    // m_robotContainer.drivetrain.swerveDrive.addVisionMeasurement(
    //     LimelightHelpers.getBotPose2d_wpiBlue(""),
    //     LimelightHelpers.getLatestResults("").timestamp_RIOFPGA_capture);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_robotContainer.startMatch();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    if (DriverStation.getAlliance().get() == Alliance.Red) {
      m_robotContainer.teleopDrive.maxSpeed = 4.0;
    } else {
      m_robotContainer.teleopDrive.maxSpeed = -4.0;
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
