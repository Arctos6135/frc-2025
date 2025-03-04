package frc.robot.commands.outtake;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.Outtake;

public class OuttakeSpin extends Command {
  private final Outtake outtake;
  private final DoubleSupplier getSpeed;

  public OuttakeSpin(Outtake outtake, DoubleSupplier getSpeed) {
    this.outtake = outtake;
    this.getSpeed = getSpeed;

    addRequirements(outtake);
  }

  @Override
  public void execute() {
    outtake.setRPS(getSpeed.getAsDouble());
  }

  /** Must be interrupted to turn off */
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    outtake.setRPS(0);
    outtake.setVoltage(0);
  }
}
