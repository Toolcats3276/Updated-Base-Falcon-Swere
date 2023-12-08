package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SensorSS extends SubsystemBase{
    
    private final DigitalInput sensor = new DigitalInput(0);

   

    public boolean isTriggered(){
        return sensor.get();
    }





    
}
