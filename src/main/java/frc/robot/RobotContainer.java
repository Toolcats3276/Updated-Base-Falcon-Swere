package frc.robot;

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
    private final Joystick driver = new Joystick(0);
    private final Joystick m_driveController = new Joystick(0);


    /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kZ.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(m_driveController, 2);
    private final JoystickButton robotCentric = new JoystickButton(m_driveController, 16);
    
    private final JoystickButton Comp = new JoystickButton(m_driveController, 2);
    private final JoystickButton ConeIn = new JoystickButton(m_driveController, 7);
    private final JoystickButton ConeOut = new JoystickButton(m_driveController, 3);
    private final JoystickButton CubeIn = new JoystickButton(m_driveController, 6);
    private final JoystickButton CubeOut = new JoystickButton(m_driveController, 10);
    private final JoystickButton Kill = new JoystickButton(m_driveController, 5);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final InfeedSS s_Infeed = new InfeedSS();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -m_driveController.getRawAxis(translationAxis), 
                () -> -m_driveController.getRawAxis(strafeAxis), 
                () -> -m_driveController.getRawAxis(rotationAxis), 
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
        Comp.onTrue (new InstantCommand(() -> s_Infeed.Comp()));
        ConeIn.onTrue (new InstantCommand(() -> s_Infeed.ConeIn()));
        ConeOut.onTrue (new InstantCommand(() -> s_Infeed.ConeOut()));
        CubeIn.onTrue (new InstantCommand(() -> s_Infeed.CubeIn()));
        CubeOut.onTrue (new InstantCommand(() -> s_Infeed.CubeOut()));
        Kill.onTrue (new InstantCommand(() -> s_Infeed.Kill()));
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
