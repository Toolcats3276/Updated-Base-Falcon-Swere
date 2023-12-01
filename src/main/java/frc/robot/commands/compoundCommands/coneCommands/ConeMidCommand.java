package frc.robot.commands.compoundCommands.coneCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.WristConstants;
import frc.robot.commands.infeed.InfeedCompCommand;
import frc.robot.commands.infeed.SlowCubeCommand;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;

import static frc.robot.Constants.WristConstants;

public class ConeMidCommand extends SequentialCommandGroup{

    
    public ConeMidCommand(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
                new InfeedCompCommand(s_Infeed),
                new ArmOutCommand(s_Arm),
                new SlowCubeCommand(s_Infeed),
                new WaitCommand(0.5),
                new InfeedCompCommand(s_Infeed),
                new PIDWristCommand(s_Wrist, WristConstants.MID_CONE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}