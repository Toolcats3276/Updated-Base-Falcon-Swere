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

    public enum State{
      In,
      Out,
      Toggle,
      Neutral;
    }

    State ArmState = State.In;

    @Override
    public void periodic(){
        switch (ArmState) {
            case In:{
            armDoublePH.set(Value.kReverse);}
            break;

            case Out:{
            armDoublePH.set(Value.kForward);}
            break;

            case Toggle:{
            Value value = armDoublePH.get();

            if (value == Value.kForward) {
              armDoublePH.set(Value.kReverse);
            } else if (value == Value.kReverse) {
              armDoublePH.set(Value.kForward);
            }  
            ArmState = State.Neutral;
            break;
            }
            
            case Neutral:{}


        }

    }


    public void In(){
        ArmState = State.In;
    }

    public void Out(){
        ArmState = State.Out;
    }
    
    public void Toggle(){
        ArmState = State.Toggle;
    }


    
}
