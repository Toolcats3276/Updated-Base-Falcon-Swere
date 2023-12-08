package frc.robot.commands.compoundCommands.coneCommands;

import frc.robot.Constants.WristConstants;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedConeCommand;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
// import frc.robot.commands.SensorCommand;
import frc.robot.commands.compoundCommands.CompCommand;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;
// import frc.robot.subsystems.SensorSS;


public class ConeInCommand extends SequentialCommandGroup{

    
    public ConeInCommand(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide) {

        addCommands(
                new ArmInCommand(s_Arm),
                new SlideInCommand(s_Slide),
                // new WaitCommand(0.1),
                new ParallelCommandGroup(
                    // new SequentialCommandGroup(
                    //     new SensorCommand(s_Sensor),
                    //     new CompCommand(s_Wrist, s_Arm, s_Infeed, s_Slide)
                    // ),
                    new InfeedConeCommand(s_Infeed, s_Wrist),
                    new PIDWristCommand(s_Wrist, WristConstants.CONE_INFEED)
                )
        );
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
}