package frc.robot.subsystems.pickup;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class PivotSubsystem extends SubsystemBase {
    public enum PivotPosition {
        in, out;
    }

    private final MotorController pivotMotor;
    private final Encoder pivotEncoder;
    private final DigitalInput limitSwitch;

    private PivotPosition pivotPosition = PivotPosition.in;

    public PivotSubsystem() {
        this.pivotMotor = new Victor(Constants.NotePickup.pivotMotor);
        this.pivotEncoder = new Encoder(Constants.NotePickup.pivotEncoder1, Constants.NotePickup.pivotEncoder2);
        this.limitSwitch = new DigitalInput(Constants.NotePickup.lmiitSwitch);
    }

    public void setPivotPosition(PivotPosition pivotPosition) {
        this.pivotPosition = pivotPosition;
    }

    @Override
    public void periodic() {
        if (limitSwitch.get()) {
            pivotEncoder.reset();
        }

        var position = pivotEncoder.get();
        if (pivotPosition == PivotPosition.out && position < Constants.NotePickup.outEncoderPosition) {
            pivotMotor.set(Constants.NotePickup.pivotSpeed);
        } else if (pivotPosition == pivotPosition.in && position > Constants.NotePickup.inEncoderPosition) {
            pivotMotor.set(-Constants.NotePickup.pivotSpeed);
        } else {
            pivotMotor.set(0);
        }
    }
}


