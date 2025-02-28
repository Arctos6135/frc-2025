package frc.robot.subsystems.outtake;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.constants.CANConstants;
import frc.robot.constants.OuttakeConstants;

public class OuttakeIOReal extends OuttakeIO {
  private final SparkMax rightMotor;
  private final SparkMax leftMotor;

  private final RelativeEncoder rightEncoder;
  private final RelativeEncoder leftEncoder;

  public OuttakeIOReal() {
    rightMotor = new SparkMax(CANConstants.OUTTAKE_RIGHT, MotorType.kBrushless);
    leftMotor = new SparkMax(CANConstants.OUTTAKE_LEFT, MotorType.kBrushless);

    SparkMaxConfig leftConfig = new SparkMaxConfig();
    leftConfig.follow(rightMotor);
    leftConfig.smartCurrentLimit(OuttakeConstants.CURRENT_LIMIT);
    leftConfig.idleMode(IdleMode.kBrake);
    leftConfig.inverted(false);

    leftConfig.encoder.positionConversionFactor(OuttakeConstants.POSITION_CONVERSION_FACTOR);
    leftConfig.encoder.velocityConversionFactor(OuttakeConstants.VELOCITY_CONVERSION_FACTOR);

    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig.smartCurrentLimit(OuttakeConstants.CURRENT_LIMIT);
    rightConfig.idleMode(IdleMode.kBrake);
    rightConfig.inverted(true);

    rightConfig.encoder.positionConversionFactor(OuttakeConstants.POSITION_CONVERSION_FACTOR);
    rightConfig.encoder.velocityConversionFactor(OuttakeConstants.VELOCITY_CONVERSION_FACTOR);

    leftMotor.configure(leftConfig, null, null);
    rightMotor.configure(rightConfig, null, null);

    this.rightEncoder = rightMotor.getEncoder();
    this.leftEncoder = leftMotor.getEncoder();
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
