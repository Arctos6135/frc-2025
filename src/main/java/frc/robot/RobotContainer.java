package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TeleopDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DrivetrainIO;
import frc.robot.subsystems.drivetrain.DrivetrainIOReal;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;

import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;

import java.io.File;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class RobotContainer {
  public final XboxController driverController =
      new XboxController(ControllerConstants.DRIVER_CONTROLLER);
  public final XboxController operatorController =
      new XboxController(ControllerConstants.OPERATOR_CONTROLLER);

  public LoggedDashboardChooser<Command> autoChooser;
  public LoggedDashboardChooser<Pose2d> positionChooser;

  public Drivetrain drivetrain;
  public TeleopDrive teleopDrive;
  public Intake intake;
  public Elevator elevator;

  public RobotContainer() {
    if (RobotBase.isReal()) {
      drivetrain = new Drivetrain(new DrivetrainIOReal(new File(Filesystem.getDeployDirectory(), "swerve")));
      intake = new Intake(new IntakeIO());
      elevator = new Elevator(new ElevatorIO());
    }

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    drivetrain.setDefaultCommand(teleopDrive);

    configureAuto();
    configureBindings();
  }

  private void configureBindings() {}

  private void configureAuto() {
    autoChooser = new LoggedDashboardChooser<Command>("auto chooser");
    positionChooser = new LoggedDashboardChooser<Pose2d>("position chooser");
  }

  public void startMatch() {}

  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}
