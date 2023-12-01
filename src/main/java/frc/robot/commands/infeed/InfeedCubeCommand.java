package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.InfeedSS;
import frc.robot.subsystems.SensorSS;
import frc.robot.subsystems.WristSS;

public class InfeedCubeCommand extends CommandBase {
    
    private InfeedSS s_Infeed;
    private SensorSS s_Sensor;
    private WristSS s_Wrist;


    public InfeedCubeCommand(InfeedSS s_Infeed, SensorSS s_Sensor, WristSS s_Wrist) {
        this.s_Infeed = s_Infeed;
        this.s_Sensor = s_Sensor;
        this.s_Wrist = s_Wrist;
        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {
        s_Infeed.CubeIn();
    }

    @Override
    public void execute() {
        if(s_Sensor.isTriggered()){
            s_Sensor.TimerStart();
        }
    }

    @Override
    public void end(boolean interrupted) {
            s_Wrist.setSetpoint(WristConstants.COMP);
            s_Infeed.Comp();
        }


    @Override
    public boolean isFinished() {
        if(s_Sensor.isTriggered() & s_Sensor.hasElapsed(0.5)){
            s_Sensor.TimerReset();
            return true; 
        }
    
        else{
            return false;
        }


        
        }
    }
    

