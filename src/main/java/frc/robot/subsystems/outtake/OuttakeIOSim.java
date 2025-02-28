package frc.robot.subsystems.outtake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.constants.OuttakeConstants;

public class OuttakeIOSim extends OuttakeIO {
  private final FlywheelSim leftMotor;
  private final FlywheelSim rightMotor;

  public OuttakeIOSim() {
    leftMotor = new FlywheelSim(OuttakeConstants.OUTTAKE_LINEAR_SYSTEM, DCMotor.getNEO(1), 0);
    rightMotor = new FlywheelSim(OuttakeConstants.OUTTAKE_LINEAR_SYSTEM, DCMotor.getNEO(1), 0);
  }

  @Override
  public void setVoltage(double voltage) {
    rightMotor.setInputVoltage(voltage);
    leftMotor.setInputVoltage(voltage);
  }

  @Override
  public void updateInputs(OuttakeInputs inputs) {
    leftMotor.update(0.02); // Assumes uniform timestep.
    rightMotor.update(0.02);

    inputs.leftVelocity =
        leftMotor.getAngularVelocityRPM() * OuttakeConstants.VELOCITY_CONVERSION_FACTOR;
    inputs.rightVelocity =
        rightMotor.getAngularVelocityRPM() * OuttakeConstants.VELOCITY_CONVERSION_FACTOR;

    inputs.leftCurrent = leftMotor.getCurrentDrawAmps();
    inputs.rightCurrent = rightMotor.getCurrentDrawAmps();

    inputs.leftVoltage = leftMotor.getInputVoltage();
    inputs.rightVoltage = rightMotor.getInputVoltage();
  }
}
