package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.utils.MathUtils;

public class ManualElevator extends Command {
  private final Elevator elevator;
  private final XboxController operatorController;

  public ManualElevator(Elevator elevator, XboxController operatorController) {
    this.elevator = elevator;
    this.operatorController = operatorController;

    addRequirements(elevator);
  }

  @Override
  public void execute() {
    elevator.setPosition(
        elevator.pidController.getSetpoint()
            - MathUtils.nearZero(operatorController.getLeftY() / 10) / 100.0);
  }

  @Override
  public void end(boolean i) {
    elevator.setVoltage(0);
  }
}
