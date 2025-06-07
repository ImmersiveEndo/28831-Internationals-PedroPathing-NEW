package pedroPathing.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import pedroPathing.SUBSYSTEMS.CascadePivot;
import pedroPathing.SUBSYSTEMS.Claw_OLD;
import pedroPathing.SUBSYSTEMS.Drivetrain;
import pedroPathing.SUBSYSTEMS.CascadeSlides_OLD;

@TeleOp(name = "Orbit TeleOp", group = "Linear OpMode")
public class BasicTeleOp extends LinearOpMode {
    private Drivetrain drivetrain;
    private CascadeSlides_OLD cascadeSlides;
    private CascadePivot slidePivot;
    private Claw_OLD claw;

    private static final int POSITION0 = 0;
    private static final int POSITION1 = 1865; // Adjusted for safer movement
    private static final int POSITION2 = 3380; // Adjusted for safer movement
    private static final int position0 = 39;
    private static final int position11 = 250; // Adjusted for safer movement
    private static final int position22 = 650; // Adjusted for safer movement

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain();
        cascadeSlides = new CascadeSlides_OLD();
        slidePivot = new CascadePivot();
        claw = new Claw_OLD();

        drivetrain.init(hardwareMap);
        cascadeSlides.init(hardwareMap);

        slidePivot.init(hardwareMap);
        claw.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Slides pos", cascadeSlides.getCurrentPosition());
        telemetry.addData("Pivot pos", slidePivot.getCurrentPosition());
        telemetry.update();

        slidePivot.moveToPosition(0, 0.95);

        waitForStart();

        while (opModeIsActive()) {
            drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad2.a) {
                cascadeSlides.moveToPosition(POSITION1, 0.95);
            } else if (gamepad2.b) {
                cascadeSlides.moveToPosition(POSITION2, 0.95);
            } else if (gamepad2.x) {
                cascadeSlides.moveToPosition(POSITION0, 1);
            } else if (gamepad2.left_stick_y > 0.15 || gamepad2.left_stick_y < 0.15) {
                 // Manual control using the left stick
                double liftPower = -gamepad2.left_stick_y; // Invert if necessary
                cascadeSlides.move(liftPower);
            } else {
                cascadeSlides.stop();
            }

            if (gamepad2.dpad_left) {
                slidePivot.moveToPosition(position11, 0.95);
            } else if (gamepad2.dpad_right) {
                slidePivot.moveToPosition(position22, 0.95);
            } else if (gamepad2.dpad_down) {
                slidePivot.moveToPosition(position0, 0.95);
            }
            else if (gamepad2.left_stick_x > 0.15 || gamepad2.left_stick_x < 0.15) {
                // Manual control using the left stick
                double liftPower = -gamepad2.left_stick_x; // Invert if necessary
                slidePivot.move(liftPower);
        }

            else {
                slidePivot.stop();
            }

            claw.controlClaw(gamepad2.left_trigger, gamepad2.right_trigger);

            claw.controlRotation(gamepad2.right_stick_x, gamepad2.right_stick_x);

            claw.controlArm(gamepad2.left_bumper, gamepad2.right_bumper);

            // Control the slides with Y for extend, A for retract
            cascadeSlides.moveSlides(gamepad1.y, gamepad1.a);

            // Control the pivot with Left and Right bumpers
            slidePivot.movePivot(gamepad1.left_bumper, gamepad1.right_bumper);

//            telemetry.addData("Pivot Power", slidePivot.getMotorPower()); // Show the motor power
            telemetry.addData("Current Position", cascadeSlides.getCurrentPosition());
            telemetry.addData("Current Pivot Position", slidePivot.getCurrentPosition());
            telemetry.addData("Current Claw Position", claw.getClawPosition());

            telemetry.update();
        }
    }
}