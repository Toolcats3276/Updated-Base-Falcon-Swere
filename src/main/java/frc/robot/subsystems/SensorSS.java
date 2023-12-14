package frc.robot.subsystems;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SensorSS extends SubsystemBase{
    
    private final DigitalInput sensor;
    private final Debouncer m_debouncer;
    private final Debouncer m_debouncer2;

   public SensorSS() {
    sensor = new DigitalInput(0);
    m_debouncer = new Debouncer(.35, DebounceType.kBoth);
    m_debouncer2 = new Debouncer(.65, DebounceType.kBoth);
   }


    public void periodic(){
        SmartDashboard.putBoolean("Sensor", sensor.get());

    }
    
    public boolean isTriggered(){
        return m_debouncer.calculate(sensor.get());
    }

    public boolean isDebounced(){
        return m_debouncer2.calculate(sensor.get());
    }





    
}
