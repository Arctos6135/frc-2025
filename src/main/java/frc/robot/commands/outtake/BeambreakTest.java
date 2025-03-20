package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.outtake.Outtake;

public class BeambreakTest extends Command {

  public Outtake outtake;

  public BeambreakTest(Outtake outtake) {
    this.outtake = outtake;
  }

  @Override
  public void execute() {
    outtake.setRPM(outtake.getBeambreak() ? 5 : 0);
  }
}
