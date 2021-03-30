package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.IntakeConfig;
import frc.CAN;

// TODO: set up pneumatics (if needed)
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake {
  private TalonSRX IntakeMotor;
  // double solenoid, probably using this
  private DoubleSolenoid ExtendoSolenoid;
  // if the robot has a compressor
  private Compressor m_Compressor;
  
  public Intake() {
    IntakeMotor = new TalonSRX(CAN.INTAKE);
    ExtendoSolenoid = new DoubleSolenoid(
      IntakeConfig.EXTENDO_FORWARD_CHANNEL, IntakeConfig.EXTENDO_REVERSE_CHANNEL);
    // TODO: remove if no compressor
    m_Compressor = new Compressor(IntakeConfig.COMPRESSOR_CHANNEL);

    TalonSRXConfiguration IntakeMotorConfig = 
      new TalonSRXConfiguration();
    
    // set up intake power limits
    IntakeMotorConfig.peakCurrentLimit = IntakeConfig.PEAK_CURRENT_LIMIT;
    IntakeMotorConfig.peakCurrentDuration = IntakeConfig.PEAK_CURRENT_DURATION;
    IntakeMotorConfig.continuousCurrentLimit = IntakeConfig.CONTINUOUS_CURRENT_LIMIT;
    
    // configure peak currents
    IntakeMotor.configAllSettings(IntakeMotorConfig);
  }

  public void setCompressorEnabled(boolean isEnabled) {
    m_Compressor.setClosedLoopControl(isEnabled);
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
    ExtendoSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  // retracts pneumatics
  public void retract() {
    ExtendoSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void disableExtendo() {
    ExtendoSolenoid.set(DoubleSolenoid.Value.kOff);
  }

  // TODO: manage pneumatic system
  public void managePneumatics() {
    // this might not be needed
  }

}