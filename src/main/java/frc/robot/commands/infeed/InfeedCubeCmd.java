package frc.robot.commands.infeed;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InfeedSS;

public class InfeedCubeCmd extends CommandBase {
    
    private InfeedSS s_Infeed;
    // private SensorSS s_Sensor;


    public InfeedCubeCmd(InfeedSS s_Infeed) {
        this.s_Infeed = s_Infeed;

        addRequirements(s_Infeed);
    }

    @Override
    public void initialize() {
        s_Infeed.CubeIn();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

        }


    @Override
    public boolean isFinished() {

        return true;


        
        }
    }
    

