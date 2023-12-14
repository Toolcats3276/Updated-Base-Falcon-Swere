package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.compoundCommands.CompCCmd;
import frc.robot.commands.compoundCommands.coneCommands.ConeHighCCmd;
import frc.robot.commands.compoundCommands.coneCommands.ConeInfeedCCmd;
import frc.robot.commands.compoundCommands.coneCommands.ConeMidCCmd;
import frc.robot.commands.compoundCommands.cubeCommands.CubeHighCCmd;
import frc.robot.commands.compoundCommands.cubeCommands.CubeInfeedCCmd;
import frc.robot.commands.infeed.InfeedCompCmd;
import frc.robot.commands.infeed.SlowConeCmd;
import frc.robot.commands.infeed.SlowCubeCmd;
import frc.robot.commands.pnuematic.ArmInCmd;
import frc.robot.commands.pnuematic.ArmOutCmd;
import frc.robot.commands.pnuematic.CompressorActiveCmd;
import frc.robot.commands.pnuematic.CompressorIdleCmd;
import frc.robot.commands.pnuematic.SlideInCmd;
import frc.robot.commands.pnuematic.SlideOutCmd;
import frc.robot.commands.wrist.ManualDownCmd;
import frc.robot.commands.wrist.ManualStopCmd;
import frc.robot.commands.wrist.ManualUpCmd;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final InfeedSS s_Infeed = new InfeedSS();
    private final ArmSS s_Arm = new ArmSS();
    private final CompressorSS s_Compressor = new CompressorSS();
    private final WristSS s_Wrist = new WristSS();
    private final SlideSS s_Slide = new SlideSS();
    private final SensorSS s_Sensor = new SensorSS();



    /* Controllers */
    private final Joystick m_driveController = new Joystick(0);
    private final Joystick m_flightStick = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;


    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(m_driveController, 14);
    private final JoystickButton robotCentric = new JoystickButton(m_driveController, 0);
    
    private final JoystickButton Comp = new JoystickButton(m_driveController,2);
    private final JoystickButton ConeIn = new JoystickButton(m_driveController,7);
    private final JoystickButton CubeIn = new JoystickButton(m_driveController,6);
    
    private final JoystickButton HighCone = new JoystickButton(m_driveController,3);
    private final JoystickButton MidCone = new JoystickButton(m_driveController,10);
    private final JoystickButton LowCone = new JoystickButton(m_driveController,8);

    private final JoystickButton HighCube = new JoystickButton(m_driveController,4);
    private final JoystickButton LowCube = new JoystickButton(m_driveController,9);

    private final JoystickButton Shoot = new JoystickButton(m_driveController,1);

    private final JoystickButton ArmIn = new JoystickButton(m_flightStick, 6);
    private final JoystickButton ArmOut = new JoystickButton(m_flightStick, 5);

    private final JoystickButton WristUp = new JoystickButton(m_flightStick,3);
    private final JoystickButton WristDown = new JoystickButton(m_flightStick,4);

    private final JoystickButton SlideOut = new JoystickButton(m_flightStick, 1);
    private final JoystickButton SlideIn = new JoystickButton(m_flightStick, 2);

    private final JoystickButton ActiveCompressor = new JoystickButton(m_flightStick,9);
    
    private final JoystickButton Cancel1 = new JoystickButton(m_driveController, 5);
    private final JoystickButton Cancel2 = new JoystickButton(m_flightStick, 7);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerveCmd(
                s_Swerve, 

                () -> m_driveController.getRawAxis(translationAxis),
                () -> m_driveController.getRawAxis(strafeAxis),
                () -> m_driveController.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // s_Infeed.setDefaultCommand(new InfeedCompCommand(s_Infeed));
        // s_Arm.setDefaultCommand(new ArmInCommand(s_Arm));
        // s_Compressor.setDefaultCommand(new CompressorIdleCommand(s_Compressor));
        // s_Wrist.setDefaultCommand(new ManualStopCommand(s_Wrist));
        // s_Slide.setDefaultCommand(new SlideInCommand(s_Slide));

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

        //shoots game pieces when the highcone and shoot button are pressed
        Shoot.onTrue(new ScoringCmd(s_Wrist, s_Infeed));

        //moves wrist and arms to position and sets motorspeed
        Comp.onTrue(new CompCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide));

        ConeIn.onTrue(new ConeInfeedCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide, s_Sensor)
            .until(() -> s_Sensor.isDebounced()));
        CubeIn.onTrue(new CubeInfeedCCmd(s_Wrist, s_Arm, s_Infeed, s_Slide, s_Sensor)
            .until(() -> s_Sensor.isDebounced()));

        HighCone.onTrue(new ConeHighCCmd(s_Wrist, s_Arm, s_Slide, s_Infeed));
        MidCone.onTrue(new ConeMidCCmd(s_Wrist, s_Arm, s_Infeed));
        LowCone.onTrue(new SlowConeCmd(s_Infeed));

        HighCube.onTrue(new CubeHighCCmd(s_Wrist, s_Arm, s_Infeed));
        LowCube.onTrue(new SlowCubeCmd(s_Infeed));
      
      
        //manual pnuematic and wrist controls
        ArmIn.onTrue(new ArmInCmd(s_Arm));
        ArmOut.onTrue(new ArmOutCmd(s_Arm));

        SlideOut.onTrue(new SlideOutCmd(s_Slide));
        SlideIn.onTrue(new SlideInCmd(s_Slide));

        WristUp.whileTrue(new ManualUpCmd(s_Wrist))
            .onFalse(new ManualStopCmd(s_Wrist));
        WristDown.whileTrue(new ManualDownCmd(s_Wrist))
            .onFalse(new ManualStopCmd(s_Wrist));

        ActiveCompressor.whileTrue(new CompressorActiveCmd(s_Compressor))
            .onFalse(new CompressorIdleCmd(s_Compressor));

        Cancel1.onTrue(new InfeedCompCmd(s_Infeed))
            .onTrue(new ManualStopCmd(s_Wrist));

        Cancel2.onTrue(new InfeedCompCmd(s_Infeed))
            .onTrue(new ManualStopCmd(s_Wrist));
      
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

