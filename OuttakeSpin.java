// This code is in progress

package frc.robot.subsystems.outtake;

import edu.wpi.first.wpilibj.XboxController;

public class OuttakeSpin {

  private Outtake outtake;
  private XboxController controllerButton;

  whileTrue() { // button is being held down
    // outtake spins at 12 Volts
  }

  public OuttakeSpin(Outtake outtakeType, XboxController controllerType) {
    outtake = outtakeType;
    controllerButton = controllerType;
  }
  
  @override
  public void setVoltage(double amount) {
    voltage = amount;
  }

}
