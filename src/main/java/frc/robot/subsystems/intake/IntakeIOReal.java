package frc.robot.subsystems.intake;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.constants.CANConstants;
import frc.robot.constants.IntakeConstants;
import org.littletonrobotics.junction.Logger;

public class IntakeIOReal extends IntakeIO {
  // private final SparkMax motor = new SparkMax(CANConstants.INTAKE_MOTOR, MotorType.kBrushless);
  private final SparkMax rightMotor = new SparkMax(CANConstants.INTAKE_RIGHT, MotorType.kBrushless);
  private final SparkMax leftMotor = new SparkMax(CANConstants.INTAKE_LEFT, MotorType.kBrushless);

  private final RelativeEncoder rightEncoder;
  private final RelativeEncoder leftEncoder;

  public IntakeIOReal() {
    SparkMaxConfig leftConfig = new SparkMaxConfig();
    leftConfig
        .follow(rightMotor)
        .smartCurrentLimit(IntakeConstants.CURRENT_LIMIT)
        .inverted(false)
        .idleMode(IdleMode.kBrake);

    leftConfig
        .encoder
        .positionConversionFactor(IntakeConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(IntakeConstants.VELOCITY_CONVERSION_FACTOR);

    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig
        .smartCurrentLimit(IntakeConstants.CURRENT_LIMIT)
        .inverted(true)
        .idleMode(IdleMode.kBrake);

    rightConfig
        .encoder
        .positionConversionFactor(IntakeConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(IntakeConstants.VELOCITY_CONVERSION_FACTOR);

    leftMotor.configure(leftConfig, null, null);
    this.leftEncoder = leftMotor.getEncoder();

    rightMotor.configure(rightConfig, null, null);
    this.rightEncoder = leftMotor.getEncoder();
  }

  @Override
  public void setVoltage(double voltage) {
    Logger.recordOutput("Intake/Voltage", voltage);
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
  }

  @Override
  public void updateInputs(IntakeInputs inputs) {
    inputs.leftCurrent = leftMotor.getOutputCurrent();
    inputs.leftTemperature = leftMotor.getMotorTemperature();
    inputs.leftVoltage = leftMotor.getBusVoltage() * leftMotor.getAppliedOutput();
    inputs.leftSpeed = leftEncoder.getVelocity();

    inputs.rightCurrent = rightMotor.getOutputCurrent();
    inputs.rightTemperature = rightMotor.getMotorTemperature();
    inputs.rightVoltage = rightMotor.getBusVoltage() * leftMotor.getAppliedOutput();
    inputs.rightSpeed = rightEncoder.getVelocity();
  }
}
