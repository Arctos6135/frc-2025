package frc.robot.subsystems.intake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.constants.IntakeConstants;

public class IntakeIOSim extends IntakeIO {
    private final DCMotorSim leftMotor;
    private final DCMotorSim rightMotor;

    public IntakeIOSim() {
        leftMotor = new DCMotorSim(IntakeConstants.INTAKE_LINEAR_SYSTEM, DCMotor.getNEO(1), null); // TODO Check what kind of neo is being used and std deviations.
        rightMotor = new DCMotorSim(IntakeConstants.INTAKE_LINEAR_SYSTEM, DCMotor.getNEO(1), null);
    }

    @Override
    public void setVoltage(double voltage) {
        leftMotor.setInputVoltage(voltage);
        rightMotor.setInputVoltage(voltage);
    }

    @Override
    public void updateInputs(IntakeInputs inputs) {
        leftMotor.update(0.02); // Assumes uniform timestep.
        rightMotor.update(0.02);

        inputs.leftSpeed = leftMotor.getAngularVelocityRPM() * IntakeConstants.VELOCITY_CONVERSION_FACTOR;
        inputs.rightSpeed = rightMotor.getAngularVelocityRPM() * IntakeConstants.VELOCITY_CONVERSION_FACTOR;

        inputs.leftCurrent = leftMotor.getCurrentDrawAmps();
        inputs.rightCurrent = rightMotor.getCurrentDrawAmps();

        inputs.leftVoltage = leftMotor.getInputVoltage();
        inputs.rightVoltage = rightMotor.getInputVoltage();
    }
}
