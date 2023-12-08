package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.OutfeedCubeCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.WristSS;
import frc.robot.subsystems.InfeedSS;

import static frc.robot.Constants.WristConstants;

public class CubeLowCCmd extends SequentialCommandGroup{

    
    public CubeLowCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
                new ArmInCmd(s_Arm),
                new PIDWristCmd(s_Wrist, WristConstants.LOW_CUBE),
                new OutfeedCubeCmd(s_Infeed)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}