package frc.robot.commands.outtake;

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
    whileTrue(()) { // Controller button specefic to outtake being held is boolean true
      outtake.setRPS(12.0);
    }
    
    io.setVoltage(0);    
  }

}
