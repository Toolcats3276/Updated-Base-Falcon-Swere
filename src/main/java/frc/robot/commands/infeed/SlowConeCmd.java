package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.InfeedConstants;
import frc.robot.subsystems.InfeedSS;

public class SlowConeCmd extends CommandBase {
    
    private InfeedSS s_Infeed;


    public SlowConeCmd(InfeedSS s_Infeed) {
        this.s_Infeed = s_Infeed;
        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {
        s_Infeed.setSpeed(InfeedConstants.SLOW_CONE_OUT);
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

