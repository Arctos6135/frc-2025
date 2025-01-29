package frc.robot.subsystems.outtake;

import frc.robot.constants.OuttakeConstants;
import frc.robot.subsystems.outtake.OuttakeIO;
import frc.robot.subsystems.outtake.OuttakeIO.OuttakeInputs;
import frc.robot.constants.CANConstants;
import frc.robot.constants.IntakeConstants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class OuttakeIOSparkMax extends OuttakeIO {
    private final SparkMax rightMotor = new SparkMax(CANConstants.OUTTAKE_RIGHT, MotorType.kBrushless); 
    private final SparkMax leftMotor = new SparkMax(CANConstants.OUTTAKE_LEFT, MotorType.kBrushless);

    private final RelativeEncoder rightEncoder;
    private final RelativeEncoder leftEncoder;

    public OuttakeIOSparkMax() {
        rightMotor.setSmartCurrentLimit(OuttakeConstants.CURRENT_LIMIT);
        leftMotor.follow(rightMotor, true);

        this.rightEncoder = rightMotor.getEncoder();
        this.leftEncoder = leftMotor.getEncoder();
        
        rightEncoder.setPositionConversionFactor(OuttakeConstants.POSITION_CONVERSION_FACTOR);
        leftEncoder.setVelocityConversionFactor(OuttakeConstants.VELOCITY_CONVERSION_FACTOR);
    }

    @Override
    public void setVoltage(double voltage) {
        rightMotor.setVoltage(voltage);
        leftMotor.setVoltage(voltage);
    }

    @Override
    public void updateInputs(OuttakeInputs inputs) {
        inputs.rightVelocity = rightEncoder.getVelocity();
        inputs.rightPosition = rightEncoder.getPosition();
        inputs.rightCurrent = rightMotor.getOutputCurrent();
        inputs.rightTemperature = rightMotor.getMotorTemperature();
        inputs.rightVoltage = rightMotor.getBusVoltage() * rightMotor.getAppliedOutput();

        inputs.leftVelocity = leftEncoder.getVelocity();
        inputs.leftPosition = leftEncoder.getPosition();
        inputs.leftCurrent = leftMotor.getOutputCurrent();
        inputs.leftTemperature = leftMotor.getMotorTemperature();
        inputs.leftVoltage = leftMotor.getBusVoltage() * leftMotor.getAppliedOutput();
    }
}