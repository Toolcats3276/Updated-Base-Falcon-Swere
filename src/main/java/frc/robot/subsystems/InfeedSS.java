package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.InfeedSpeed;
import frc.robot.subsystems.StateMemory.*;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
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
    private final DigitalInput Sensor = new DigitalInput(0);
    private final Timer sensor_Timer = new Timer();



    public enum Mode{
        Comp,
        ConeIn,
        ConeOut,
        CubeIn,
        CubeOut,
        Kill;
    }

    Mode InfeedMode = Mode.Comp;
    
    

    public Mode InfeedMode(Mode InfeedMode) {
        switch (InfeedMode) {
            case Comp:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.Comp);

            case ConeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.ConeIn);

            case ConeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.ConeOut);

            case CubeIn:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.CubeIn);

            case CubeOut:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput, InfeedSpeed.CubeOut);

            case Kill:
                m_infeedMotor.set(TalonFXControlMode.PercentOutput,InfeedSpeed.Kill);       
        }
        
        return InfeedMode;
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

public void Kill(){
    InfeedMode = Mode.Kill;
}

}