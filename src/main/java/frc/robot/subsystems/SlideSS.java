package frc.robot.subsystems;


import frc.robot.Constants.PneumaticConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SlideSS extends SubsystemBase{
    
    DoubleSolenoid slideDoublePH = new DoubleSolenoid(PneumaticConstants.SOLENOID_ID, PneumaticsModuleType.REVPH, 6, 7);

    public enum Mode{
      In,
      Out,
      Toggle,
      Neutral;
    }

    Mode SlideMode = Mode.In;

    @Override
    public void periodic(){
        switch (SlideMode) {
            case In:{
            slideDoublePH.set(Value.kReverse);}
            break;

            case Out:{
            slideDoublePH.set(Value.kForward);}
            break;

            case Toggle:{
            Value value = slideDoublePH.get();

            if (value == Value.kForward) {
              slideDoublePH.set(Value.kReverse);
            } else if (value == Value.kReverse) {
              slideDoublePH.set(Value.kForward);
            }  
            SlideMode = Mode.Neutral;
            break;
            }
            
            case Neutral:{}


        }

    }


    public void In(){
      SlideMode = Mode.In;
    }

    public void Out(){
      SlideMode = Mode.Out;
    }
    
    public void Toggle(){
      SlideMode = Mode.Toggle;
    }


    
}
