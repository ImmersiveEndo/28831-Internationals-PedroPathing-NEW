package pedroPathing.SUBSYSTEMS;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Claw_OLD {
    private Servo clawServo = null;
    private Servo armServo = null;
    private Servo rotationServo = null;

    // PID VARIABLES
    private double integralSum = 0;
    private double lastError = 0;
    private final double Kp = 0.1; // Proportional gain
    private final double Ki = 0.01; // Integral gain
    private final double Kd = 0.05; // Derivative gain
    private final ElapsedTime timer = new ElapsedTime();

    // Initialize claw and arm servos
    public void init(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawDrive");
        armServo = hardwareMap.get(Servo.class, "armServo");
        rotationServo = hardwareMap.get(Servo.class, "rotationServo");
    }

    // Control claw opening and closing using sensitivity
    public void controlClaw(float open, float close) {
        if (open > 0.1) {
            clawServo.setPosition(0.5);  // Open the claw
        }
        else if (close > 0.1) {
            clawServo.setPosition(0.75);   // Close the claw
        }
    }

    public void useClaw(boolean open, boolean close) {
        if (open) {
            clawServo.setPosition(0.65);   // Open the claw
        }
        else if (close) {
            clawServo.setPosition(0.75);   // Close the claw
        }
    }


    public void controlRotation(double rotateRight, double rotateLeft) {
        if(rotateRight > 0.1) {
            rotationServo.setPosition(0.8);
        } else if (rotateLeft > 0.1) {
            rotationServo.setPosition(-0.8);
        } else {
            rotationServo.setPosition(0.5);
        }
    }

    // Control the arm using D-pad (Up = forward, Down = backward)
    public void controlArm(boolean up, boolean down) {
        if (up) {
            armServo.setPosition(0.65); // Move forward
        } else if (down) {
            armServo.setPosition(0.2); // Move backward
        } else {
            armServo.setPosition(0.5); // Move to neutral position
        }
    }

    public double PID(double reference, double state) {
        double error = reference - state;
        double dt = timer.seconds();  // Get elapsed time since last update

        integralSum += error * dt;
        double derivative = (error - lastError) / dt;
        lastError = error;
        timer.reset();

        return (Kp * error) + (Ki * integralSum) + (Kd * derivative);
    }

    public double getClawPosition() {
        try {
            return (clawServo.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}