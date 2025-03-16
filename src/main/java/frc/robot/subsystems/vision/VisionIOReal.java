package frc.robot.subsystems.vision;

import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.vision.LimelightHelpers.LimelightResults;

public class VisionIOReal extends VisionIO {
  private String limelightName = VisionConstants.LIMELIGHT_NAME;
  private LimelightResults limelightResults;

  public VisionIOReal() {
    LimelightHelpers.setCameraPose_RobotSpace(
        "",
        VisionConstants.FORWARD_OFFSET,
        VisionConstants.SIDE_OFFSET,
        VisionConstants.UP_OFFSET,
        VisionConstants.ROLL_OFFSET,
        VisionConstants.PITCH_OFFSET,
        VisionConstants.YAW_OFFSET);
  }

  @Override
  public void updateInputs(VisionInputs inputs) {
    inputs.botPose = LimelightHelpers.getBotPose2d_wpiBlue("");
    inputs.timestamp = LimelightHelpers.getLatestResults("").timestamp_RIOFPGA_capture;
  }
}
