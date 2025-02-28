package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.outtake.Outtake;

public class OuttakeRamp extends Command {
  private final Outtake outtake;
  private double voltage;
  private double step = 0.1;

  public OuttakeRamp(Outtake outtake) {
    this.outtake = outtake;

    addRequirements(outtake);
  }

  @Override
  public void initialize() {
    voltage = 0.0;
    voltage = voltage + step;
  }

  @Override
  public void execute() {
    voltage += step;
    outtake.setVoltage(voltage);
  }

  @Override
  public boolean isFinished() {
    return voltage >= 12.0;
  }

  @Override
  public void end(boolean interrupted) {
    outtake.setVoltage(0);
  }
}
