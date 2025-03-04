package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;

public class ElevatorPositionSet extends Command {
  public double setpoint;
  public Elevator elevator;

  public ElevatorPositionSet(Elevator elevator, Double setpoint) {
    this.elevator = elevator;
    this.setpoint = setpoint;

    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    elevator.setPosition(setpoint);
  }

  @Override
  public void execute() {
    elevator.setVoltage(elevator.calculatePID());
  }

  @Override
  public boolean isFinished() {
    return elevator.atSetpoint();
  }
}
