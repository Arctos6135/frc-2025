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

    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig.smartCurrentLimit(OuttakeConstants.CURRENT_LIMIT);
    rightConfig.idleMode(IdleMode.kBrake);
    rightConfig.inverted(true);

    rightConfig.encoder.positionConversionFactor(OuttakeConstants.POSITION_CONVERSION_FACTOR);
    rightConfig.encoder.velocityConversionFactor(OuttakeConstants.VELOCITY_CONVERSION_FACTOR);

    rightMotor.configure(rightConfig, null, null);

    SparkMaxConfig leftConfig = new SparkMaxConfig();
    leftConfig.follow(rightMotor, true);
    leftConfig.smartCurrentLimit(OuttakeConstants.CURRENT_LIMIT);
    leftConfig.idleMode(IdleMode.kBrake);

    leftConfig.encoder.positionConversionFactor(OuttakeConstants.POSITION_CONVERSION_FACTOR);
    leftConfig.encoder.velocityConversionFactor(OuttakeConstants.VELOCITY_CONVERSION_FACTOR);

    leftMotor.configure(leftConfig, null, null);

    this.rightEncoder = rightMotor.getEncoder();
    this.leftEncoder = leftMotor.getEncoder();
  }

  @Override
  public void setVoltage(double voltage) {
    rightMotor.setVoltage(voltage);
  }

  @Override
  public void updateInputs(OuttakeInputs inputs) {
    inputs.velocity = rightEncoder.getVelocity();
    inputs.position = rightEncoder.getPosition();
    inputs.current = rightMotor.getOutputCurrent();
    inputs.temperature = rightMotor.getMotorTemperature();
    inputs.voltage = rightMotor.getBusVoltage() * rightMotor.getAppliedOutput();
  }
}
