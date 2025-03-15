package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.outtake.Outtake;

public class ManualOuttake extends Command {
  private Outtake outtake;
  private XboxController controller;

  public ManualOuttake(Outtake outtake, XboxController controller) {
    this.outtake = outtake;
    this.controller = controller;

    addRequirements(outtake);
  }

  @Override
  public void execute() {
    outtake.setRPS(40 * (controller.getRightTriggerAxis()));
  }

  @Override
  public void end(boolean interrupted) {
    outtake.setRPS(0);
  }
}
