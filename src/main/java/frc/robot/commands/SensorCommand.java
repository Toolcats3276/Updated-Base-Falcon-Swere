// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.SensorSS;

// public class SensorCommand extends CommandBase {
    
//     private SensorSS s_Sensor;

//     public SensorCommand(SensorSS s_Sensor) {
//         this.s_Sensor = s_Sensor;
//         addRequirements(s_Sensor);
//     }

//     @Override
//     public void initialize() {
//         s_Sensor.TimerReset();

//     }

//     @Override
//     public void execute() {
//         if(s_Sensor.isTriggered()){
//             s_Sensor.TimerStart();
//         }

//     }

//     @Override
//     public void end(boolean interrupted) {
//             s_Sensor.TimerReset();
//         }


//     @Override
//     public boolean isFinished() {
//         if(s_Sensor.isTriggered() && s_Sensor.hasElapsed(0.75)){
//             s_Sensor.TimerReset();
//             return true; 
//         }
    
//         else{
//             return false;
//         }
    
// }
// }
