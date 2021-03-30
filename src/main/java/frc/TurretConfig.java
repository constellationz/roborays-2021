package frc;

public class TurretConfig {
  // joysticks (control)
  public static int XBOX_PORT = 0;
  
  // motor power limits
  public static int CONTINUOUS_CURRENT_LIMIT = 50;
  public static int PEAK_CURRENT_DURATION = 0;
  public static int PEAK_CURRENT_LIMIT = 0;
  
  // rotations of turret motor per turret rotation
  // TODO: calculate this
  public static double TURRET_ROTATION_RATIO = 1;
  
  // TODO: find good free spinning velocity
  // TODO: determine whether to use power or velocity
  // TODO: tune velocity
  public static double FLYWHEEL_POWER = 0.85;
  public static double FLYWHEEL_VELOCITY = 100;
}
