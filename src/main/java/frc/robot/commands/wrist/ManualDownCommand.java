package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSS;

public class ManualDownCommand extends CommandBase {
    
    private WristSS s_Wrist;


    public ManualDownCommand(WristSS Wrist) {
        this.s_Wrist = Wrist;
        addRequirements(s_Wrist);
    }

    @Override
    public void initialize() {
        s_Wrist.DownManual();
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
