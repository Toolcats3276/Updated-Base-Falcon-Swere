package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InfeedSS;

public class InfeedCompCommand extends CommandBase {
    
    private InfeedSS s_Infeed;


    public InfeedCompCommand(InfeedSS Infeed) {
        this.s_Infeed = Infeed;
        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {
        s_Infeed.Comp();
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
