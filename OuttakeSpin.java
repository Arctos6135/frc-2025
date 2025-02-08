package frc.robot.subsystems.outtake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.button.Trigger;
import frc.robot.subsystems.intake.Intake;

public class OuttakeSpin {

  private Outtake outtake;
  private XboxController controllerButton;

  public OuttakeSpin(Outtake outtakeType, XboxController controllerType) {
    this.outtake = outtakeType;
    this.controllerButton = controllerType;
  }

  public void spin() {
    if (controllerButton.getAButton()) {
       whileTrue(controllerButton.getAButton()) {
         outtake.setRPS(12.0);
       }
    }
      
    else {
      io.setVoltage(0);
    }
  }

}
