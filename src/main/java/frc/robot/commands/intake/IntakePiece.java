package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.constants.IntakeConstants;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.outtake.Outtake;

public class IntakePiece {

  public static void intakeOuttakeMove(
      Intake intake, double intakeRPS, Outtake outtake, double outtakeRPS) {
    intake.setRPS(intakeRPS);
    outtake.setRPS(intakeRPS);
  }

  public static Command badIntakePiece(Intake intake, Outtake outtake) {
    return new StartEndCommand(
            () ->
                intakeOuttakeMove(
                    intake, IntakeConstants.INTAKE_RPS, outtake, OuttakeConstants.OUTTAKE_RPS),
            () -> intakeOuttakeMove(intake, 0, outtake, 0),
            intake,
            outtake)
        .raceWith(new WaitCommand(IntakeConstants.INTAKE_PIECE_TIME));
  }

  public static Command beambreakIntake(Intake intake, Outtake outtake, DigitalInput beambreak) {
    return new StartEndCommand(
            () ->
                intakeOuttakeMove(
                    intake, IntakeConstants.INTAKE_RPS, outtake, OuttakeConstants.OUTTAKE_RPS),
            () -> intakeOuttakeMove(intake, 0, outtake, 0),
            intake,
            outtake)
        .raceWith(new WaitUntilCommand(() -> beambreak.get()));
  }
}
