package frc.robot.commands.compoundCommands.coneCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCompCmd;
import frc.robot.commands.infeed.SlowCubeCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class ConeMidCCmd extends SequentialCommandGroup{

    
    public ConeMidCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
                new InfeedCompCmd(s_Infeed),
                new ArmOutCmd(s_Arm),
                new SlowCubeCmd(s_Infeed),
                new WaitCommand(0.5),
                new InfeedCompCmd(s_Infeed),
                new PIDWristCmd(s_Wrist, WristConstants.MID_CONE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}