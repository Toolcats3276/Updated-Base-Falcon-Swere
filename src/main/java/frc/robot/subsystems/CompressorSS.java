package frc.robot.subsystems;


import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CompressorSS extends SubsystemBase{

    Compressor pcmCompressor = new Compressor(50, PneumaticsModuleType.REVPH);

    public enum Mode{
        Idle,
        Active;
      }
  
      Mode CompressorMode = Mode.Idle;
  
      @Override
      public void periodic(){
          switch (CompressorMode) {
            case Idle:
            pcmCompressor.enableAnalog(0, 120);

            case Active:
            pcmCompressor.enableAnalog(100, 120);
        }

        SmartDashboard.putNumber("airPressure", pcmCompressor.getPressure());

      }


      public void Idle(){
        CompressorMode = Mode.Idle;
      }
      public void Active(){
        CompressorMode = Mode.Active;
      }
}

    