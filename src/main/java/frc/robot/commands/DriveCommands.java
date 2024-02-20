package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.drive.Drive;

import java.util.function.DoubleSupplier;

public class DriveCommands {
    private static final double DEADBAND = 0.1;


    private static ShuffleboardTab tab = Shuffleboard.getTab("General");
    //    private static GenericEntry xLog, yLog, zLog;
    private static GenericEntry xLog = tab.add("x forward", 0.0).getEntry();
    private static GenericEntry yLog = tab.add("y left", 0.0).getEntry();
    private static GenericEntry zLog = tab.add("z rotate ccw", 0.0).getEntry();

    private DriveCommands() {
    }

    /**
     * Field relative drive command using two joysticks (controlling linear and angular velocities).
     */
    public static Command joystickDrive(
            Drive drive,
            DoubleSupplier xSupplier,
            DoubleSupplier ySupplier,
            DoubleSupplier omegaSupplier) {
        return Commands.run(
                () -> {
                    // Apply deadband
                    double linearMagnitude = MathUtil.applyDeadband(
                            Math.hypot(xSupplier.getAsDouble(), ySupplier.getAsDouble()), DEADBAND);
                    Rotation2d linearDirection =
                            new Rotation2d(-xSupplier.getAsDouble(), ySupplier.getAsDouble());
                    double omega = MathUtil.applyDeadband(omegaSupplier.getAsDouble(), DEADBAND);

                    // Square values
                    linearMagnitude = linearMagnitude * linearMagnitude;
                    omega = Math.copySign(omega * omega, omega);

                    // Log inputs after deadband and square
                    xLog.setDouble(linearDirection.getCos() * linearMagnitude);
                    yLog.setDouble(linearDirection.getSin() * linearMagnitude);
                    zLog.setDouble(omega);

                    // Calculate new linear velocity
                    Translation2d linearVelocity =
                            new Pose2d(new Translation2d(), linearDirection)
                                    .transformBy(new Transform2d(linearMagnitude, 0.0, new Rotation2d()))
                                    .getTranslation();

                    // Convert to field relative speeds & send command
                    drive.runVelocity(
                            ChassisSpeeds.fromFieldRelativeSpeeds(
                                    linearVelocity.getX() * drive.getMaxLinearSpeedMetersPerSec(),
                                    linearVelocity.getY() * drive.getMaxLinearSpeedMetersPerSec(),
                                    omega * drive.getMaxAngularSpeedRadPerSec(),
                                    drive.getRotation()
                            )
                    );
                },
                drive);
    }
}