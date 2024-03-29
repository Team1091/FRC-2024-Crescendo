package frc.robot;

public final class Constants {

    public static class Shooter {
        public static final int frontLeftMotorChannel = 11;
        public static final int backLeftMotorChannel = 12;
        public static final int frontRightMotorChannel = 13;
        public static final int backRightMotorChannel = 14;
        public static final double autoShooterSpeed = -1;
        public static final int outtakeTime = 1500;
        public static final int revTime = 1500;
        public static final double backWheelPercent = 1;
        public static double speaker = 1;
    }

    public static class Climber {
        public static final int leftClimbMotorChannel = 2;
        public static final int rightClimbMotorChannel = 3;
        public static final double climberSpeed = .5;
        public static final int fastClimberSpeed = 1;
    }

    public static class WheelModule {
        public static final int FRONT_LEFT = 0;
        public static final int FRONT_RIGHT = 1;
        public static final int BACK_LEFT = 2;
        public static final int BACK_RIGHT = 3;
    }

    // TODO
    public static final class NotePickup {
        public final static int inputMotorChannel =1; //is a motor not a solenoid
        public final static double inputMotorSpeed = -1; //make negative to reverse rotor
        public final static int pivotMotor = 0;
        public final static int pivotEncoder1 = 1;
        public final static int pivotEncoder2 = 2;
        public final static int outEncoderPosition = -950;
        public final static int inEncoderPosition = 0;
        public static double pivotSpeed = -.65;
        public static int limitSwitch = 4;
        public static int pivotEncoder3 = 3;
        public static double spitSpeed = 1;
        public static int ampPosition = -300;
    }

    public static final class Auto {
        public final static double robotLength = .9144;
        public final static double intakeProtrusionLength = .2032;
        public final static double noteDiameter = .36;
        public final static double noteRadius = noteDiameter/2;
        public final static double xDistanceToNote = 1.85 - robotLength - intakeProtrusionLength - noteRadius;
        public final static double yDistanceToNote = 1.6;
        public final static double directionToNote = Math.atan(yDistanceToNote/(xDistanceToNote-.25)) * (180/Math.PI);
        public static double diagonalDistanceToNote  = Math.hypot((xDistanceToNote-.25), yDistanceToNote);
        public static double normalSpeed = .08;
        public static double slowSpeed = .05;
        public static double rotateSpeed = 2;
        public static double clearDistance = .67;
    }

    public static final class Align {
        public static final int redShooterID = 4;
        public static final int blueShooterID = 7;
        public static final double errorMargin = 0.05;
        public static final double shooterRange = .75;
        public static double alignSpeed = 2.6;
    }

    public static final double linearDeadband = 0.1;
    public static final double rotationalDeadband = .1;

}
