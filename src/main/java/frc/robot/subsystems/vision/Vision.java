package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.constants.VisionConstants;
import org.littletonrobotics.junction.AutoLog;

public class Vision {
  String limelightName;
  VisionInputs inputs;
  double[] t2darray;

  @AutoLog
  public static class VisionInputs {
    double tx;
    double ty;
    double tv;
    double skew;
    Pose2d pose;
    double timestamp;
  }

  public void updateInputs() {
    // t2darray = LimelightHelpers.getT2DArray(limelightName);
    // inputs.tv = t2darray[0];
    // inputs.ty = t2darray[4];
    // inputs.tx = t2darray[5];
    // inputs.skew = t2darray[16]; TODO: this gives an error still
    // inputs.pose = LimelightHelpers.getBotPose2d(limelightName);
    // inputs.targetPose = LimelightHelpers.getTargetPose3d_RobotSpace(limelightName);
  }

  public Vision(String limelightName) {
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

  public double getTX() {
    return inputs.tx;
  }

  public double getTY() {
    return inputs.ty;
  }

  public double getSkew() {
    return inputs.skew;
  }
}
