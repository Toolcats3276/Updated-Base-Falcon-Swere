package frc.robot.commands.pnuematic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SlideSS;

public class SlideInCommand extends CommandBase {
    
    private SlideSS s_Slide;


    public SlideInCommand(SlideSS Slide) {
        this.s_Slide = Slide;
        addRequirements(s_Slide);
    }

    @Override
    public void initialize() {
        s_Slide.In();
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
