package frc.robot.commands;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.pickup.PivotSubsystem;

public class PivotCommand extends Command {
    private final PivotSubsystem pivotSubsystem;
    private boolean setOut;


    public PivotCommand(PivotSubsystem pivotSubsystem, boolean setOut) {
        this.pivotSubsystem = pivotSubsystem;
        this.setOut = setOut;
    }

    @Override
    public void initialize() {
        pivotSubsystem.setPivotPosition(PivotSubsystem.PivotPosition.in);
    }
    @Override
    public void execute() {
       if(setOut){
            pivotSubsystem.setPivotPosition(PivotSubsystem.PivotPosition.out);
        } else{
            pivotSubsystem.setPivotPosition(PivotSubsystem.PivotPosition.in);
        }
    }

    @Override
    public void end(boolean interrupted) {
        pivotSubsystem.setPivotPosition(PivotSubsystem.PivotPosition.in);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
