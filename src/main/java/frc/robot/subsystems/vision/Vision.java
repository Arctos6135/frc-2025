package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;
import org.littletonrobotics.junction.Logger;

public class Vision {
  private VisionIO io;
  private final VisionInputsAutoLogged inputs = new VisionInputsAutoLogged();

  public Vision(VisionIO io) {
    this.io = io;
  }

  public void update() {
    io.updateInputs(inputs);

    Logger.processInputs("vision", inputs);
  }

  public Pose2d getVisionPose() {
    return inputs.botPose;
  }

  public double getTimestamp() {
    return inputs.timestamp;
  }
}
