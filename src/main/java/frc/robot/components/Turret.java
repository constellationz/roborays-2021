package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.functions.util;
import frc.TurretConfig;
import java.lang.Math;
import frc.CAN;
import frc.DIO;

public class Turret {
  private TalonSRX TurretMotor;
  private TalonSRX FlywheelMotor;
  private DigitalInput LeftSwitch;
  private DigitalInput RightSwitch;
  
  public Turret() {
    // declare motors
    TurretMotor = new TalonSRX(CAN.TURRET);
    FlywheelMotor = new TalonSRX(CAN.FLYWHEEL);   

    // declare limit switches
    LeftSwitch = new DigitalInput(DIO.TURRET_LIMIT_SWITCH_LEFT);
    RightSwitch = new DigitalInput(DIO.TURRET_LIMIT_SWITCH_RIGHT);

    TalonSRXConfiguration TurretConfiguration = 
      new TalonSRXConfiguration();
    
    // set up configuration object
    TurretConfiguration.peakCurrentLimit = TurretConfig.PEAK_CURRENT_LIMIT;
    TurretConfiguration.peakCurrentDuration = TurretConfig.PEAK_CURRENT_DURATION;
    TurretConfiguration.continuousCurrentLimit = TurretConfig.CONTINUOUS_CURRENT_LIMIT;
    
    // configure peak currents
    TurretMotor.configAllSettings(TurretConfiguration);
    FlywheelMotor.configAllSettings(TurretConfiguration);
  }

  // positive should be right
  public void velocityDriveTurret(double rotVelocity) {
    TurretMotor.set(TalonSRXControlMode.Velocity, rotVelocity);
  }

  // positive should be right
  public void directDriveTurret(double value) {
    TurretMotor.set(TalonSRXControlMode.PercentOutput, value);
  }

  // rotates the turret, positive values should aim right
  public void rotate(double value) {
    // clamp drive value for safety
    value = util.clamp(value, -1.0, 1.0);

    // get pressed status
    boolean isLeftSwitchPressed = LeftSwitch.get();
    boolean isRightSwitchPressed = RightSwitch.get();

    if (isLeftSwitchPressed == true) {
      // left switch pressed, clamp drive to right only
      value = util.clamp(value, 0.0, 1.0);
    } else if (isRightSwitchPressed == true) {
      // right switch pressed, clamp drive to left only
      value = util.clamp(value, -1.0, 0);
    }

    directDriveTurret(value);
  }

  public void spoolFlywheel() {
    // FlywheelMotor.set(TalonSRXControlMode.Velocity, TurretConfig.FLYWHEEL_VELOCITY);
    FlywheelMotor.set(TalonSRXControlMode.PercentOutput, TurretConfig.FLYWHEEL_POWER);
  }

  public void stopFlywheel() {
    FlywheelMotor.set(TalonSRXControlMode.PercentOutput, 0.0);
  }
  
}
