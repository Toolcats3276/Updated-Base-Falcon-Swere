package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SensorSS extends SubsystemBase{
    
    private final DigitalInput sensor = new DigitalInput(0);
    private final Timer sensorTimer = new Timer();

   

    public boolean isTriggered(){
        return sensor.get();
    }

    public void TimerStart(){
        sensorTimer.start();
    }

    public void TimerReset(){
        sensorTimer.reset();
    }

    public boolean hasElapsed(double delay){
            if(sensorTimer.hasElapsed(delay)){
                return true;
            }
            else{
                return false;
            }
    }




    
}
