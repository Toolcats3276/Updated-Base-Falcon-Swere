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
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    // private final Joystick m_DriveController = new Joystick(0);
    private final Joystick m_DriveController = new Joystick(0);

    private final Joystick m_flightStick = new Joystick(1);


    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(m_DriveController, 2);
    private final JoystickButton robotCentric = new JoystickButton(m_DriveController, 16);
  
    private final JoystickButton ActiveCompressor = new JoystickButton(m_flightStick,9);
    private final JoystickButton ArmOut = new JoystickButton(m_flightStick, 5);
    private final JoystickButton ArmIn = new JoystickButton(m_flightStick, 6);

   


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ArmSS s_Arm = new ArmSS();
    private final CompressorSS s_Compressor = new CompressorSS();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> Math.pow(m_DriveController.getRawAxis(translationAxis), 1)/2,
                () -> Math.pow(m_DriveController.getRawAxis(strafeAxis), 1)/2, 
                () -> Math.pow(m_DriveController.getRawAxis(rotationAxis), 1)/2, 
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
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        ArmIn.onTrue(new InstantCommand(() -> s_Arm.In()));
        ArmOut.onTrue(new InstantCommand(() -> s_Arm.Out()));
        ActiveCompressor.onTrue(new InstantCommand(() -> s_Compressor.Active()));
        ActiveCompressor.onFalse(new InstantCommand(() -> s_Compressor.Idle()));
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

