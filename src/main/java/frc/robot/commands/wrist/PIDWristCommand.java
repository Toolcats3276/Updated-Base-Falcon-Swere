package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.WristSS;

public class PIDWristCommand extends CommandBase {
    
    private WristSS s_Wrist;
    private double setPoint;



    public PIDWristCommand(WristSS Wrist, double setPoint) {
        this.s_Wrist = Wrist;
        this.setPoint = setPoint;
        addRequirements(s_Wrist);
    }

    @Override
    public void initialize() {
      s_Wrist.setSetpoint(setPoint);

    }

    @Override
    public void execute() {

        
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
