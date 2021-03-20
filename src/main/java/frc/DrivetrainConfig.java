package frc;

public class DrivetrainConfig {
    // values in m/s
    public static double FORWARD_SPEED = 1;
    public static double TURN_SPEED = 1;
    public static double MAX_SPEED = 1;

    // joysticks (control)
    public static int XBOX_PORT = 0;

    // for curvaturedrive2
    public static double HIGH_CRITICAL = 1;
    public static double DELTA_CRITICAL = 1;

    // motor power limit
    public static int CONTINUOUS_CURRENT_LIMIT = 50;
    public static int PEAK_CURRENT_DURATION = 0;
    public static int PEAK_CURRENT_LIMIT = 0;

    // circumference in meters
    public static double WHEEL_CIRCUMFERENCE = 3.141 * 0.2;

    // motor revolutions per wheel revolution
    public static double GEAR_RATIO = 64.0;

    // ratio of 1 meter/s -> motor revolutions/s
    public static double GROUND_RATIO = 1/WHEEL_CIRCUMFERENCE * GEAR_RATIO;
}
