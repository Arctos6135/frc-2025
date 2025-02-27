package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.drivetrain.TeleopDrive;
import frc.robot.commands.elevator.ElevatorPositionSet;
import frc.robot.commands.elevator.ManualElevator;
import frc.robot.commands.outtake.OuttakeSpin;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIO;
import frc.robot.subsystems.elevator.ElevatorIOReal;
import frc.robot.subsystems.elevator.ElevatorIOSim;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.outtake.Outtake;
import frc.robot.subsystems.outtake.OuttakeIO;
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
  public final Outtake outtake;
  public final Elevator elevator;

  public final TeleopDrive teleopDrive;

  public RobotContainer() {
    if (RobotBase.isReal()) {
      this.intake = new Intake(new IntakeIO());
      this.elevator = new Elevator(new ElevatorIOReal());
      this.outtake = new Outtake(new OuttakeIO());
    } else {
      this.intake = new Intake(new IntakeIO());
      this.elevator = new Elevator(new ElevatorIOSim());
      this.outtake = new Outtake(new OuttakeIO());
    }

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    drivetrain.setDefaultCommand(teleopDrive);
    elevator.setDefaultCommand(new ManualElevator(elevator, operatorController));

    configureAuto();
    configureBindings();
  }

  private void configureBindings() {
    Trigger operatorRightBumper =
        new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);
    Trigger operatorA = new JoystickButton(operatorController, XboxController.Button.kA.value);
    Trigger operatorB = new JoystickButton(operatorController, XboxController.Button.kB.value);
    Trigger operatorX = new JoystickButton(operatorController, XboxController.Button.kX.value);
    Trigger operatorY = new JoystickButton(operatorController, XboxController.Button.kY.value);
    Trigger operatorDpadDown = new Trigger(() -> operatorController.getPOV() == 180);
    Trigger operatorDpadUp = new Trigger(() -> operatorController.getPOV() == 0);
    Trigger operatorDpadRight = new Trigger(() -> operatorController.getPOV() == 90);
    Trigger operatorDpadLeft = new Trigger(() -> operatorController.getPOV() == 270);

    operatorRightBumper.whileTrue(new OuttakeSpin(outtake));
    // operatorA.whileTrue(new QuickOuttake(outtake));
    operatorDpadDown.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.INTAKE_POSITION));
    operatorDpadLeft.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    operatorDpadRight.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    operatorDpadUp.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    operatorA.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.INTAKE_POSITION));
    operatorX.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    operatorB.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));
  }

  private void configureAuto() {
    autoChooser = new LoggedDashboardChooser<Command>("auto chooser");
    positionChooser = new LoggedDashboardChooser<Pose2d>("position chooser");
  }

  public void startMatch() {}

  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}
