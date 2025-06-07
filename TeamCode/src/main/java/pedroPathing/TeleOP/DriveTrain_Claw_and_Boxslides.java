package pedroPathing.TeleOP;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import pedroPathing.SUBSYSTEMS.Claw;
import pedroPathing.SUBSYSTEMS.Drivetrain;
import pedroPathing.SUBSYSTEMS.CascadeSlides;

@TeleOp(name = "DriveTrain_Claw_BoxTube", group = "Linear OpMode")
public class DriveTrain_Claw_and_Boxslides extends LinearOpMode {
    private Drivetrain drivetrain;
    private Claw claw;
    private CascadeSlides cascadeSlides;
    private Limelight3A camera;

    private DcMotorEx leftEncoder;
    private DcMotorEx rightEncoder;

    @Override
    public void runOpMode() {
        // Initialize Limelight3A camera directly
        camera = hardwareMap.get(Limelight3A.class, "limelight");
        camera.pipelineSwitch(0);              // Ensure correct pipeline is selected
        camera.setPollRateHz(50);              // Optional: how often data updates
        camera.start();                        // Start the camera

        // Initialize subsystems
        claw = new Claw();
        claw.init(hardwareMap, camera);        // Pass camera to Claw directly

        cascadeSlides = new CascadeSlides();
        cascadeSlides.init(hardwareMap);

        telemetry.addData("Status", "OpMode Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            cascadeSlides.moveSlides(gamepad1.left_bumper, gamepad1.right_bumper, 0.2);
            claw.autoAlignClaw(telemetry);

            telemetry.update();
        }
    }
}
