package frc.robot.subsystems.elevator;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.constants.ElevatorConstants;

public class ElevatorIOSim extends ElevatorIO {
    private final DCMotorSim leftMotor;
    private final DCMotorSim rightMotor;

    public ElevatorIOSim() {
        leftMotor = new DCMotorSim(ElevatorConstants.ELEVATOR_LINEAR_SYSTEM, DCMotor.getNEO(1), 0.1111);
        rightMotor = new DCMotorSim(ElevatorConstants.ELEVATOR_LINEAR_SYSTEM, DCMotor.getNEO(1), 0.1111); // Should be no difference between the two.
    
    }

    @Override
    public void setVoltage(double voltage) {
        leftMotor.setInputVoltage(voltage);
        rightMotor.setInputVoltage(voltage);
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        // Meters.
        inputs.leftPosition = leftMotor.getAngularPositionRotations() * ElevatorConstants.POSITION_CONVERSION_FACTOR;
        inputs.rightPosition = rightMotor.getAngularPositionRotations() * ElevatorConstants.POSITION_CONVERSION_FACTOR;

        // Meters per second.
        inputs.leftVelocity = leftMotor.getAngularVelocityRPM() * ElevatorConstants.VELOCITY_CONVERSION_FACTOR;
        inputs.rightVelocity = rightMotor.getAngularVelocityRPM() * ElevatorConstants.VELOCITY_CONVERSION_FACTOR;

        inputs.leftCurrent = leftMotor.getCurrentDrawAmps();
        inputs.rightCurrent = rightMotor.getCurrentDrawAmps();

        inputs.leftVoltage = leftMotor.getInputVoltage();
        inputs.rightVoltage = rightMotor.getInputVoltage();
    }
}
