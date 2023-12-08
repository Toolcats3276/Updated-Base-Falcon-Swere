package frc.robot.subsystems;

import frc.robot.Constants.WristConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
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
    private SimpleMotorFeedforward wristFFController; 

    private double setPoint;
    private double output;

    private double g = 0.0656;
    private double v = 0.0002;

    private double p = 0.028;
    private double i = 0;   
    private double d = 0.0018;
    private final double MAX_OUTPUT = 0.6;



    public WristSS() {
        m_wristMotor = new TalonFX(WristConstants.WRIST_MOTOR_ID);
        m_wristMotor.configFactoryDefault();
        m_wristMotor.setNeutralMode(NeutralMode.Brake);

        wristPot = new AnalogPotentiometer(0);

        wristEncoder = new CANCoder(WristConstants.WRIST_ENCODER_ID);
        wristEncoder.configFactoryDefault();
        wristEncoder.setPositionToAbsolute();

        m_wristMotor.configRemoteFeedbackFilter(wristEncoder, WristConstants.WRIST_ENCODER_ID);

        wristPIDController = new PIDController(p, i, d);
        wristPIDController.setTolerance(1.5);

        wristFFController = new SimpleMotorFeedforward(g, v);





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
            // if ((wristEncoder.getPosition() >= WristConstants.WRIST_SETPOINT_MAX && output > 0.0) || 
            //     (wristEncoder.getPosition() <= WristConstants.WRIST_SETPOINT_MIN && output > 0.0)) {
            //     m_wristMotor.set(TalonFXControlMode.PercentOutput,0);
            //     return;}

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
                    wristPIDController.setSetpoint(setPoint);
                    output = MathUtil.clamp(wristPIDController.calculate(wristEncoder.getPosition(), setPoint) + wristFFController.calculate(1, 0.5), -MAX_OUTPUT, MAX_OUTPUT);

                    m_wristMotor.set(TalonFXControlMode.PercentOutput, output);
                    
                    break;
                }

                

            }

            SmartDashboard.putNumber("wristPos", wristEncoder.getPosition());
            SmartDashboard.putNumber("wristAbsolutePos", wristEncoder.getAbsolutePosition());
            SmartDashboard.putNumber("output", output);
            SmartDashboard.putNumber("setpoint", setPoint);

            
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

    public double returnSetPoint(){
        System.out.println(setPoint);
        return setPoint;
    }

    public double returnPostition(){
        return wristEncoder.getPosition();
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
