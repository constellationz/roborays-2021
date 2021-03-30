package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.IntakeConfig;
import frc.CAN;

public class Intake {
  private TalonSRX IntakeMotor;
  
  public Intake() {
    IntakeMotor = new TalonSRX(CAN.INTAKE);

    TalonSRXConfiguration IntakeMotorConfig = 
      new TalonSRXConfiguration();
    
    // set up intake power limits
    IntakeMotorConfig.peakCurrentLimit = IntakeConfig.PEAK_CURRENT_LIMIT;
    IntakeMotorConfig.peakCurrentDuration = IntakeConfig.PEAK_CURRENT_DURATION;
    IntakeMotorConfig.continuousCurrentLimit = IntakeConfig.CONTINUOUS_CURRENT_LIMIT;
    
    // configure peak currents
    IntakeMotor.configAllSettings(IntakeMotorConfig);
  }

  // drives intake
  // TODO: positive value = intake, negative value = eject
  public void driveIntakeMotor(double value) {
    IntakeMotor.set(TalonSRXControlMode.PercentOutput, value);
  }

  // intakes items
  public void intake() {
    driveIntakeMotor(IntakeConfig.INTAKE_POWER);
  }

  // stops intake
  public void stopIntake() {
    driveIntakeMotor(0.0);
  }

  // ejects items in intake
  public void eject() {
    driveIntakeMotor(-IntakeConfig.EJECT_POWER);
  }

  // extends pneumatics
  public void extend() {
    // TODO: set solenoid output
  }

  // retracts pneumatics
  public void retract() {
    // TODO: set solenoid output
  }

  // TODO: manage pneumatic system
  public void managePneumatics() {

  }

}