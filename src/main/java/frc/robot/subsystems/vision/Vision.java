package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.constants.VisionConstants;
import org.littletonrobotics.junction.AutoLog;

public class Vision {
  String limelightName;
  VisionInputs inputs;

  @AutoLog
  public static class VisionInputs {
    Pose2d botPose2d;
  }

  public void updateInputs() {
    inputs.botPose2d = LimelightHelpers.getBotPose2d(limelightName);
  }

  public static Pose2d getPose2d(String limelightName) {
    return LimelightHelpers.getBotPose2d(limelightName);
  }

  public Vision(String limelightName, boolean isReal) {
    this.limelightName = limelightName;
    inputs = new VisionInputs();
    LimelightHelpers.setCameraPose_RobotSpace(
        limelightName,
        VisionConstants.FORWARD_OFFSET,
        VisionConstants.SIDE_OFFSET,
        VisionConstants.UP_OFFSET,
        VisionConstants.ROLL_OFFSET,
        VisionConstants.PITCH_OFFSET,
        VisionConstants.YAW_OFFSET);
  }
}
