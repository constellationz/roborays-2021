package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.functions.DrivetrainSpeed;
import frc.DrivetrainConfig;
import java.lang.Math;
import frc.CAN;

public class Drivetrain {
  private TalonSRX FrontLeftMotor;
  private TalonSRX FrontRightMotor;
  private TalonSRX BackLeftMotor;
  private TalonSRX BackRightMotor;
  // used for getSelectedSensorVelocity functions
  private final WPI_TalonFX FalconLeft;
  private final WPI_TalonFX FalconRight;

  public Drivetrain() {
    FrontLeftMotor = new TalonSRX(CAN.FRONT_LEFT);
    FrontRightMotor = new TalonSRX(CAN.FRONT_RIGHT);   
    BackLeftMotor = new TalonSRX(CAN.BACK_LEFT);   
    BackRightMotor = new TalonSRX(CAN.BACK_RIGHT);   

    // TODO: possibly use all four encoders to reduce noise
    // TODO: set FeedbackDevice in CTRE Lifeboat before using
    FalconLeft = new WPI_TalonFX(CAN.FRONT_LEFT);
    FalconRight= new WPI_TalonFX(CAN.FRONT_RIGHT);

    // motor configuration
    TalonSRXConfiguration MotorConfiguration = 
      new TalonSRXConfiguration();

    MotorConfiguration.peakCurrentLimit = DrivetrainConfig.PEAK_CURRENT_LIMIT;
    MotorConfiguration.peakCurrentDuration = DrivetrainConfig.PEAK_CURRENT_DURATION;
    MotorConfiguration.continuousCurrentLimit = DrivetrainConfig.CONTINUOUS_CURRENT_LIMIT;

    FrontLeftMotor.configAllSettings(MotorConfiguration);
    FrontRightMotor.configAllSettings(MotorConfiguration);
    BackLeftMotor.configAllSettings(MotorConfiguration);
    BackRightMotor.configAllSettings(MotorConfiguration);
  }

  public void reset() {
    DrivetrainSpeed noSpeed = new DrivetrainSpeed(0.0, 0.0);
    directDrive(noSpeed);
  }

  // constrain value between minimum and maximum
  private double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

  public void directDriveLeft(double value) {
    FrontLeftMotor.set(TalonSRXControlMode.PercentOutput, -value);
    BackLeftMotor.set(TalonSRXControlMode.PercentOutput, -value);
  }

  public void directDriveRight(double value) {
    FrontRightMotor.set(TalonSRXControlMode.PercentOutput, value);
    BackRightMotor.set(TalonSRXControlMode.PercentOutput, value);
  }

  public void velocityDriveLeft(double value) {
    FrontLeftMotor.set(TalonSRXControlMode.Velocity, -value);
    BackLeftMotor.set(TalonSRXControlMode.Velocity, -value);
  }

  public void velocityDriveRight(double value) {
    FrontRightMotor.set(TalonSRXControlMode.Velocity, value);
    BackRightMotor.set(TalonSRXControlMode.Velocity, value);
  }

  // meters per second
  public void unitDriveLeft(double unitValue) {
    velocityDriveLeft(unitValue * DrivetrainConfig.GROUND_RATIO);
  }

  public void unitDriveRight(double unitValue) {
    velocityDriveRight(unitValue * DrivetrainConfig.GROUND_RATIO);
  }

  public void directDrive(DrivetrainSpeed driveSpeed) {
    // drives velocity
    directDriveLeft(driveSpeed.left);
    directDriveRight(driveSpeed.right);
  }

  public void velocityDrive(DrivetrainSpeed driveSpeed) {
    // drives velocity
    velocityDriveLeft(driveSpeed.left);
    velocityDriveRight(driveSpeed.right);
  }

  public void CurvatureDrive2(double forward, double turn) {
    DrivetrainSpeed DriveSpeed = ComputeCurvatureDrive2(forward, turn);
    velocityDrive(DriveSpeed);
  }

  public void CurvatureDrive(double forward, double turn) {
    DrivetrainSpeed DriveSpeed = ComputeCurvatureDrive(forward, turn);
    velocityDrive(DriveSpeed);
  }

  // gets forward speed in m/s
  public double getForwardSpeed() {
    double leftSpeed = -FalconLeft.getSelectedSensorVelocity();
    double rightSpeed = FalconRight.getSelectedSensorVelocity();
    // motor rpm divided by ground ratio
    return (leftSpeed + rightSpeed) * 0.5 / DrivetrainConfig.GROUND_RATIO;
  }

  private DrivetrainSpeed ComputeCurvatureDrive2(double forward, double turn) {
    // clamp for safety
    forward = clamp(forward, -1.0, 1.0);
    turn = clamp(turn, -1.0, 1.0);

    double forwardSpeed = getForwardSpeed();
    double lowSpeedCharacter = clamp(
      (DrivetrainConfig.HIGH_CRITICAL - forwardSpeed)/DrivetrainConfig.DELTA_CRITICAL,
      0.0, 1.0);
    double highSpeedCharacter = 1 - lowSpeedCharacter;

    DrivetrainSpeed skidSpeed = 
      ComputeBooleanConstrainedSkidSteer(forward, turn);
    DrivetrainSpeed curvatureSpeed = 
      ComputeCurvatureDrive(forward, turn);

    return skidSpeed.scale(lowSpeedCharacter).add(curvatureSpeed.scale(highSpeedCharacter));
  }

  private DrivetrainSpeed ComputeBooleanConstrainedSkidSteer(double forward, double turn) {
    // clamp for safety
    forward = clamp(forward, -1.0, 1.0);
    turn = clamp(turn, -1.0, 1.0);

    double forwardPower = forward * DrivetrainConfig.FORWARD_SPEED;
    double turnPower = turn * DrivetrainConfig.TURN_SPEED;

    double leftVelocity = clamp(forwardPower + turnPower, -DrivetrainConfig.MAX_SPEED, DrivetrainConfig.MAX_SPEED);
    double rightVelocity = clamp(forwardPower - turnPower, -DrivetrainConfig.MAX_SPEED, DrivetrainConfig.MAX_SPEED);

    return new DrivetrainSpeed(leftVelocity, rightVelocity);
  }

  public DrivetrainSpeed ComputeCurvatureDrive(double forward, double turn) {
    // clamp for safety
    forward = clamp(forward, -1.0, 1.0);
    turn = clamp(turn, -1.0, 1.0);

    double turnScaled = turn * Math.abs(forward);

    double forwardPower = forward * DrivetrainConfig.FORWARD_SPEED;
    double turnPower = turnScaled * DrivetrainConfig.TURN_SPEED;

    double leftVelocity = clamp(forwardPower + turnPower, -DrivetrainConfig.MAX_SPEED, DrivetrainConfig.MAX_SPEED);
    double rightVelocity = clamp(forwardPower - turnPower, -DrivetrainConfig.MAX_SPEED, DrivetrainConfig.MAX_SPEED);

    return new DrivetrainSpeed(leftVelocity, rightVelocity);
  }
}
