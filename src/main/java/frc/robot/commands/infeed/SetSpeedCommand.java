package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InfeedSS;

public class SetSpeedCommand extends CommandBase {
    
    private InfeedSS s_Infeed;
    private double speed;
    private final EventLoop m_loop = new EventLoop();


    public SetSpeedCommand(InfeedSS s_Infeed, double speed) {
        this.s_Infeed = s_Infeed;
        this.speed = speed;
        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {
        s_Infeed.setSpeed(speed);
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
