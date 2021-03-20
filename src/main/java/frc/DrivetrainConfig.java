package frc;

public class DrivetrainConfig {
    // values in m/s
    public static double FORWARD_SPEED = 3.0;
    public static double MAX_SPEED = 3.0;
    public static double TURN_SPEED = 1.5;

    // joysticks (control)
    public static int XBOX_PORT = 0;

    /*
    * speeds at which curvaturedrive2 shifts
    * from skid steer to curvaturedrive
    * LOW_CRITICAL is the lower velocity point 
    * for linear transition while HIGH_CRITICAL
    * is the upper bound for the transition.
    */
    public static double HIGH_CRITICAL = 0.1;
    public static double LOW_CRITICAL = 0.2;
    public static double DELTA_CRITICAL = HIGH_CRITICAL - LOW_CRITICAL;

    // motor power limit
    public static int CONTINUOUS_CURRENT_LIMIT = 50;
    public static int PEAK_CURRENT_DURATION = 0;
    public static int PEAK_CURRENT_LIMIT = 0;

    // circumference in meters
    public static double WHEEL_CIRCUMFERENCE = 3.141 * 0.2;

    // motor revolutions per wheel revolution
    public static double GEAR_RATIO = 64.0;

    // ratio of 1 meter -> 1 motor revolution
    public static double GROUND_RATIO = 1/WHEEL_CIRCUMFERENCE * GEAR_RATIO;
}
