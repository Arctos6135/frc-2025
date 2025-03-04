package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;
import java.util.function.DoubleSupplier;

public class IntakeMove extends Command {

  private final Intake intake;
  private final DoubleSupplier getSpeed;

  public IntakeMove(Intake intake, DoubleSupplier getSpeed) {
    this.intake = intake;
    this.getSpeed = getSpeed;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    if (reversed) {
      intake.setRPS(-IntakeConstants.INTAKE_RPS + 10);
    } else {
      intake.setRPS(IntakeConstants.INTAKE_RPS);
    }
  public void execute() {
    intake.setRPS(getSpeed.getAsDouble());
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
