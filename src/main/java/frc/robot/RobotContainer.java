package frc.robot;
//hi
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.compoundCommands.CompCommand;
import frc.robot.commands.compoundCommands.coneCommands.ConeHighCommand;
import frc.robot.commands.compoundCommands.coneCommands.ConeMidCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeHighCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeLowCommand;

import frc.robot.commands.infeed.InfeedCompCommand;
import frc.robot.commands.infeed.InfeedConeCommand;
import frc.robot.commands.infeed.InfeedCubeCommand;
import frc.robot.commands.infeed.OutfeedCubeCommand;

import frc.robot.commands.pnuematic.ArmInCommand;
import frc.robot.commands.pnuematic.ArmOutCommand;
import frc.robot.commands.pnuematic.CompressorActiveCommand;
import frc.robot.commands.pnuematic.CompressorIdleCommand;

import frc.robot.commands.wrist.ManualDownCommand;
import frc.robot.commands.wrist.ManualStopCommand;
import frc.robot.commands.wrist.ManualUpCommand;

import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick m_driveController = new Joystick(0);
    private final Joystick m_flightStick = new Joystick(1);



    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(m_driveController, 2);
    private final JoystickButton robotCentric = new JoystickButton(m_driveController, 16);
    
    // private final JoystickButton Comp = new JoystickButton(m_driveController, 2);
    private final JoystickButton ConeIn = new JoystickButton(m_driveController, 7);
    // private final JoystickButton ConeOut = new JoystickButton(m_driveController, 3);
    private final JoystickButton CubeIn = new JoystickButton(m_driveController, 6);
    private final JoystickButton CubeOut = new JoystickButton(m_driveController, 10);
  
    private final JoystickButton ArmIn = new JoystickButton(m_flightStick, 6);

    private final JoystickButton ActiveCompressor = new JoystickButton(m_flightStick,9);
    private final JoystickButton ArmOut = new JoystickButton(m_flightStick, 5);
    private final JoystickButton WristUp = new JoystickButton(m_flightStick,3);
    private final JoystickButton WristDown = new JoystickButton(m_flightStick,4);

    private final JoystickButton Comp = new JoystickButton(m_driveController,1);
    private final JoystickButton HighCone = new JoystickButton(m_driveController,2);
    private final JoystickButton MidCone = new JoystickButton(m_driveController,3);
    private final JoystickButton HighCube = new JoystickButton(m_driveController,4);
    private final JoystickButton MidCube = new JoystickButton(m_driveController,5);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final InfeedSS s_Infeed = new InfeedSS();
    private final ArmSS s_Arm = new ArmSS();
    private final CompressorSS s_Compressor = new CompressorSS();
    private final WristSS s_Wrist = new WristSS();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 

                () -> Math.pow(m_driveController.getRawAxis(translationAxis), 1)/2,
                () -> Math.pow(m_driveController.getRawAxis(strafeAxis), 1)/2, 
                () -> Math.pow(m_driveController.getRawAxis(rotationAxis), 1)/2, 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Drive Controller Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        //temporary controls for each motor speed
        Comp.onTrue (new InfeedCompCommand(s_Infeed));
        ConeIn.onTrue (new InfeedConeCommand(s_Infeed));
        // ConeOut.onTrue (new OutfeedConeCommand(s_Infeed));
        CubeIn.onTrue (new InfeedCubeCommand(s_Infeed));
        CubeOut.onTrue (new OutfeedCubeCommand(s_Infeed));
      
        //manual pnuematic controls
        ArmIn.onTrue(new ArmInCommand(s_Arm));
        ArmOut.onTrue(new ArmOutCommand(s_Arm));
        ActiveCompressor.whileTrue(new CompressorActiveCommand(s_Compressor));
        ActiveCompressor.onFalse(new CompressorIdleCommand(s_Compressor));
      
        //manual wrist controls
        WristUp.whileTrue(new ManualUpCommand(s_Wrist));
        WristDown.whileTrue(new ManualDownCommand(s_Wrist));
        WristUp.onFalse(new ManualStopCommand(s_Wrist));
        WristDown.onFalse(new ManualStopCommand(s_Wrist));

        Comp.onTrue(new CompCommand(s_Wrist, s_Arm));
        HighCone.onTrue(new ConeHighCommand(s_Wrist, s_Arm));
        MidCone.onTrue(new ConeMidCommand(s_Wrist, s_Arm));
        HighCube.onTrue(new CubeHighCommand(s_Wrist, s_Arm));
        MidCube.onTrue(new CubeLowCommand(s_Wrist, s_Arm));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}

