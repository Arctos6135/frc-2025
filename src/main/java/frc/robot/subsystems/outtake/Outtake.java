package frc.robot.subsystems.drivetrain.outtake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.drivetrain.outtake.OuttakeIO.OuttakeInputs;

public class Outtake extends SubsystemBase {
    private final OuttakeIO io;
    private final OuttakeInputsAutoLogged inputs = new OuttakeInputsAutoLogged();

    public Outtake(OuttakeIO io) {
        this.io = io;
    }

    @Override
    public void periodic(){
        io.updateInputs(inputs);
        Logger.processInputs("outtake", inputs);
    }

    public void setVoltage(double voltage) {
        io.setVoltage(voltage);
    }
}
