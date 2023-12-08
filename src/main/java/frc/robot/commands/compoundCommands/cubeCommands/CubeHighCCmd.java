package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCompCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.WristSS;
import frc.robot.subsystems.InfeedSS;

import static frc.robot.Constants.WristConstants;

public class CubeHighCCmd extends SequentialCommandGroup{

    
    public CubeHighCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
            new InfeedCompCmd(s_Infeed),
            new ArmOutCmd(s_Arm),
            new WaitCommand(0.5),
            new PIDWristCmd(s_Wrist, WristConstants.HIGH_CUBE)
                );
        addRequirements(s_Wrist, s_Arm);
    }
    
    
}