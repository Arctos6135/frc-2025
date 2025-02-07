package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.elevator.Elevator;

public class ElevatorDefault extends Command {
  public double setpoint;
  public Elevator elevator;

  public ElevatorDefault(Elevator elevator) {
    this.elevator = elevator;

    addRequirements(elevator);
  }

  // Sets the elevator setpoint to their corresponding scoring height.
  public void L1() {
    elevator.setPosition(ElevatorConstants.L1_HEIGHT);
  }

  public void L2() {
    elevator.setPosition(ElevatorConstants.L2_HEIGHT);
  }

  public void L3() {
    elevator.setPosition(ElevatorConstants.L3_HEIGHT);
  }

  public void L4() {
    elevator.setPosition(ElevatorConstants.L4_HEIGHT);
  }
}
