package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSS;

public class ManualStopCommand extends CommandBase {
    
    private WristSS s_Wrist;


    public ManualStopCommand(WristSS Wrist) {
        this.s_Wrist = Wrist;
        addRequirements(s_Wrist);
    }

    @Override
    public void initialize() {
        s_Wrist.StopManual();
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
