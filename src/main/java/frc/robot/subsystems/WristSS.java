package frc.robot.subsystems;

import frc.robot.Constants.WristConstants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXPIDSetConfiguration;
import com.ctre.phoenix.sensors.CANCoder;

public class WristSS extends SubsystemBase {

    private TalonFX m_wristMotor;
    private CANCoder wristEncoder;
    private AnalogPotentiometer wristPot;
    private PIDController wristPIDController;

    private double setPoint;

    private double p = 0;
    private double i = 0;
    private double d = 0;
    private double ff = 0;
    private double MAX_OUTPUT = 0;

    public WristSS() {
        m_wristMotor = new TalonFX(WristConstants.WRIST_MOTOR_ID);
        m_wristMotor.configFactoryDefault();
        m_wristMotor.setNeutralMode(NeutralMode.Brake);

        wristPot = new AnalogPotentiometer(0);

        wristEncoder = new CANCoder(WristConstants.WRIST_ENCODER_ID);
        wristEncoder.configFactoryDefault();

        m_wristMotor.configRemoteFeedbackFilter(wristEncoder, WristConstants.WRIST_ENCODER_ID);

        wristPIDController = new PIDController(p, i, d);



    }

    public enum Mode{
        ManualUp,
        ManualDown,
        ManualStop,
        PID;
    }

    Mode WristMode = Mode.ManualStop;

    
    @Override
        
        public void periodic(){

            switch (WristMode) {
                case ManualUp:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, WristConstants.MANUAL_WRIST_UP_SPEED);
                    m_wristMotor.getSelectedSensorPosition();
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
                case PID:{
                   // m_wristMotor.set(TalonFXControlMode.Position, wristPIDController, setPoint);
                }

            }

            SmartDashboard.putNumber("wristPos", wristEncoder.getPosition());
            SmartDashboard.putNumber("wristAbsolutePos", wristEncoder.getAbsolutePosition());
            SmartDashboard.putNumber("motorEncoderPos", m_wristMotor.getSelectedSensorPosition());
            
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
