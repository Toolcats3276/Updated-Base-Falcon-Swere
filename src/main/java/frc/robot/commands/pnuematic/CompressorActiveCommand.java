package frc.robot.commands.pnuematic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CompressorSS;

public class CompressorActiveCommand extends CommandBase {
    
    private CompressorSS s_Compressor;


    public CompressorActiveCommand(CompressorSS Compressor) {
        this.s_Compressor = Compressor;
        addRequirements(s_Compressor);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        s_Compressor.Active();
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}