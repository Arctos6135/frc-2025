package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.Outtake;

public class OuttakeSpin extends Command {
  private final Outtake outtake;
  private final boolean reversed;

  public OuttakeSpin(Outtake outtake, boolean reversed) {
    this.outtake = outtake;
    this.reversed = reversed;

    addRequirements(outtake);
  }

  @Override
  public void initialize() {
    if (reversed) {
      outtake.setRPS(-OuttakeConstants.OUTTAKE_RPS);
    } else {
      outtake.setRPS(OuttakeConstants.OUTTAKE_RPS);
    }
  }

  /** Must be interrupted to turn off */
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
