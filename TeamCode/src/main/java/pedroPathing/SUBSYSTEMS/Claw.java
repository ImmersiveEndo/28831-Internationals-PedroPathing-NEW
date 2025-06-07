package pedroPathing.SUBSYSTEMS;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    private Servo clawServo;
    private Limelight3A camera;

    private boolean isClawOpen = false;
    private double clawOpenPosition = 0.8;
    private double clawClosedPosition = 0.2;

    private ElapsedTime timer = new ElapsedTime();

    public void init(HardwareMap hardwareMap, Limelight3A camera) {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        this.camera = camera;

        // Start claw closed
        clawServo.setPosition(clawClosedPosition);
        isClawOpen = false;
        timer.reset();
    }

    // Control claw open/close based on buttons (example)
    public void controlClaw(boolean openButton, boolean closeButton) {
        if (openButton) {
            clawServo.setPosition(clawOpenPosition);
            isClawOpen = true;
            timer.reset();
        } else if (closeButton) {
            clawServo.setPosition(clawClosedPosition);
            isClawOpen = false;
            timer.reset();
        }
    }

    // Example of manual claw angle turn control (if you want)
    public void turnClaw(double leftTrigger, double rightTrigger) {
        // Increment or decrement servo position slightly based on triggers
        double pos = clawServo.getPosition();
        double change = (rightTrigger - leftTrigger) * 0.01; // small step per loop
        pos += change;
        pos = Math.min(1.0, Math.max(0.0, pos));
        clawServo.setPosition(pos);
    }

    // Example method for setting claw angle explicitly
    public void angleClaw(boolean increase, boolean decrease) {
        double pos = clawServo.getPosition();
        if (increase) pos += 0.01;
        if (decrease) pos -= 0.01;
        pos = Math.min(1.0, Math.max(0.0, pos));
        clawServo.setPosition(pos);
    }

    // Your automatic claw alignment using Limelight angle
    public void autoAlignClaw(Telemetry telemetry) {
        if (camera == null) {
            telemetry.addData("Claw", "No camera assigned");
            return;
        }

        LLResult result = camera.getLatestResult();
        if (result == null) {
            telemetry.addData("Claw", "No Limelight result");
            return;
        }

        double[] outputs = result.getPythonOutput();
        if (outputs != null && outputs.length >= 4) {
            double angleDegrees = outputs[3];

            // Map 0-360 degrees to servo position 0.0-1.0
            double servoPos = angleDegrees / 360.0;
            servoPos = Math.min(1.0, Math.max(0.0, servoPos));

            clawServo.setPosition(servoPos);

            telemetry.addData("Claw Servo Angle", angleDegrees);
            telemetry.addData("Claw Servo Position", servoPos);
            telemetry.addData("Detected Angle",angleDegrees );
        } else {
            telemetry.addData("Claw", "No valid angle data");
        }
    }
}
