package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.intake.Intake;


public class IntakeMove extends Command{

    private final Intake intake;
    private final XboxController operatorController;

    public IntakeMove(Intake intake, XboxController operatorController){
        this.intake = intake;
        this.operatorController = operatorController;

        addRequirements(intake);
    }

    @Override
    public void execute(){
        if(operatorController.getAButton()){
            intake.setRPS(IntakeConstants.INTAKE_RPS);
        }
        else{
            intake.setRPS(0);
        }
    }

    @Override
    public void end(boolean i){
        intake.setVoltage(0);
        intake.setRPS(0);
    }

}
