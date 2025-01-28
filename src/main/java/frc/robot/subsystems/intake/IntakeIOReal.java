package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.constants.CANConstants;
import frc.robot.constants.IntakeConstants;

public class IntakeIOReal extends IntakeIO {
  private final SparkMax motor = new SparkMax(CANConstants.INTAKE_MOTOR, MotorType.kBrushless);

  private final RelativeEncoder encoder;

  public IntakeIOReal() {
    SparkMaxConfig motorConfig = new SparkMaxConfig();
    motorConfig
      .smartCurrentLimit(IntakeConstants.CURRENT_LIMIT)
      .inverted(false)
      .idleMode(IdleMode.kBrake);
    
    motorConfig.encoder
      .positionConversionFactor(IntakeConstants.POSITION_CONVERSION_FACTOR)
      .velocityConversionFactor(IntakeConstants.VELOCITY_CONVERSION_FACTOR);

    motor.configure(motorConfig, null, null);
    encoder = motor.getEncoder();
  }

  @Override
  public void setVoltage(double voltage) {
    Logger.recordOutput("Intake/Voltage", voltage);
    motor.setVoltage(voltage);
  }

  @Override
  public void updateInputs(IntakeInputs inputs) {
    inputs.current = motor.getOutputCurrent();
    inputs.temperature = motor.getMotorTemperature();
    inputs.voltage = motor.getBusVoltage() * motor.getAppliedOutput();
    inputs.speed = encoder.getVelocity();
  }
}
