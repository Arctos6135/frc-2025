package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.intake.Intake;

public class IntakeRamp extends Command {
    private final Intake intake;
    private double voltage;
    private double step = 0.1;

    public IntakeRamp(Intake intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        voltage = 0.0;
        voltage = voltage + step;
    }

    @Override
    public void execute() {
        voltage += step;
        intake.setVoltage(voltage);
    }

    @Override
    public boolean isFinished() {
        return voltage >= 12.0;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setVoltage(0);
    }
}