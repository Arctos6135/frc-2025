package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.elevator.Elevator;

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
    // elevator.setVoltage(elevator.feedForward.calculate(operatorController.getLeftY() * ElevatorConstants.ELEVATOR_MAX_SPEED, 0));
    elevator.setVoltage(Math.pow(operatorController.getLeftY(), 3) * 4);
  }

  @Override
  public void end(boolean i) {
    elevator.setVoltage(0);
  }
}
