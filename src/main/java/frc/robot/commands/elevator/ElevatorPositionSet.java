package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;

public class ElevatorPositionSet extends Command {
  public double setpoint;
  public double endTime = 0;
  public Elevator elevator;

  public ElevatorPositionSet(Elevator elevator, Double setpoint) {
    this.elevator = elevator;
    this.setpoint = setpoint;

    addRequirements(elevator);
  }

  // Sets the elevator setpoint to their corresponding scoring height.

  public void initialise() {
    elevator.setPosition(setpoint);
    endTime = 0;
  }

  @Override
  public void execute() {
    elevator.setVoltage(elevator.calculatePID());
    if (elevator.atSetpoint()) {
      endTime++;
    } else {
      endTime = 0;
    }
  }

  @Override
  public boolean isFinished() {
    return endTime >= 25;
  }
}
