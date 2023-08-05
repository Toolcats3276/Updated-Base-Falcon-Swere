package frc.robot.commands.pnuematic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSS;

public class ArmOutCommand extends CommandBase {
    
    private ArmSS s_Arm;


    public ArmOutCommand(ArmSS Arm) {
        this.s_Arm = Arm;
        addRequirements(s_Arm);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        s_Arm.Out();
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
