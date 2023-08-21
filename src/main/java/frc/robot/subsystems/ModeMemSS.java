package frc.robot.subsystems;

import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ModeMemSS extends SubsystemBase{

  public BooleanSupplier Cone = () -> false;
  public BooleanSupplier Cube = () -> false;
  public Boolean ConeBool = false;
  public Boolean CubeBool = false;
      
  public void TrueCone(){
    Cone = () -> true;
  }

  public void TrueCube(){
    Cube = () -> true;     
  }
  
  public void FalseCone(){
    Cone = () -> false;
  }

  public void FalseCube(){
    Cube = () -> false;     
  }
      
}

    