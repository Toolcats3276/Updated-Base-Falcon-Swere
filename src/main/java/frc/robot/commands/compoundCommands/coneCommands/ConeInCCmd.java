package frc.robot.commands.compoundCommands.coneCommands;

import frc.robot.Constants.WristConstants;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedConeCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;


public class ConeInCCmd extends SequentialCommandGroup{

    
    public ConeInCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide) {

        addCommands(
                new ArmInCmd(s_Arm),
                new SlideInCmd(s_Slide),
                new ParallelCommandGroup(
                    new InfeedConeCmd(s_Infeed),
                    new PIDWristCmd(s_Wrist, WristConstants.CONE_INFEED)
                )
        );
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
}