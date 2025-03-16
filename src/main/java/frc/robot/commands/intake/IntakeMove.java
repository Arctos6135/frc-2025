package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ElevatorConstants;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.intake.Intake;
import java.util.function.DoubleSupplier;

public class IntakeMove extends Command {

  private final Intake intake;
  private final Elevator elevator;
  private final DoubleSupplier getSpeed;

  public IntakeMove(Intake intake, DoubleSupplier getSpeed, Elevator elevator) {
    this.intake = intake;
    this.getSpeed = getSpeed;
    this.elevator = elevator;

    addRequirements(intake);
  }

  public void execute() {
    if (Math.abs(elevator.getPosition() - ElevatorConstants.HANDOFF_HEIGHT) < 0.005) {
      intake.setRPS(getSpeed.getAsDouble());
    } else {
      intake.setRPS(0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean i) {
    intake.setRPS(0);
    intake.setVoltage(0);
  }
}
