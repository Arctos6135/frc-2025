package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.outtake.Outtake;

public class BeambreakTest extends Command {
  private Outtake outtake;

  public BeambreakTest(Outtake outtake) {
    this.outtake = outtake;
  }

  @Override
  public void execute() {
    if (
    /*outtake.beambreak.get()*/ false) {
      outtake.setVoltage(2);
    } else {
      outtake.setVoltage(0);
    }
  }
}
