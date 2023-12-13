package frc.robot.commands.compoundCommands.cubeCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SensorSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;

public class CubeInfeedCCmd extends SequentialCommandGroup{

    
    public CubeInfeedCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide, SensorSS s_Sensor) {

        addCommands(
            new RepeatCommand(
                new ConditionalCommand(
                    new StoreCubeCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide), 
                    new CubeInCCmd(s_Wrist, s_Arm, s_Infeed),
                    () -> s_Sensor.isTriggered()
                )
            )
        );
        
               
            
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
} 