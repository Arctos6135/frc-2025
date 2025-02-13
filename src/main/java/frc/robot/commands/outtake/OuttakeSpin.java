package frc.robot.subsystems.outtake;

import edu.wpi.first.wpilibj.XboxController;


public class OuttakeSpin extends Command {

  private Outtake outtake;
  private XboxController controllerButton;

  public OuttakeSpin(Outtake outtakeType, XboxController controllerType) {
    this.outtake = outtakeType;
    this.controllerButton = controllerType;
  }
  
  @override
  public void initialize(double speed) {
    whileTrue(configureBindings()) {
      outtake.setRPS(12.0);
    }
  }

  @override
  public void end() {
    outtake.setRPS(0);
  }


}
