package frc.robot.subsystems.intake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.constants.IntakeConstants;

public class IntakeIOSim extends IntakeIO {
  private final FlywheelSim motor;

  public IntakeIOSim() {
    motor =
        new FlywheelSim(
            IntakeConstants.INTAKE_LINEAR_SYSTEM,
            DCMotor.getNEO(1),
            new double[] {0.000}); // TODO Check what kind of neo is being used and std deviations.
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setInputVoltage(voltage);
  }

  @Override
  public void updateInputs(IntakeInputs inputs) {
    motor.update(0.02); // Assumes uniform timestep.

    inputs.speed =
        motor.getAngularVelocityRPM() * IntakeConstants.VELOCITY_CONVERSION_FACTOR; // Converts from RPM to RPS
    inputs.current = motor.getCurrentDrawAmps();
    inputs.voltage = motor.getInputVoltage();
  }
}
