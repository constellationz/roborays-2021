package frc.functions;

public class util {
    
  // constrain value between minimum and maximum
  public static double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

}
