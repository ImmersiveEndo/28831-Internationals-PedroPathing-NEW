package pedroPathing.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import pedroPathing.SUBSYSTEMS.Vision;

@TeleOp(name = "Test Vision Limelight (No LED)")
public class visiontest extends LinearOpMode {

    private Vision vision;

    @Override
    public void runOpMode() {
        // Initialize vision system (LED is unused)
        vision = new Vision(hardwareMap, telemetry);

        vision.initializeCamera();  // Start the Limelight camera

        telemetry.addLine("Vision initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Manually update vision system
            vision.periodic();


            telemetry.update();
        }
    }
}
