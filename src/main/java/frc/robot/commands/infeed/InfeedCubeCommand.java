package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InfeedSS;

public class InfeedCubeCommand extends CommandBase {
    
    private InfeedSS s_Infeed;


    public InfeedCubeCommand(InfeedSS Infeed) {
        this.s_Infeed = Infeed;
        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        s_Infeed.CubeIn();
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
