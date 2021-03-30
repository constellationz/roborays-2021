// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.Drivetrain;
import frc.robot.components.Conveyor;
import frc.robot.components.Turret;
import frc.robot.components.Intake;
import frc.functions.DrivetrainSpeed;
import frc.DrivetrainConfig;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // robot components
  private Drivetrain m_Drivetrain = new Drivetrain();
  private Conveyor m_Conveyor = new Conveyor();
  private Intake m_Intake = new Intake();
  private Turret m_Turret = new Turret();
  private XboxController m_xboxController = 
    new XboxController(DrivetrainConfig.XBOX_PORT);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    m_Drivetrain.reset();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // double turn = m_xboxController.getX(GenericHID.Hand.kLeft);
    double forward = m_xboxController.getY(GenericHID.Hand.kRight);

    // testing code
    // TODO: tune sensor control loops
    m_Drivetrain.directDriveLeft(forward);
    m_Drivetrain.directDriveRight(forward);

    // TODO: test curvaturedrive
    // m_DriveTrain.CurvatureDrive(forward, turn);

    // TODO: add CurvatureDrive2, ONLY USE WHEN READY!
    // m_Drivetrain.CurvatureDrive2(forward, turn);

    // TODO: add turret subsystem

    // TODO: add conveyor subsystem

    // TODO: add intake subsystem

    // TODO: add pneumatic management
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    m_Drivetrain.reset();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
