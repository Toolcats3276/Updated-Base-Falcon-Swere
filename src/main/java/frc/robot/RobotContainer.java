package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.compoundCommands.CompCommand;
import frc.robot.commands.compoundCommands.coneCommands.ConeHighCommand;
import frc.robot.commands.compoundCommands.coneCommands.ConeInCommand;
import frc.robot.commands.compoundCommands.coneCommands.ConeMidCommand;
import frc.robot.commands.compoundCommands.coneCommands.StoreConeCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeHighCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeInCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeLowCommand;
import frc.robot.commands.compoundCommands.cubeCommands.CubeMidCommand;
import frc.robot.commands.compoundCommands.cubeCommands.StoreCubeCommand;
import frc.robot.commands.infeed.InfeedCompCommand;
import frc.robot.commands.infeed.InfeedConeCommand;
import frc.robot.commands.infeed.InfeedCubeCommand;
import frc.robot.commands.infeed.OutfeedConeCommand;
import frc.robot.commands.infeed.OutfeedCubeCommand;
import frc.robot.commands.infeed.SlowConeCommand;
import frc.robot.commands.infeed.SlowCubeCommand;
import frc.robot.commands.pnuematic.ArmInCommand;
import frc.robot.commands.pnuematic.ArmOutCommand;
import frc.robot.commands.pnuematic.CompressorActiveCommand;
import frc.robot.commands.pnuematic.CompressorIdleCommand;
import frc.robot.commands.pnuematic.SlideInCommand;
import frc.robot.commands.pnuematic.SlideOutCommand;
// import frc.robot.commands.wrist.InfeedCommand;
import frc.robot.commands.wrist.ManualDownCommand;
import frc.robot.commands.wrist.ManualStopCommand;
import frc.robot.commands.wrist.ManualUpCommand;
// import frc.robot.subsystems.SensorSS;
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
    // private final SensorSS s_Sensor = new SensorSS();



    /* Controllers */
    private final Joystick m_driveController = new Joystick(0);
    private final Joystick m_flightStick = new Joystick(1);

    private final DigitalInput sensor = new DigitalInput(0);
    private final Timer sensorTimer = new Timer();

    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;


    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(m_driveController, 14);
    private final JoystickButton robotCentric = new JoystickButton(m_driveController, 0);

    private final Trigger sensorTrigger = new Trigger(() -> sensor.get());

    private final Trigger negateSensorTrigger = new Trigger(() -> ! sensor.get());
    
    private final JoystickButton Comp = new JoystickButton(m_driveController,2);
    private final JoystickButton ConeIn = new JoystickButton(m_driveController,7);
    private final JoystickButton CubeIn = new JoystickButton(m_driveController,6);
    
    private final JoystickButton HighCone = new JoystickButton(m_driveController,3);
    private final JoystickButton MidCone = new JoystickButton(m_driveController,10);
    private final JoystickButton LowCone = new JoystickButton(m_driveController,8);

    private final JoystickButton HighCube = new JoystickButton(m_driveController,4);
    // private final JoystickButton MidCube = new JoystickButton(m_driveController,0);
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
            new TeleopSwerve(
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
        // periodic();
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
        Shoot.onTrue(new ScoringCommand(s_Wrist, rotationAxis, s_Infeed));

        //moves wrist and arms to position and sets motorspeed
        Comp.onTrue(new CompCommand(s_Wrist, s_Arm, s_Infeed, s_Slide));

        ConeIn.onTrue(new RepeatCommand(
            new ConditionalCommand(
                new StoreConeCommand(s_Wrist, s_Arm, s_Infeed, s_Slide), 
                new ConeInCommand(s_Wrist, s_Arm, s_Infeed, s_Slide),
                () -> sensorTrigger.getAsBoolean())));
            // .onFalse(new CompCommand(s_Wrist, s_Arm, s_Infeed, s_Slide));
        
        CubeIn.onTrue(new RepeatCommand(
            new ConditionalCommand(
                new StoreCubeCommand(s_Wrist, s_Arm, s_Infeed, s_Slide), 
                new CubeInCommand(s_Wrist, s_Arm, s_Infeed),
                () -> sensorTrigger.getAsBoolean())));
            // .onFalse(new CompCommand(s_Wrist, s_Arm, s_Infeed, s_Slide));

        HighCone.onTrue(new ConeHighCommand(s_Wrist, s_Arm, s_Slide, s_Infeed));
        MidCone.onTrue(new ConeMidCommand(s_Wrist, s_Arm, s_Infeed));
        LowCone.onTrue(new SlowConeCommand(s_Infeed));

        HighCube.onTrue(new CubeHighCommand(s_Wrist, s_Arm, s_Infeed));
        // MidCube.onTrue(new CubeMidCommand(s_Wrist, s_Arm, s_Infeed));
        LowCube.onTrue(new SlowCubeCommand(s_Infeed));
      
      
        //manual pnuematic and wrist controls
        ArmIn.onTrue(new ArmInCommand(s_Arm));
        ArmOut.onTrue(new ArmOutCommand(s_Arm));

        SlideOut.onTrue(new SlideOutCommand(s_Slide));
        SlideIn.onTrue(new SlideInCommand(s_Slide));

        WristUp.whileTrue(new ManualUpCommand(s_Wrist))
            .onFalse(new ManualStopCommand(s_Wrist));
        WristDown.whileTrue(new ManualDownCommand(s_Wrist))
            .onFalse(new ManualStopCommand(s_Wrist));

        ActiveCompressor.whileTrue(new CompressorActiveCommand(s_Compressor))
            .onFalse(new CompressorIdleCommand(s_Compressor));

        Cancel1.onTrue(new InfeedCompCommand(s_Infeed))
            .onTrue(new ManualStopCommand(s_Wrist));

        Cancel2.onTrue(new InfeedCompCommand(s_Infeed))
            .onTrue(new ManualStopCommand(s_Wrist));
      
    }



    
    // public void periodic() {
    //     System.out.println("periodic");
    //     if(sensorTrigger.getAsBoolean()){
    //         sensorTimer.start();
    //         System.out.println("start");
    //     }

    //     if(HighCone.getAsBoolean()){
    //         new ConeHighCommand(s_Wrist, s_Arm, s_Slide, s_Infeed);
    //     }
    //     else if(ConeIn.getAsBoolean() && sensorTrigger.getAsBoolean() && sensorTimer.hasElapsed(0.1)){
    //         new CompCommand(s_Wrist, s_Arm, s_Infeed, s_Slide);
    //         sensorTimer.reset();
    //     }
        
    
    // }

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

