package frc.robot.subsystems;


import frc.robot.Constants.PneumaticConstants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmSS extends SubsystemBase{
    
    DoubleSolenoid armDoublePH = new DoubleSolenoid(PneumaticConstants.SOLENOID_ID, PneumaticsModuleType.REVPH, PneumaticConstants.OUT_CHANNEL, PneumaticConstants.IN_CHANNEL);

    public enum Mode{
      In,
      Out,
      Toggle;
    }

    Mode ArmMode = Mode.In;

    @Override
    public void periodic(){
        switch (ArmMode) {
            case In:
            armDoublePH.set(Value.kForward);

            case Out:
            armDoublePH.set(Value.kReverse);

            case Toggle:
            Value value = armDoublePH.get();

            if (value == Value.kForward) {
              armDoublePH.set(Value.kReverse);
            } else if (value == Value.kReverse) {
              armDoublePH.set(Value.kForward);
            }

        }

    }


    public void In(){
        ArmMode = Mode.In;
    }

    public void Out(){
        ArmMode = Mode.Out;
    }
    
    public void Toggle(){
        ArmMode = Mode.Toggle;
    }


    
}
