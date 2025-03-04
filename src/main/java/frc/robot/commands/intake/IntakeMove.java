package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.intake.Intake;

public class IntakeMove extends Command {

  private final Intake intake;
  private final boolean reversed;

  public IntakeMove(Intake intake, boolean reversed) {
    this.reversed = reversed;
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    if (reversed) {
      intake.setRPS(-IntakeConstants.INTAKE_RPS);
    } else {
      intake.setRPS(IntakeConstants.INTAKE_RPS);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean i) {
    intake.setRPS(0);
    intake.setVoltage(0);
  }
}
