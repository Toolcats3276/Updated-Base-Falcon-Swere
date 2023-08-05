package frc.robot.commands.compoundCommands;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSS;

import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;

public class TestScoring extends CommandBase {
    
    private ArmSS s_Arm;
    private WristSS s_Wrist;
    private InfeedSS s_Infeed;
    private final TalonFX m_wristMotor = new TalonFX(WristConstants.WRIST_MOTOR_ID);
 

    public TestScoring(ArmSS Arm, WristSS Wrist, InfeedSS Infeed) {
        this.s_Arm = Arm;
        this.s_Wrist = Wrist;
        this.s_Infeed = Infeed;
        addRequirements(s_Arm, s_Wrist, s_Infeed);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        s_Arm.Out();
        s_Infeed.ConeOut();
    }

    @Override
    public void end(boolean interrupted) {
        s_Infeed.Comp();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
