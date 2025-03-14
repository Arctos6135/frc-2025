package frc.robot.subsystems.vision;

import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.vision.LimelightHelpers.LimelightResults;

public class VisionIOReal extends VisionIO {
  private String limelightName = VisionConstants.LIMELIGHT_NAME;
  private LimelightResults limelightResults;

  public VisionIOReal() {}

  @Override
  public void updateInputs(VisionInputs inputs) {
    limelightResults = LimelightHelpers.getLatestResults(limelightName);
    inputs.botPose = limelightResults.getBotPose2d_wpiBlue();
    inputs.timestamp = limelightResults.timestamp_RIOFPGA_capture;
  }
}
