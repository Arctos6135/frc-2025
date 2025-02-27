package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.Outtake;

public class OuttakeSpin extends Command {
  private Outtake outtake;

  public OuttakeSpin(Outtake outtake) {
    this.outtake = outtake;
  }

  @Override
  public void initialize() {
    outtake.setRPS(OuttakeConstants.OUTTAKE_RPS);
  }

  @Override
  public void end(boolean i) {
    outtake.setRPS(0);
    outtake.setVoltage(0);
  }
}
