package frc.robot.subsystems;
//hi

import frc.robot.Constants.InfeedConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;



public class InfeedSS extends SubsystemBase {


    private final TalonFX m_infeedMotor = new TalonFX(InfeedConstants.INFEED_MOTOR_ID);
  
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

    State InfeedState = State.Kill;
    
    @Override

    public void periodic() {
        switch(InfeedState) {
            case Comp:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.COMP);
                break;

            case ConeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_IN);
                System.out.println("Cone is Infeeding");
                break;


            case ConeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_OUT);
                break;


            case CubeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_IN);
                break;


            case CubeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_OUT);
                break;


            case Kill:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput,InfeedConstants.KILL);
                break;
        }

        SmartDashboard.getNumber("InfeedSpeed",m_infeedMotor.getMotorOutputPercent());
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