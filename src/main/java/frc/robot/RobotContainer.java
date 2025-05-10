package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.drivetrain.ResetGyro;
import frc.robot.commands.drivetrain.TeleopDrive;
import frc.robot.constants.ControllerConstants;
import frc.robot.constants.PositionConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.vision.LimelightHelpers;
import java.io.File;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class RobotContainer {
  public final XboxController driverController =
      new XboxController(ControllerConstants.DRIVER_CONTROLLER);
  // public final XboxController operatorController = new
  // XboxController(ControllerConstants.OPERATOR_CONTROLLER);

  public SendableChooser<Command> autoChooser;
  // public LoggedDashboardChooser<Command> autoChooser;
  public LoggedDashboardChooser<Pose2d> positionChooser;

  public final Drivetrain drivetrain;
  // public final Intake intake;
  // public final Outtake outtake;
  // public final Elevator elevator;
  // public final Vision vision;
  // public final Vision vision;
  // public final DigitalInput beambreak = new DigitalInput(9);

  public final TeleopDrive teleopDrive;

  public RobotContainer() {
    this.drivetrain = new Drivetrain(new File(Filesystem.getDeployDirectory(), "swerve"));
    // if (RobotBase.isReal()) {
    //   this.intake = new Intake(new IntakeIOReal());
    //   this.elevator = new Elevator(new ElevatorIOReal());
    //   this.outtake = new Outtake(new OuttakeIOReal());
    //   this.vision = new Vision(new VisionIOReal());

    // } else {
    //   this.intake = new Intake(new IntakeIOSim());
    //   this.elevator = new Elevator(new ElevatorIOSim());
    //   this.outtake = new Outtake(new OuttakeIOSim());
    //   this.vision = new Vision(new VisionIOSim());
    // }

    teleopDrive = new TeleopDrive(drivetrain, driverController);
    drivetrain.setDefaultCommand(teleopDrive);
    // elevator.setDefaultCommand(new ManualElevator(elevator, operatorController));

    configureAuto();
    configureBindings();
  }

  private void configureBindings() {
    Trigger driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
    Trigger driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
    Trigger driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
    Trigger driverLeftBumper =
        new JoystickButton(driverController, XboxController.Button.kLeftBumper.value);
    Trigger driverRightBumper =
        new JoystickButton(driverController, XboxController.Button.kRightBumper.value);

    // Trigger operatorA = new JoystickButton(operatorController, XboxController.Button.kA.value);
    // Trigger operatorB = new JoystickButton(operatorController, XboxController.Button.kB.value);
    // Trigger operatorX = new JoystickButton(operatorController, XboxController.Button.kX.value);
    // Trigger operatorY = new JoystickButton(operatorController, XboxController.Button.kY.value);

    // Trigger operatorDpadDown = new Trigger(() -> operatorController.getPOV() == 180);
    // Trigger operatorDpadUp = new Trigger(() -> operatorController.getPOV() == 0);
    // Trigger operatorDpadRight = new Trigger(() -> operatorController.getPOV() == 90);
    // Trigger operatorDpadLeft = new Trigger(() -> operatorController.getPOV() == 270);

    // Trigger operatorLeftTrigger = new Trigger(() -> operatorController.getLeftTriggerAxis() > 0);
    // Trigger operatorRightTrigger = new Trigger(() -> operatorController.getRightTriggerAxis() >
    // 0);

    // Trigger operatorLeftBumper =
    //     new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
    // Trigger operatorRightBumper =
    //     new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

    // driverLeftBumper.whileTrue(new AutoAlign(drivetrain, vision, true));
    // driverRightBumper.whileTrue(new AutoAlign(drivetrain, vision, false));

    // driverA.whileTrue(
    //     new MakeNormal(drivetrain, vision)
    //         .andThen(
    //             new CenterDrivetrain(drivetrain, vision)
    //                 .andThen(new SmashTag(drivetrain, vision))));

    // driverB.whileTrue(new BeambreakTest(outtake));

    // operatorA.whileTrue(new QuickOuttake(outtake));
    // operatorDpadDown.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    // operatorDpadLeft.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    // operatorDpadRight.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    // operatorDpadUp.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));

    // operatorX.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.HANDOFF_HEIGHT));
    // operatorA.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    // operatorB.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    // operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    // operatorY.onTrue(new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));

    // operatorLeftBumper.whileTrue(
    //     new IntakeMove(intake, () -> -IntakeConstants.INTAKE_RPS, elevator));
    // operatorRightBumper.whileTrue(new OuttakeSpin(outtake, () -> -OuttakeConstants.OUTTAKE_RPS));

    // operatorLeftTrigger.whileTrue(
    //     new IntakeMove(
    //         intake,
    //         () -> operatorController.getLeftTriggerAxis() * IntakeConstants.INTAKE_RPS,
    //         elevator));
    // operatorRightTrigger.whileTrue(new ManualOuttake(outtake, operatorController));

    /* Reset Gyro QOL */
    Command delayGyroFix = new WaitCommand(2);
    delayGyroFix.addRequirements(drivetrain);
    Command resetGyroCommand =
        new InstantCommand(() -> drivetrain.swerveDrive.zeroGyro(), drivetrain)
            /* .andThen(new InstantCommand(() -> drivetrain.swerveDrive.resetOdometry()))
             * TODO figure out a way to reset the odometry?
             */
            .andThen(delayGyroFix);
    resetGyroCommand.setName("ResetGyro");
    driverX.onTrue(resetGyroCommand);

    /* Smart Dashboard */
    SmartDashboard.putData("Zero Gyro", new ResetGyro(drivetrain));
    // SmartDashboard.putData(
    //     "Zero Encoder", new InstantCommand(() -> elevator.zeroEncoderPosition(), elevator));

    SmartDashboard.putData(
        "reset to vision",
        new InstantCommand(
            () -> drivetrain.swerveDrive.resetOdometry(LimelightHelpers.getBotPose2d_wpiBlue(""))));
  }

  private void configureAuto() {

    // NamedCommands.registerCommand(
    //     "elevatorL2", new ElevatorPositionSet(elevator, ElevatorConstants.L2_HEIGHT));
    // NamedCommands.registerCommand(
    //     "elevatorL3", new ElevatorPositionSet(elevator, ElevatorConstants.L3_HEIGHT));
    // NamedCommands.registerCommand(
    //     "elevatorL4", new ElevatorPositionSet(elevator, ElevatorConstants.L4_HEIGHT));
    // NamedCommands.registerCommand(
    //     "elevatorHandoffHeight",
    //     new ElevatorPositionSet(elevator, ElevatorConstants.HANDOFF_HEIGHT));
    // NamedCommands.registerCommand(
    //     "elevatorDown", new ElevatorPositionSet(elevator, ElevatorConstants.ZERO));
    // NamedCommands.registerCommand("intakePiece", IntakePiece.badIntakePiece(intake, outtake));
    // NamedCommands.registerCommand("outtakePiece", new QuickOuttake(outtake));
    // NamedCommands.registerCommand("autoAlignLeft", new AutoAlign(drivetrain, vision, true));
    // NamedCommands.registerCommand("autoAlignRight", new AutoAlign(drivetrain, vision, false));

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);

    positionChooser = new LoggedDashboardChooser<Pose2d>("position chooser");
    positionChooser.addOption("Blue Right", PositionConstants.BLUE_RIGHT);
    positionChooser.addOption("Blue Middle", PositionConstants.BLUE_MIDDLE);
    positionChooser.addOption("Blue Left", PositionConstants.BLUE_LEFT);
    positionChooser.addOption("Red Right", PositionConstants.RED_RIGHT);
    positionChooser.addOption("Red Middle", PositionConstants.RED_MIDDLE);
    positionChooser.addOption("Red Left", PositionConstants.RED_LEFT);
  }

  public void startMatch() {}

  public Command getAutonomousCommand() {
    // return autoChooser.get();
    drivetrain.swerveDrive.resetOdometry(positionChooser.get());
    return autoChooser.getSelected();
  }
}
