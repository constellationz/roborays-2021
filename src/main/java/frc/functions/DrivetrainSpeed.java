package frc.functions;

public class DrivetrainSpeed {
    public double left;
    public double right;

    public DrivetrainSpeed(double leftSpeed, double rightSpeed) {
        left = leftSpeed;
        right = rightSpeed;
    }

    public DrivetrainSpeed multiply(DrivetrainSpeed otherDrivetrain) {
        left *= otherDrivetrain.left;
        right *= otherDrivetrain.right;
        return this;
    }

    public DrivetrainSpeed add(DrivetrainSpeed otherDrivetrain) {
        left += otherDrivetrain.left;
        right += otherDrivetrain.right;
        return this;
    }

    public DrivetrainSpeed scale(double value) {
        left *= value;
        right *= value;
        return this;
    }

    public void reset() {
        left = 0.0;
        right = 0.0;
    }
}
