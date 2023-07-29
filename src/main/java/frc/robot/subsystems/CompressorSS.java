package frc.robot.subsystems;


import frc.robot.Constants.PneumaticConstants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CompressorSS extends SubsystemBase{

    Compressor pcmCompressor = new Compressor(PneumaticConstants.COMPRESSOT_ID, PneumaticsModuleType.REVPH);

    public enum Mode{
        Idle,
        Active;
      }
  
      Mode CompressorMode = Mode.Idle;
  
      @Override
      public void periodic(){
          switch (CompressorMode) {
            case Idle:
            pcmCompressor.enableAnalog(PneumaticConstants.MIN_IDLE_PRESSURE, PneumaticConstants.MAX_IDLE_PRESSURE);

            case Active:
            pcmCompressor.enableAnalog(PneumaticConstants.MIN_ACTIVE_PRESSURE,PneumaticConstants.MAX_ACTIVE_PRESSURE);
        }

        SmartDashboard.putBoolean("compressor", pcmCompressor.isEnabled());
        SmartDashboard.putNumber("airPressure", pcmCompressor.getPressure());
    
      }


      public void Idle(){
        CompressorMode = Mode.Idle;
      }
      public void Active(){
        CompressorMode = Mode.Active;
      }
}

    