package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class CubeLowCommand extends SequentialCommandGroup{

    
    public CubeLowCommand(WristSS s_Wrist, ArmSS s_Arm) {

        addCommands(
                new ArmInCommand(s_Arm),
                new WaitCommand(0.5),
                new PIDWristCommand(s_Wrist, WristConstants.LOW_CUBE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}