package frc.robot.subsystems;

import frc.robot.Constants.InfeedConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;



public class InfeedSS extends SubsystemBase {


    private TalonFX m_infeedMotor;
    private Double speed;

  
    public InfeedSS(){
            m_infeedMotor = new TalonFX(InfeedConstants.INFEED_MOTOR_ID);
            m_infeedMotor.configFactoryDefault();
            m_infeedMotor.setNeutralMode(NeutralMode.Brake);

        

    }


    public enum Mode{
        Comp,
        ConeIn,
        ConeOut,
        CubeIn,
        CubeOut,
        SetSpeed,
    }

    Mode InfeedMode = Mode.Comp;
    
    @Override

    public void periodic() {

        switch(InfeedMode) {

            case Comp:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.COMP);
                break;
            }

            case ConeIn:{

                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_IN);
                break;
            }


            case ConeOut:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_OUT);
                break;
            }


            case CubeIn:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_IN);
                break;
            }


            case CubeOut:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_OUT);
                break;
            }

            case SetSpeed:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, speed);
            }
        }

        SmartDashboard.getNumber("InfeedSpeed",m_infeedMotor.getMotorOutputPercent());
    }

    public void Comp(){
        InfeedMode = Mode.Comp;
    }
    
    public void ConeIn(){
        InfeedMode = Mode.ConeIn;
    }
    
    public void ConeOut(){
        InfeedMode = Mode.ConeOut;
    }
    
    public void CubeIn(){
        InfeedMode = Mode.CubeIn;
    }
    
    public void CubeOut(){
        InfeedMode = Mode.CubeOut;
    }

    public void setSpeed(double speed){
    this.speed = speed;
    InfeedMode = Mode.SetSpeed;
    }

    
    
}