package frc.robot.subsystems.outtake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.constants.OuttakeConstants;

public class OuttakeIOSim extends OuttakeIO {
  private final DCMotorSim leftMotor;
  private final DCMotorSim rightMotor;

  public OuttakeIOSim() {
    leftMotor =
        new DCMotorSim(
            OuttakeConstants.OUTTAKE_LINEAR_SYSTEM,
            DCMotor.getNeo550(1),
            null); // TODO std and type of motor
    rightMotor = new DCMotorSim(OuttakeConstants.OUTTAKE_LINEAR_SYSTEM, DCMotor.getNeo550(1), null);
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

    inputs.leftPosition =
        leftMotor.getAngularPositionRotations() * OuttakeConstants.POSITION_CONVERSION_FACTOR;
    inputs.rightPosition =
        rightMotor.getAngularPositionRotations() * OuttakeConstants.POSITION_CONVERSION_FACTOR;

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
