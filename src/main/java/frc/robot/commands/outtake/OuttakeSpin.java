package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.outtake.Outtake;


public class OuttakeSpin extends Command{

  private Outtake outtake;

  public OuttakeSpin(Outtake outtake) {
    this.outtake = outtake;
  }
  
  @Override
  public void initialize() {
    outtake.setRPS(IntakeConstants.INTAKE_RPS);
  }

  @Override
  public void end(boolean i) {
    outtake.setRPS(0);
  }


}
