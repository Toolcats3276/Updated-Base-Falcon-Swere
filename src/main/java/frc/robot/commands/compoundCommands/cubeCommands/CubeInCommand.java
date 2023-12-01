package frc.robot.commands.compoundCommands.cubeCommands;

import frc.robot.Constants.WristConstants;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.infeed.InfeedCubeCommand;
import frc.robot.commands.pnuematic.*;
import frc.robot.commands.wrist.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.WristSS;
import frc.robot.subsystems.SensorSS;


public class CubeInCommand extends SequentialCommandGroup{

    
    public CubeInCommand(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SensorSS s_Sensor) {

        addCommands(
                new ArmInCommand(s_Arm),
                // new WaitCommand(0.5),
                new ParallelCommandGroup(
                    new InfeedCubeCommand(s_Infeed, s_Sensor, s_Wrist),
                    new PIDWristCommand(s_Wrist, WristConstants.CUBE_INFEED))
                );
        addRequirements(s_Wrist, s_Arm, s_Infeed, s_Sensor);
    }
    
    
}