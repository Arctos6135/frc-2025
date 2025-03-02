package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.FollowPathCommand;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.drivetrain.AutoAlign;
import frc.robot.commands.drivetrain.TeleopDrive;
import frc.robot.commands.elevator.ElevatorPositionSet;
import frc.robot.commands.elevator.ManualElevator;
import frc.robot.commands.intake.IntakeMove;
import frc.robot.commands.intake.IntakePiece;
import frc.robot.commands.outtake.OuttakeSpin;
import frc.robot.commands.outtake.QuickOuttake;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIOReal;
import frc.robot.subsystems.elevator.ElevatorIOSim;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIOReal;
import frc.robot.subsystems.intake.IntakeIOSim;
import frc.robot.subsystems.outtake.Outtake;
import frc.robot.subsystems.outtake.OuttakeIOReal;
import frc.robot.subsystems.outtake.OuttakeIOSim;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class RobotContainer {
  public final XboxController driverController =
      new XboxController(ControllerConstants.DRIVER_CONTROLLER);
  public final XboxController operatorController =
      new XboxController(ControllerConstants.OPERATOR_CONTROLLER);

  public SendableChooser<Command> autoChooser;
  //public LoggedDashboardChooser<Command> autoChooser;
  public LoggedDashboardChooser<Pose2d> positionChooser;

  public final Drivetrain drivetrain =
      new Drivetrain(new File(Filesystem.getDeployDirectory(), "swerve"));
  public final Intake intake;
  public final Outtake outtake;
  public final Elevator elevator;
  public final DigitalInput beambreak = new DigitalInput(0);

  public final TeleopDrive teleopDrive;

  public RobotContainer() {
    if (RobotBase.isReal()) {
      this.intake = new Intake(new IntakeIOReal());
      this.elevator = new Elevator(new ElevatorIOReal());
      this.outtake = new Outtake(new OuttakeIOReal());
    } else {
      this.intake = new Intake(new IntakeIOSim());
      this.elevator = new Elevator(new ElevatorIOSim());
      this.outtake = new Outtake(new OuttakeIOSim());
    }

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    drivetrain.setDefaultCommand(teleopDrive);
    elevator.setDefaultCommand(new ManualElevator(elevator, operatorController));

    configureAuto();
    configureBindings();
  }

  private void configureBindings() {
    Trigger driverA = new JoystickButton(driverController, XboxController.Button.kA.value);

    Trigger operatorA = new JoystickButton(operatorController, XboxController.Button.kA.value);
    Trigger operatorB = new JoystickButton(operatorController, XboxController.Button.kB.value);
    Trigger operatorX = new JoystickButton(operatorController, XboxController.Button.kX.value);
    Trigger operatorY = new JoystickButton(operatorController, XboxController.Button.kY.value);

    Trigger operatorDpadDown = new Trigger(() -> operatorController.getPOV() == 180);
    Trigger operatorDpadUp = new Trigger(() -> operatorController.getPOV() == 0);
    Trigger operatorDpadRight = new Trigger(() -> operatorController.getPOV() == 90);
    Trigger operatorDpadLeft = new Trigger(() -> operatorController.getPOV() == 270);

    Trigger operatorLeftTrigger = new Trigger(() -> operatorController.getLeftTriggerAxis() > 0);
    Trigger operatorRightTrigger = new Trigger(() -> operatorController.getRightTriggerAxis() > 0);

    Trigger operatorLeftBumper =
        new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
    Trigger operatorRightBumper =
        new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

    driverA.whileTrue(new AutoAlign(drivetrain));

    operatorLeftBumper.onTrue(
        IntakePiece.badIntakePiece(
            intake, outtake)); // TODO: when we have beambreak on switch to the better command
    operatorRightBumper.whileTrue(new QuickOuttake(outtake));
    // operatorA.whileTrue(new QuickOuttake(outtake));
    operatorDpadDown.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L1_HEIGHT));
    operatorDpadLeft.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    operatorDpadRight.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    operatorDpadUp.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    operatorA.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.INTAKE_POSITION));
    operatorX.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.HANDOFF_HEIGHT));
    operatorB.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    operatorLeftTrigger.whileTrue(new IntakeMove(intake));
    operatorRightTrigger.whileTrue(
        new OuttakeSpin(outtake)); // TODO: make these changed based on how much its pressed?
  }

  private void configureAuto() {
    //autoChooser = new LoggedDashboardChooser<Command>("auto chooser");
    positionChooser = new LoggedDashboardChooser<Pose2d>("position chooser");

    NamedCommands.registerCommand(
        "elevatorL2", new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorL3", new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorL4", new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorIntakeHeight",
        new ElevatorPositionSet(elevator, ElevatorConstants.INTAKE_POSITION));
    NamedCommands.registerCommand("intakePiece", IntakePiece.badIntakePiece(intake, outtake));
    NamedCommands.registerCommand(
        "beambreakIntake", IntakePiece.beambreakIntake(intake, outtake, beambreak));
    NamedCommands.registerCommand("outtakePiece", new QuickOuttake(outtake));

    autoChooser = AutoBuilder.buildAutoChooser();
    // autoChooser.addOption("StartA_F1_D2", new PathPlannerAuto("A_F1_D2"));

    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public void startMatch() {}

  public Command getAutonomousCommand() {
    //return autoChooser.get();
    return autoChooser.getSelected();
  }
}
