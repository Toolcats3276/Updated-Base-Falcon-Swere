package frc.robot.subsystems;


import frc.robot.Constants.PneumaticConstants;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ModeMemSS extends SubsystemBase{

    public BooleanSupplier Cone = null;
    public BooleanSupplier Cube = null;
      
        public Boolean TrueCone(){
        Cone = () -> true;
        return true;
        }

      
      public Boolean FalseCube(){
        Cube = () -> true;
        return false;
        }
      
}

    