package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;

public class VisionIO {
  public static class VisionInputs {
    Pose2d botPose;
    double timestamp;
  }

  public void updateInputs(VisionInputs inputs) {}
}
