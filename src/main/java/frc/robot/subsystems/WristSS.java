package frc.robot.subsystems;

import frc.robot.Constants.WristConstants;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

public class WristSS extends SubsystemBase {


    private final TalonFX m_wristMotor = new TalonFX(WristConstants.WRIST_MOTOR_ID);
    private final CANCoder wristEncoder = new CANCoder(WristConstants.WRIST_ENCODER_ID);
    public final double encoderAngle = wristEncoder.getAbsolutePosition();

    public enum Mode{
        ManualUp,
        ManualDown,
        ManualStop;
    }

    Mode WristMode = Mode.ManualStop;

    
    @Override
        
        public void periodic(){
            switch (WristMode) {
                case ManualUp:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, WristConstants.MANUAL_WRIST_UP_SPEED);
                    break;
                }
                case ManualDown:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, WristConstants.MANUAL_WRIST_DOWN_SPEED);
                    break;
                }
                case ManualStop:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, 0);
                    break;
                }

            }

            SmartDashboard.putNumber("wristPos", wristEncoder.getPosition());
            SmartDashboard.putNumber("wristAbsolutePos", wristEncoder.getAbsolutePosition());
        }

    public void UpManual(){
        WristMode = Mode.ManualUp;
    }
    public void DownManual(){
        WristMode = Mode.ManualDown;
    }
    public void StopManual(){
        WristMode = Mode.ManualStop;
    }
    public void BrakeWrist(){
        m_wristMotor.configFactoryDefault();
        m_wristMotor.setNeutralMode(NeutralMode.Brake);
    }
    public void CoastWrist(){
        m_wristMotor.configFactoryDefault();
        m_wristMotor.setNeutralMode(NeutralMode.Coast);
    }
}