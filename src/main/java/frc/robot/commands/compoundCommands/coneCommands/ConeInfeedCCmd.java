package frc.robot.commands.compoundCommands.coneCommands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.compoundCommands.CompCCmd;
import frc.robot.commands.infeed.InfeedConeCmd;
import frc.robot.subsystems.ArmSS;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SensorSS;
import frc.robot.subsystems.SlideSS;
import frc.robot.subsystems.WristSS;


public class ConeInfeedCCmd extends SequentialCommandGroup{

    
    public ConeInfeedCCmd(WristSS s_Wrist, ArmSS s_Arm, InfeedSS s_Infeed, SlideSS s_Slide, SensorSS s_Sensor) {

        addCommands(
                    new RepeatCommand(
                        new ConditionalCommand(
                            new StoreConeCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide), 
                            new ConeInCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide),
                            () -> s_Sensor.isTriggered())
                    )
        );
        
        
               
            
        addRequirements(s_Wrist, s_Arm, s_Infeed);
    }
    
    
} 