package frc.robot.commands.compoundCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.WristConstants;
import frc.robot.commands.compoundCommands.coneCommands.ConeInCommand;
import frc.robot.commands.compoundCommands.coneCommands.StoreConeCommand;
import frc.robot.commands.infeed.InfeedCompCommand;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class InfeedCommand extends SequentialCommandGroup{

    
    public InfeedCommand(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide) {

        addCommands(
            // (new RepeatCommand(
            //     new ConditionalCommand(
            //         new StoreConeCommand(s_Wrist, s_Arm, s_Infeed, s_Slide), 
            //         new ConeInCommand(s_Wrist, s_Arm, s_Infeed, s_Slide),
            //         () -> sensorTrigger.getAsBoolean())))
        );
               
            
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
} 