package frc.robot.commands.compoundCommands.coneCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCompCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class StoreConeCCmd extends SequentialCommandGroup{

    
    public StoreConeCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide) {

        addCommands(
                new WaitCommand(0.3),
                new SlideInCmd(s_Slide),
                new ArmInCmd(s_Arm),
                new WaitCommand(0.1), 
                new InfeedCompCmd(s_Infeed),
                new PIDWristCmd(s_Wrist, WristConstants.COMP)
        );
               
            
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
} 