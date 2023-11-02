package frc.robot.subsystems;

import frc.robot.Constants.WristConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

public class WristSS extends SubsystemBase {

    private TalonFX m_wristMotor;
    private CANCoder wristEncoder;
    private AnalogPotentiometer wristPot;
    private PIDController wristPIDController;

    private double setPoint;
    private double output;

    private double p = 3;
    private double i = 0;
    private double d = 0;
    private double ff = 0;
    private double MAX_OUTPUT = 0.6;
    private double MIN_OUTPUT = -0.6;

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

                //  stops motor when encoder position excedes their max and min position (manual mode only)
            if ((wristEncoder.getPosition() >= WristConstants.WRIST_SETPOINT_MAX && output > 0.0) || 
                (wristEncoder.getPosition() <= WristConstants.WRIST_SETPOINT_MIN && output > 0.0)) {
                m_wristMotor.set(TalonFXControlMode.PercentOutput,0);
                return;}

            switch (WristMode) {
                case ManualUp:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, WristConstants.MANUAL_WRIST_UP_SPEED);
                    m_wristMotor.getSelectedSensorPosition();
                    output = WristConstants.MANUAL_WRIST_UP_SPEED;
                    break;
                }
                case ManualDown:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, WristConstants.MANUAL_WRIST_DOWN_SPEED);
                    output = WristConstants.MANUAL_WRIST_DOWN_SPEED;
                    break;
                }
                case ManualStop:{
                    m_wristMotor.set(TalonFXControlMode.PercentOutput, 0);
                    output = 0;
                    break;
                }
                case PID:{
                   // m_wristMotor.set(TalonFXControlMode.Position, wristPIDController, setPoint);
                    wristPIDController.setSetpoint(setPoint);
                    output = MathUtil.clamp(wristPIDController.calculate(wristPot.get(), setPoint), -MAX_OUTPUT, MAX_OUTPUT);

                    m_wristMotor.set(TalonFXControlMode.PercentOutput, output);
                    

                    System.out.println(wristPIDController.calculate(wristPot.get(), setPoint));
                    if(Math.abs(wristPot.get() - setPoint) < WristConstants.WRIST_PID_TOLERANCE) {
                        StopManual();
                        m_wristMotor.set(TalonFXControlMode.PercentOutput, 0);
                    }
                    break;
                }

            }

            // SmartDashboard.putNumber("wristPos", wristEncoder.getPosition());
            // SmartDashboard.putNumber("wristAbsolutePos", wristEncoder.getAbsolutePosition());
            // SmartDashboard.putNumber("motorEncoderPos", m_wristMotor.getSelectedSensorPosition());
            SmartDashboard.putNumber("potPos", wristPot.get());
            
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


    public void setSetpoint(double setPoint){
        this.setPoint = setPoint;
        WristMode = Mode.PID;
        System.out.println("Mode Set");
        wristPIDController.reset();
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
