package frc.robot.commands;

import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;

public class ScoringCommand extends CommandBase {
    
    private WristSS s_Wrist;
    private double setPoint;
    private InfeedSS s_Infeed;
    private final EventLoop m_loop = new EventLoop();



    public ScoringCommand(WristSS Wrist, double setPoint, InfeedSS s_Infeed) {
        this.s_Wrist = Wrist;
        this.setPoint = setPoint;
        this.s_Infeed = s_Infeed;
        addRequirements(s_Wrist);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(s_Wrist.returnSetPoint() == WristConstants.COMP){
            s_Infeed.CubeOut();
        }
        else if(s_Wrist.returnSetPoint() == WristConstants.HIGH_CONE){
            s_Infeed.ConeOut();
        }
        else if(s_Wrist.returnSetPoint() == WristConstants.MID_CONE){
            s_Infeed.ConeOut();
        }
        else if(s_Wrist.returnSetPoint() == WristConstants.HIGH_CUBE){
            s_Infeed.CubeOut();
        }
        
     
        
        
    }

    @Override
    public void end(boolean interrupted) {
        s_Wrist.StopManual();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
