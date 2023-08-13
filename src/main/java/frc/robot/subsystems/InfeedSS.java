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
    private DigitalInput sensor;
    private Timer sensorTimer;
  
    public InfeedSS(){
            m_infeedMotor = new TalonFX(InfeedConstants.INFEED_MOTOR_ID);
            m_infeedMotor.configFactoryDefault();
            m_infeedMotor.setNeutralMode(NeutralMode.Brake);

            sensor = new DigitalInput(0);
            sensorTimer = new Timer();

    }


    public enum Mode{
        Comp,
        ConeIn,
        ConeOut,
        CubeIn,
        CubeOut,
    }

    Mode InfeedMode = Mode.Comp;
    
    @Override

    public void periodic() {


        switch(InfeedMode) {
            case Comp:{
                sensorTimer.reset();
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.COMP);
                break;
            }

            case ConeIn:{
                sensorTimer.start();
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_IN);
                if (sensor.get() & sensorTimer.hasElapsed(0.2)){
                    sensorTimer.reset();
                    InfeedMode = Mode.Comp;
                }
                break;
            }


            case ConeOut:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CONE_OUT);
                break;
            }


            case CubeIn:{
                sensorTimer.start();
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_IN);
                if (sensor.get() & sensorTimer.hasElapsed(0.175)){
                    sensorTimer.reset();
                    InfeedMode = Mode.Comp;
                }
                break;
            }


            case CubeOut:{
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedConstants.CUBE_OUT);
                break;
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

}
