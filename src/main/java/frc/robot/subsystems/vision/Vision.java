package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import frc.robot.constants.VisionConstants;

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
        Pose3d targetPose;
    }

    public void updateInputs() {
        t2darray = LimelightHelpers.getT2DArray(limelightName);
        inputs.tv = t2darray[0];
        inputs.ty = t2darray[4];
        inputs.tx = t2darray[5];
        inputs.skew = t2darray[16];
        inputs.pose = LimelightHelpers.getBotPose2d(limelightName);
        inputs.targetPose = LimelightHelpers.getTargetPose3d_CameraSpace(limelightName);
    }

    public Vision(String limelightName) {
        this.limelightName = limelightName;
        inputs = new VisionInputs();

        LimelightHelpers.setCameraPose_RobotSpace(limelightName, VisionConstants.FORWARD_OFFSET, VisionConstants.SIDE_OFFSET, VisionConstants.UP_OFFSET, VisionConstants.ROLL_OFFSET, VisionConstants.PITCH_OFFSET, VisionConstants.YAW_OFFSET);
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

    public Pose2d visionPose() {
        return inputs.pose;
    }

    public boolean validTarget() {
        return inputs.tv == 1.0 ? true : false;
    }

    public Pose3d getAprilTagPose() {
        return inputs.targetPose;
    }
}