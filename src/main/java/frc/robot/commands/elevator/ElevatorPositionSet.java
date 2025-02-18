package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.elevator.Elevator;
import edu.wpi.first.math.controller.PIDController;

public class ElevatorPositionSet extends Command {
  public double setpoint;
  public double timeAtSetpoint;
  public Elevator elevator;
  public PIDController pidController = new PIDController(ElevatorConstants.PID[0], ElevatorConstants.PID[1], ElevatorConstants.PID[2]);
  
  public ElevatorPositionSet(Elevator elevator, Double setpoint, Double timeAtSetpoint) {
    this.elevator = elevator;
    this.setpoint = setpoint;
    this.timeAtSetpoint = timeAtSetpoint;

    addRequirements(elevator);
  }

  // Sets the elevator setpoint to their corresponding scoring height.
  
  public void initialise(){
    elevator.setPosition(setpoint);
    timeAtSetpoint = 0;
  }

  @Override
  public void execute(){
      elevator.setVoltage(elevator.calculatePID());
      if (pidController.atSetpoint())
        timeAtSetpoint += 1;
      else 
        timeAtSetpoint = 0;
      
  }

  @Override
  public boolean isFinished() {
    return timeAtSetpoint >= 25;
  }
}
