package frc.robot.commands.compoundCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCompCommand;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class CompCommand extends SequentialCommandGroup{

    
    public CompCommand(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
                new ArmInCommand(s_Arm),
                new WaitCommand(0.5),
                new ParallelCommandGroup(
                    new PIDWristCommand(s_Wrist, WristConstants.COMP),
                    new InfeedCompCommand(s_Infeed))
                );
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
} 