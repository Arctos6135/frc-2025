package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.Outtake;

public class OuttakeSpin extends Command {
  private Outtake outtake;

  public OuttakeSpin(Outtake outtake) {
    this.outtake = outtake;

    addRequirements(outtake);
  }

  @Override
  public void execute() {
    outtake.setRPS(OuttakeConstants.OUTTAKE_RPS);
  }

  /**
   * Must be interrupted to turn off
   */
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    outtake.setRPS(0);
    outtake.setVoltage(0);
  }
}
