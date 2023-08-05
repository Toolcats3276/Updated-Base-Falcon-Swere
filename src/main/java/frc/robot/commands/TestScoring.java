package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSS;
import frc.robot.subsystems.ArmSS;

public class TestScoring extends CommandBase {
    
    private ArmSS s_Arm;
    private WristSS s_Wrist;

    public TestScoring(ArmSS Arm, WristSS Wrist) {
        this.s_Arm = Arm;
        this.s_Wrist = Wrist;
        addRequirements(s_Arm, s_Wrist);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        s_Arm.Out();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
