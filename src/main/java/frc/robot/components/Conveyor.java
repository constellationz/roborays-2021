package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.IntakeConfig;
import frc.CAN;

public class Conveyor {
  private TalonSRX ConveyorMotor;
  
  public Conveyor() {
    ConveyorMotor = new TalonSRX(CAN.CONVEYOR);

    TalonSRXConfiguration MotorConfig = 
      new TalonSRXConfiguration();
    
    // set up intake power limits
    MotorConfig.peakCurrentLimit = IntakeConfig.PEAK_CURRENT_LIMIT;
    MotorConfig.peakCurrentDuration = IntakeConfig.PEAK_CURRENT_DURATION;
    MotorConfig.continuousCurrentLimit = IntakeConfig.CONTINUOUS_CURRENT_LIMIT;
    
    // configure peak currents
    ConveyorMotor.configAllSettings(MotorConfig);
  }

  // drives conveyor
  // TODO: positive value = intake, negative value = eject
  public void driveConveyorMotor(double value) {
    ConveyorMotor.set(TalonSRXControlMode.PercentOutput, value);
  }

  // intakes items
  public void intake() {
    driveConveyorMotor(IntakeConfig.INTAKE_POWER);
  }

  // stops intake
  public void stopIntake() {
    driveConveyorMotor(0.0);
  }

  // ejects items in intake
  public void eject() {
    driveConveyorMotor(-IntakeConfig.EJECT_POWER);
  }

}