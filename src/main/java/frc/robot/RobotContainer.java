package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ElevatorDefault;
import frc.robot.commands.TeleopDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorIOReal;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeIOReal;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class RobotContainer {
  public final XboxController driverController =
      new XboxController(ControllerConstants.DRIVER_CONTROLLER);
  public final XboxController operatorController =
      new XboxController(ControllerConstants.OPERATOR_CONTROLLER);

  public LoggedDashboardChooser<Command> autoChooser;
  public LoggedDashboardChooser<Pose2d> positionChooser;

  public final Drivetrain drivetrain =
      new Drivetrain(new File(Filesystem.getDeployDirectory(), "swerve"));
  public final Intake intake;
  public final Elevator elevator;

  public final TeleopDrive teleopDrive;
  public final ElevatorDefault elevatorDefault;

  public RobotContainer() {
    if (RobotBase.isReal()) {
      this.intake = new Intake(new IntakeIOReal());
      this.elevator = new Elevator(new ElevatorIOReal());
    } else {
      this.intake = new Intake(new IntakeIO());
      this.elevator = new Elevator(new ElevatorIO());
    }

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    elevatorDefault = new ElevatorDefault(elevator);

    drivetrain.setDefaultCommand(teleopDrive);
    elevator.setDefaultCommand(elevatorDefault);

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
