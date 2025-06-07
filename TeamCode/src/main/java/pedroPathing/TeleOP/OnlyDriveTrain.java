package pedroPathing.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import pedroPathing.SUBSYSTEMS.Drivetrain;

@TeleOp(name = "OnlyDriveTrain", group = "Linear OpMode")
public class OnlyDriveTrain extends LinearOpMode {

    private Drivetrain drivetrain;
    private DcMotorEx leftEncoder;  // First odometry encoder on motor port 0
    private DcMotorEx rightEncoder; // Second odometry encoder on motor port 1

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain();
        drivetrain.init(hardwareMap);
        leftEncoder = hardwareMap.get(DcMotorEx.class, "leftEncoder");
        leftEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        rightEncoder = hardwareMap.get(DcMotorEx.class, "rightEncoder");
        rightEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftEncoder.setDirection(DcMotorEx.Direction.REVERSE);   // Adjust if needed
        rightEncoder.setDirection(DcMotorEx.Direction.FORWARD);  // Adjust if needed

        telemetry.addData("Status", "OpMode Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            // Display gamepad inputs
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Left Stick X", gamepad1.left_stick_x);

            // Display the encoder positions for both odometry wheels
            telemetry.addData("Left Encoder Ticks", leftEncoder.getCurrentPosition());
            telemetry.addData("Right Encoder Ticks", rightEncoder.getCurrentPosition());

            telemetry.update();
        }
    }
}