package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;
import org.littletonrobotics.junction.AutoLog;

public class VisionIO {
  @AutoLog
  public static class VisionInputs {
    Pose2d botPose;
    double timestamp;
  }

  public void updateInputs(VisionInputs inputs) {}
}
