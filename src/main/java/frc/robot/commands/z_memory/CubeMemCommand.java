package frc.robot.commands.z_memory;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ModeMemSS;

public class CubeMemCommand extends CommandBase {
    
    private ModeMemSS s_ModeMem;


    public CubeMemCommand(ModeMemSS s_ModeMem) {
        this.s_ModeMem = s_ModeMem;
        addRequirements(s_ModeMem);
    }

    @Override
    public void initialize() {
        s_ModeMem.TrueCube();
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
