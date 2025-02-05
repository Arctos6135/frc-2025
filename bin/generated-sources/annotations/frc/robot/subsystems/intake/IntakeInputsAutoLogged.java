package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class IntakeInputsAutoLogged extends IntakeIO.IntakeInputs
    implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("Current", current);
    table.put("Temperature", temperature);
    table.put("Voltage", voltage);
    table.put("Speed", speed);
  }

  @Override
  public void fromLog(LogTable table) {
    current = table.get("Current", current);
    temperature = table.get("Temperature", temperature);
    voltage = table.get("Voltage", voltage);
    speed = table.get("Speed", speed);
  }

  public IntakeInputsAutoLogged clone() {
    IntakeInputsAutoLogged copy = new IntakeInputsAutoLogged();
    copy.current = this.current;
    copy.temperature = this.temperature;
    copy.voltage = this.voltage;
    copy.speed = this.speed;
    return copy;
  }
}
