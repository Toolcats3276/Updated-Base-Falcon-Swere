package frc.robot.subsystems;
//hi

import frc.robot.Constants.InfeedSpeed;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Timer;



public class InfeedSS extends SubsystemBase {


    private final TalonFX m_infeedMotor = new TalonFX(16);
  
    public void Infeed()
    {

    }


    public enum State{
        Comp,
        ConeIn,
        ConeOut,
        CubeIn,
        CubeOut,
        Kill;
    }

    State InfeedState = State.Comp;
    
    @Override

    public void periodic() {
        switch(InfeedState) {
            case Comp:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.Comp);
                break;

            case ConeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.ConeIn);
                System.out.println("Cone is Infeeding");
                break;


            case ConeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.ConeOut);
                break;


            case CubeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.CubeIn);
                break;


            case CubeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.CubeOut);
                break;


            case Kill:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput,InfeedSpeed.Kill);
                break;
        }
    }

public void Comp(){
    InfeedState = State.Comp;
}

public void ConeIn(){
    InfeedState = State.ConeIn;
    System.out.println("State is ConeIn");
}

public void ConeOut(){
    InfeedState = State.ConeOut;

}

public void CubeIn(){
    InfeedState = State.CubeIn;

}

public void CubeOut(){
    InfeedState = State.CubeOut;

}

public void Kill(){
    InfeedState = State.Kill;
}

}
