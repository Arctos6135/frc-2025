package frc.robot.subsystems.outtake;

import org.littletonrobotics.junction.AutoLog;

public class OuttakeIO {
    @AutoLog
    public static class OuttakeInputs{ 
        public double rightVelocity;
        public double rightPosition;
        public double rightTemperature;
        public double rightCurrent;
        public double rightVoltage;

        public double leftVelocity;
        public double leftPosition;
        public double leftTemperature;
        public double leftCurrent;
        public double leftVoltage;

    }
    
    public void updateInputs(OuttakeInputs inputs){}
    public void setVoltage(double voltage){}
}
