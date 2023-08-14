package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class CubeHighCommand extends SequentialCommandGroup{

    
    public CubeHighCommand(WristSS s_Wrist, ArmSS s_Arm) {

        addCommands(
                new ArmOutCommand(s_Arm),
                new WaitCommand(0.5),
                new PIDWristCommand(s_Wrist, WristConstants.HIGH_CUBE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}