package frc.robot.subsystems;


import frc.robot.Constants.PneumaticConstants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CompressorSS extends SubsystemBase{

    Compressor pcmCompressor = new Compressor(PneumaticConstants.COMPRESSOT_ID, PneumaticsModuleType.REVPH);

    public enum State{
        Idle,
        Active;
      }
  
      State CompressorState = State.Idle;
  
      @Override

      public void periodic(){
          switch (CompressorState) {
            case Idle:{
            pcmCompressor.enableAnalog(0, PneumaticConstants.MAX_IDLE_PRESSURE);
            System.out.println("Idle");
            break;}

            case Active:{
            pcmCompressor.enableAnalog(PneumaticConstants.MIN_ACTIVE_PRESSURE,PneumaticConstants.MAX_ACTIVE_PRESSURE);
            System.out.println("Active");}
            break;

        }
 
            
        SmartDashboard.putBoolean("compressor", pcmCompressor.isEnabled());
        SmartDashboard.putNumber("airPressure", pcmCompressor.getPressure());
    
      }


      public void Idle(){
        CompressorState = State.Idle;
        System.out.println("Idle Mode");}

      
      public void Active(){
        CompressorState = State.Active;
        System.out.println("Active Mode");}
      
}

    