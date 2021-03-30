package frc;

public class IntakeConfig {
  // motor power for intaking and ejecting balls
  public static double INTAKE_POWER = 0.5;
  public static double EJECT_POWER = 0.35;

  // conveyor motor powers
  public static double CONVEYOR_INTAKE_POWER = 0.5;
  public static double CONVEYOR_EJECT_POWER = 0.5;

  // channel of pneumatic control module
  public static int PCM_CHANNEL = 0;

  // channel of solenoid controller on PCM
  // TODO: ensure directions are correct
  public static int EXTENDO_FORWARD_CHANNEL = 1;
  public static int EXTENDO_REVERSE_CHANNEL = 2;

  // channel of compressor
  // TODO: see documentation for compressor construction on CAN and PWM
  public static int COMPRESSOR_CHANNEL = 0;

  // motor power limits
  public static int CONTINUOUS_CURRENT_LIMIT = 50;
  public static int PEAK_CURRENT_DURATION = 0;
  public static int PEAK_CURRENT_LIMIT = 0;
}
