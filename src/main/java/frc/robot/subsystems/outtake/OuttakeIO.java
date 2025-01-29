package frc.robot.subsystems.drivetrain.outtake;

import org.littletonrobotics.junction.AutoLog;

public class OuttakeIO {
    @AutoLog
    public static class OuttakeInputs{ 

        public double velocity;
        public double position;
        public double temperature;
        public double current;
        public double voltage;
    }
    
    public void updateInputs(OuttakeInputs inputs){}
    public void setVoltage(double voltage){}
}
