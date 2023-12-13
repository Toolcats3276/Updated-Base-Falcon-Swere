package frc.robot.commands.compoundCommands.coneCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCompCmd;
import frc.robot.commands.infeed.SlowCubeCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;
import frc.robot.subsystems.InfeedSS;

import static frc.robot.Constants.WristConstants;

public class ConeHighCCmd extends SequentialCommandGroup{

    
    public ConeHighCCmd(WristSS s_Wrist, ArmSS s_Arm, SlideSS s_Slide, InfeedSS s_Infeed) {

        addCommands(
                new SlideOutCmd(s_Slide),
                new InfeedCompCmd(s_Infeed),
                new ArmOutCmd(s_Arm),
                new SlowCubeCmd(s_Infeed),
                new WaitCommand(0.5),
                new InfeedCompCmd(s_Infeed),
                new PIDWristCmd(s_Wrist, WristConstants.HIGH_CONE)
               
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
   
}