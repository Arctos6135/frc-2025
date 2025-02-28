package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;
import org.littletonrobotics.junction.Logger;

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

  @Override
  public void initialize() {
    elevator.setPosition(setpoint);
    endTime = Timer.getFPGATimestamp() + 0.5;
  }

  @Override
  public void execute() {
    elevator.setVoltage(elevator.calculatePID());
    Logger.recordOutput("Elevator/endTime", endTime);
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }
}
