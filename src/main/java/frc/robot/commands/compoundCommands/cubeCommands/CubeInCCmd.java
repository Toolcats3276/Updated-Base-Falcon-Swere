package frc.robot.commands.compoundCommands.cubeCommands;

import frc.robot.Constants.WristConstants;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCubeCmd;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;
// import frc.robot.subsystems.SensorSS;


public class CubeInCCmd extends SequentialCommandGroup{

    
    public CubeInCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed) {

        addCommands(
                new ArmInCmd(s_Arm),
                new WaitCommand(0.2),
                new ParallelCommandGroup(
                    new InfeedCubeCmd(s_Infeed),
                    new PIDWristCmd(s_Wrist, WristConstants.CUBE_INFEED))
                );
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
}