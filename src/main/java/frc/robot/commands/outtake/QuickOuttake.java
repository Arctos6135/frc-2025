package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.Outtake;

public class QuickOuttake extends Command {
  private final Outtake outtake;

  private double endTime = 0;
  private final double duration;

  /**
   * Run the outtake for a specified duration and then stop.
   *
   * @param outtake Outtake Subsystem
   * @param duration The duration in seconds to run the outtake for.
   */
  public QuickOuttake(Outtake outtake, double duration) {
    this.outtake = outtake;
    this.duration = duration;
  }

  public QuickOuttake(Outtake outtake) {
    this(outtake, OuttakeConstants.QUICK_OUTTAKE_DURATION);
  }

  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + duration;
    outtake.setRPS(OuttakeConstants.OUTTAKE_RPS);
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }

  @Override
  public void end(boolean interrupted) {
    outtake.setRPS(0);
  }
}
