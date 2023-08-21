package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.commands.z_memory.CubeMemCommand;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.ModeMemSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class CubeMidCommand extends SequentialCommandGroup{

    
    public CubeMidCommand(WristSS s_Wrist, ArmSS s_Arm, ModeMemSS s_ModeMem) {

        addCommands(
                new CubeMemCommand(s_ModeMem),
                new ArmInCommand(s_Arm),
                new WaitCommand(0.5),
                new PIDWristCommand(s_Wrist, WristConstants.MID_CUBE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}