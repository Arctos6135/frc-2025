package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.SwerveConstants;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class ResetGyro extends Command {
  double endTime = Double.MAX_VALUE;

  Drivetrain drivetrain;

  public ResetGyro(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    endTime = Timer.getFPGATimestamp() + SwerveConstants.RESET_GYRO_DELAY;
    drivetrain.swerveDrive.zeroGyro();
  }

  @Override
  public InterruptionBehavior getInterruptionBehavior() {
    return InterruptionBehavior.kCancelIncoming;
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }

  @Override
  public void end(boolean interrupted) {
    endTime = Double.MAX_VALUE;
  }
}
