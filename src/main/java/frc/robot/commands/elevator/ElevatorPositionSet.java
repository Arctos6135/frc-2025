package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;

public class ElevatorPositionSet extends Command {
  public double setpoint;
  public double endTime = 0; // in seconds
  public Elevator elevator;

  public ElevatorPositionSet(Elevator elevator, Double setpoint) {
    this.elevator = elevator;
    this.setpoint = setpoint;

    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + 1000000;
    elevator.setPosition(setpoint);
  }

  @Override
  public void execute() {
    elevator.setVoltage(elevator.calculatePID());

    if (elevator.atSetpoint()) {
      endTime = Timer.getFPGATimestamp() + 0.5; //TODO: test this, i think it shouldnt change
    } else {
      endTime = Timer.getFPGATimestamp() + 1000000;
    }
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }
}
