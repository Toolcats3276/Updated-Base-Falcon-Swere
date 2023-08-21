package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.ModeMemSS;

public class OutfeedConeCommand extends CommandBase {
    
    private InfeedSS s_Infeed;
    private ModeMemSS s_ModeMem;


    public OutfeedConeCommand(InfeedSS s_Infeed, ModeMemSS s_ModeMem) {
        this.s_Infeed = s_Infeed;
        this.s_ModeMem = s_ModeMem;
        addRequirements(s_Infeed, s_ModeMem);
    }

    @Override
    public void initialize() {
        s_Infeed.ConeOut();
        s_ModeMem.FalseCone();
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
