package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSS;

public class PIDWristCmd extends CommandBase {
    
    private WristSS s_Wrist;
    private double setPoint;



    public PIDWristCmd(WristSS Wrist, double setPoint) {
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
      
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
