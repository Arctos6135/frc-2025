package frc.robot.subsystems.elevator;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.constants.CANConstants;
import frc.robot.constants.ElevatorConstants;

public class ElevatorIOReal extends ElevatorIO {
  private final SparkMax leftMotor = new SparkMax(CANConstants.ELEVATOR_LEFT, MotorType.kBrushless);
  private final SparkMax rightMotor =
      new SparkMax(CANConstants.ELEVATOR_RIGHT, MotorType.kBrushless);

  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

  public ElevatorIOReal() {
    // Left Motor Configuration
    SparkMaxConfig leftConfig = new SparkMaxConfig();
    leftConfig
        .smartCurrentLimit(ElevatorConstants.CURRENT_LIMIT)
        .idleMode(IdleMode.kBrake)
        .inverted(true);

    leftConfig
        .encoder
        .positionConversionFactor(ElevatorConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(ElevatorConstants.VELOCITY_CONVERSION_FACTOR);

    leftMotor.configure(leftConfig, null, null);

    // Right Motor Configuration
    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig
        .follow(leftMotor, true) // follow the other motor
        .smartCurrentLimit(ElevatorConstants.CURRENT_LIMIT)
        .idleMode(IdleMode.kBrake);

    rightConfig
        .encoder
        .positionConversionFactor(ElevatorConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(ElevatorConstants.VELOCITY_CONVERSION_FACTOR);

    rightMotor.configure(rightConfig, null, null);

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();
  }

  @Override
  public void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
  }

  @Override
  public void updateInputs(ElevatorInputs inputs) {
    inputs.leftPosition = -leftEncoder.getPosition();
    inputs.rightPosition = rightEncoder.getPosition();

    inputs.leftVelocity = -leftEncoder.getVelocity();
    inputs.rightVelocity = rightEncoder.getVelocity();

    inputs.leftCurrent = leftMotor.getOutputCurrent();
    inputs.rightCurrent = rightMotor.getOutputCurrent();

    inputs.leftTemperature = leftMotor.getMotorTemperature();
    inputs.rightTemperature = rightMotor.getMotorTemperature();

    inputs.leftVoltage = leftMotor.getBusVoltage() * leftMotor.getAppliedOutput();
    inputs.rightVoltage = rightMotor.getBusVoltage() * rightMotor.getAppliedOutput();
  }
}
