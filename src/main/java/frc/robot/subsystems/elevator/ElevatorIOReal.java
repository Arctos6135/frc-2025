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
    leftConfig.smartCurrentLimit(ElevatorConstants.CURRENT_LIMIT).idleMode(IdleMode.kBrake);
    // .inverted(true)

    // setting up soft limits, soft stops are not set up on elevator rightMotor
    // because it
    // follows leftMotor
    leftConfig
        .inverted(false)
        .softLimit
        .forwardSoftLimitEnabled(true)
        .reverseSoftLimitEnabled(true)
        .forwardSoftLimit(ElevatorConstants.ELEVATOR_MAX)
        .reverseSoftLimit(ElevatorConstants.ELEVATOR_MIN);

    leftConfig
        .encoder
        .positionConversionFactor(ElevatorConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(ElevatorConstants.VELOCITY_CONVERSION_FACTOR);

    // Right Motor Configuration
    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig
        .follow(leftMotor, true) // follows the other motor but inverted
        .smartCurrentLimit(ElevatorConstants.CURRENT_LIMIT)
        .idleMode(IdleMode.kBrake);
    // .softLimit
    // .forwardSoftLimitEnabled(false)
    // .reverseSoftLimitEnabled(false);

    rightConfig
        .encoder
        .positionConversionFactor(ElevatorConstants.POSITION_CONVERSION_FACTOR)
        .velocityConversionFactor(ElevatorConstants.VELOCITY_CONVERSION_FACTOR);

    rightMotor.configure(rightConfig, null, null);
    leftMotor.configure(leftConfig, null, null);

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();
  }

  @Override
  public void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
  }

  @Override
  public void zeroEncoderPosition() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  @Override
  public void updateInputs(ElevatorInputs inputs) {
    inputs.position = leftEncoder.getPosition(); // Meters
    inputs.velocity = leftEncoder.getVelocity(); // Meters per second
    inputs.current = leftMotor.getOutputCurrent(); // Amps
    inputs.temperature = leftMotor.getMotorTemperature(); // Degrees celcius
    inputs.voltage = leftMotor.getBusVoltage() * leftMotor.getAppliedOutput(); // Volts
  }
}
