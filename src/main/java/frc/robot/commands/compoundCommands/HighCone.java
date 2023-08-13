package frc.robot.commands.compoundCommands;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;

import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class HighCone extends SequentialCommandGroup{

    
    public HighCone(WristSS s_Wrist, ArmSS s_Arm) {

        addCommands(
                // new ArmOutCommand(s_Arm),
                new PIDWristCommand(s_Wrist, WristConstants.HIGH_CONE)
                );
                
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}