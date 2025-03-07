package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.drivetrain.CenterDrivetrain;
import frc.robot.commands.drivetrain.MakeNormal;
import frc.robot.commands.drivetrain.ResetGyro;
import frc.robot.commands.drivetrain.SmashTag;
import frc.robot.commands.drivetrain.TeleopDrive;
import frc.robot.commands.elevator.ElevatorPositionSet;
import frc.robot.commands.elevator.ManualElevator;
import frc.robot.commands.intake.IntakeMove;
import frc.robot.commands.intake.IntakePiece;
import frc.robot.commands.outtake.ManualOuttake;
import frc.robot.commands.outtake.OuttakeSpin;
import frc.robot.commands.outtake.QuickOuttake;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.ElevatorConstants;
import frc.robot.constants.IntakeConstants;
import frc.robot.constants.OuttakeConstants;
import frc.robot.constants.PositionConstants;
import frc.robot.constants.VisionConstants;
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
import frc.robot.subsystems.vision.Vision;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class RobotContainer {
  public final XboxController driverController =
      new XboxController(ControllerConstants.DRIVER_CONTROLLER);
  public final XboxController operatorController =
      new XboxController(ControllerConstants.OPERATOR_CONTROLLER);

  public SendableChooser<Command> autoChooser;
  // public LoggedDashboardChooser<Command> autoChooser;
  public LoggedDashboardChooser<Pose2d> positionChooser;

  public final Drivetrain drivetrain =
      new Drivetrain(new File(Filesystem.getDeployDirectory(), "swerve"));
  public final Intake intake;
  public final Outtake outtake;
  public final Elevator elevator;
  public final Vision vision;
  // public final DigitalInput beambreak;

  public final TeleopDrive teleopDrive;

  public RobotContainer() {
    if (RobotBase.isReal()) {
      this.intake = new Intake(new IntakeIOReal());
      this.elevator = new Elevator(new ElevatorIOReal());
      this.outtake = new Outtake(new OuttakeIOReal());
      this.vision = new Vision(VisionConstants.LIMELIGHT_NAME);

    } else {
      this.intake = new Intake(new IntakeIOSim());
      this.elevator = new Elevator(new ElevatorIOSim());
      this.outtake = new Outtake(new OuttakeIOSim());
      this.vision = new Vision(VisionConstants.LIMELIGHT_NAME);
    }
    // beambreak = outtake.beambreak;

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    drivetrain.setDefaultCommand(teleopDrive);
    elevator.setDefaultCommand(new ManualElevator(elevator, operatorController));

    configureAuto();
    configureBindings();
  }

  private void configureBindings() {
    Trigger driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
    Trigger driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
    Trigger driverB = new JoystickButton(driverController, XboxController.Button.kB.value);

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

    driverA.whileTrue(
        new MakeNormal(drivetrain, vision)
            .andThen(
                new CenterDrivetrain(drivetrain, vision)
                    .andThen(new SmashTag(drivetrain, vision))));

    // driverB.whileTrue(new BeambreakTest(outtake));

    // operatorA.whileTrue(new QuickOuttake(outtake));
    operatorDpadDown.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    operatorDpadLeft.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    operatorDpadRight.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    operatorDpadUp.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    operatorX.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.HANDOFF_HEIGHT));
    operatorA.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    operatorB.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    // operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    operatorLeftBumper.whileTrue(new IntakeMove(intake, () -> -IntakeConstants.INTAKE_RPS));
    operatorRightBumper.whileTrue(new OuttakeSpin(outtake, () -> -OuttakeConstants.OUTTAKE_RPS));

    operatorLeftTrigger.whileTrue(
        new IntakeMove(
            intake, () -> operatorController.getLeftTriggerAxis() * IntakeConstants.INTAKE_RPS));
    operatorRightTrigger.whileTrue(new ManualOuttake(outtake, operatorController));

    /* Reset Gyro QOL */
    Command delayGyroFix = new WaitCommand(2);
    delayGyroFix.addRequirements(drivetrain);
    Command resetGyroCommand =
        new InstantCommand(() -> drivetrain.swerveDrive.zeroGyro(), drivetrain)
            .andThen(delayGyroFix);
    resetGyroCommand.setName("ResetGyro");
    driverX.onTrue(resetGyroCommand);
    new OuttakeSpin(
        outtake, () -> operatorController.getRightTriggerAxis() * OuttakeConstants.OUTTAKE_RPS);

    /* Smart Dashboard */
    SmartDashboard.putData("Zero Gyro", new ResetGyro(drivetrain));
    SmartDashboard.putData(
        "Zero Encoder", new InstantCommand(() -> elevator.zeroEncoderPosition(), elevator));
  }

  private void configureAuto() {
    // autoChooser = new LoggedDashboardChooser<Command>("auto chooser");
    positionChooser = new LoggedDashboardChooser<Pose2d>("position chooser");
    positionChooser.addOption("red red", PositionConstants.RED_RED);
    positionChooser.addOption("red middle", PositionConstants.RED_MIDDLE);
    positionChooser.addOption("red blue", PositionConstants.RED_BLUE);

    positionChooser.addOption("blue red", PositionConstants.BLUE_RED);
    positionChooser.addOption("blue middle", PositionConstants.BLUE_MIDDLE);
    positionChooser.addOption("blue blue", PositionConstants.BLUE_BLUE);

    NamedCommands.registerCommand(
        "elevatorL2", new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorL3", new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorL4", new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorHandoffHeight",
        new ElevatorPositionSet(elevator, ElevatorConstants.HANDOFF_HEIGHT));
    NamedCommands.registerCommand(
        "elevatorDown", new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    NamedCommands.registerCommand("intakePiece", IntakePiece.badIntakePiece(intake, outtake));
    NamedCommands.registerCommand("beambreakIntake", IntakePiece.badIntakePiece(intake, outtake));
    // "beambreakIntake", IntakePiece.beambreakIntake(intake, outtake, beambreak));
    NamedCommands.registerCommand("outtakePiece", new QuickOuttake(outtake));

    autoChooser = AutoBuilder.buildAutoChooser();
    // autoChooser.addOption("StartA_F1_D2", new PathPlannerAuto("A_F1_D2"));

    SmartDashboard.putData("Auto Chooser", autoChooser);
    //TODO: I think autos are rotated, not just flipped, we might have to rename again
  }

  public void startMatch() {}

  public Command getAutonomousCommand() {
    // return autoChooser.get();
    drivetrain.swerveDrive.resetOdometry(positionChooser.get());
    return autoChooser.getSelected();
  }
}
