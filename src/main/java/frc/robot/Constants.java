package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;


public final class Constants {
    public static final double stickDeadband = 0.00005;



    //infeed motor speeds
    public static final class InfeedConstants{

        public static final int INFEED_MOTOR_ID = 16;

        public static final double COMP = 0.00;
        public static final double CONE_IN = 0.75;
        public static final double CONE_OUT = -0.5;
        public static final double SLOW_CONE_OUT = -0.2;
        public static final double CUBE_IN = -0.7;
        public static final double CUBE_OUT = 1.00;
        public static final double SLOW_CUBE_OUT = 0.2;

    }

    public static final class WristConstants {
        public static final int WRIST_MOTOR_ID = 15;
        public static final int WRIST_ENCODER_ID = 17;

        public static final double MANUAL_WRIST_UP_SPEED = 0.25;
        public static final double MANUAL_WRIST_DOWN_SPEED = -0.25;

        public static final double WRIST_SETPOINT_MAX = 0.88;
        public static final double WRIST_SETPOINT_MIN = 0.22;
        public static final double WRIST_PID_TOLERANCE = 0.05;

        public static final double ABSOLUTE_POS = 6;
        public static final double COMP = ABSOLUTE_POS;
        public static final double HIGH_CONE = ABSOLUTE_POS - 117;
        public static final double MID_CONE = ABSOLUTE_POS - 155;
        public static final double LOW_CONE = ABSOLUTE_POS;

        public static final double HIGH_CUBE = ABSOLUTE_POS - 67;
        public static final double MID_CUBE = ABSOLUTE_POS;
        public static final double LOW_CUBE = ABSOLUTE_POS;

        public static final double CONE_INFEED = ABSOLUTE_POS - 139.41;
        public static final double CUBE_INFEED = ABSOLUTE_POS - 110.77;
        
    }

    public static final class PneumaticConstants {

        public static final int COMPRESSOR_ID = 50;
        public static final int SOLENOID_ID = 50;

        public static final int MIN_IDLE_PRESSURE = 0;
        public static final int MAX_IDLE_PRESSURE = 120;
        public static final int MIN_ACTIVE_PRESSURE = 100;
        public static final int MAX_ACTIVE_PRESSURE = 120;

        public static final int IN_CHANNEL = 3;
        public static final int OUT_CHANNEL = 4;
        public static final int SLIDE_IN_CHANNEL = 6;
        public static final int SLIDE_OUT_CHANNEL = 7;
    }

    public static final class Swerve {
        public static final int pigeonID = 10;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-


        public static final COTSFalconSwerveConstants chosenModule =  
        
            COTSFalconSwerveConstants.SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(22); 
        public static final double wheelBase = Units.inchesToMeters(22); 
        public static final double wheelCircumference = chosenModule.wheelCircumference;


        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;
        public static final double angleKF = chosenModule.angleKF;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.05; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = (0.32 / 12); //TODO: This must be tuned to specific robot
        public static final double driveKV = (1.51 / 12);
        public static final double driveKA = (0.27 / 12);

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 5;

        /** Radians per Second */
        public static final double maxAngularVelocity = 12; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { 
            public static final int driveMotorID = 5;
            public static final int angleMotorID = 3;
            public static final int canCoderID = 14;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(49.658);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { 
            public static final int driveMotorID = 6;
            public static final int angleMotorID = 7;
            public static final int canCoderID = 11;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(118.125);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { 
            public static final int driveMotorID = 2;
            public static final int angleMotorID = 1;
            public static final int canCoderID = 13;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(82.6171875);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { 
            public static final int driveMotorID = 4;
            public static final int angleMotorID = 8;
            public static final int canCoderID = 12;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(187.64648437500003);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }

}
