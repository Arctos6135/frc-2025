package frc.robot.subsystems.outtake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.constants.OuttakeConstants;

public class OuttakeIOSim extends OuttakeIO {
  private final FlywheelSim motor;

  public OuttakeIOSim() {
    motor = new FlywheelSim(OuttakeConstants.OUTTAKE_LINEAR_SYSTEM, DCMotor.getNEO(1), 0);
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setInputVoltage(voltage);
  }

  @Override
  public void updateInputs(OuttakeInputs inputs) {
    motor.update(0.02); // Assumes uniform timestep.
    inputs.velocity = motor.getAngularVelocityRPM() * OuttakeConstants.VELOCITY_CONVERSION_FACTOR;

    inputs.current = motor.getCurrentDrawAmps();
    inputs.voltage = motor.getInputVoltage();
  }
}
