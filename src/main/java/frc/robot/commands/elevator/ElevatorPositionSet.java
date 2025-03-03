package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.Elevator;
import org.littletonrobotics.junction.Logger;

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
    elevator.setPosition(setpoint);
  }

  @Override
  public void execute() {
    elevator.setVoltage(elevator.calculatePID());

    Logger.recordOutput("Elevator/endTime", endTime);
    Logger.recordOutput("Elevator/currentTime", Timer.getFPGATimestamp());
  }

  @Override
  public boolean isFinished() {
    return elevator.atSetpoint();
  }
}
