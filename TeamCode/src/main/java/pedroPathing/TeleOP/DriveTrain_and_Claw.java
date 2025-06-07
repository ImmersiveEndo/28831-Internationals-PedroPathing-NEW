//package pedroPathing.TeleOP;
//
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//import pedroPathing.SUBSYSTEMS.Drivetrain;
//import pedroPathing.SUBSYSTEMS.Claw;
//import pedroPathing.SUBSYSTEMS.Vision;
//
//@TeleOp(name = "DriveTrain_Claw", group = "Linear OpMode")
//public class DriveTrain_and_Claw extends LinearOpMode {
//
//    private Drivetrain drivetrain;
//    private Claw claw;
//
//    private DcMotorEx leftEncoder;    // First odometry encoder on motor port 0
//    private DcMotorEx rightEncoder;   // Second odometry encoder on motor port 1
//
//
//    @Override
//    public void runOpMode() {
//        drivetrain = new Drivetrain();
//        drivetrain.init(hardwareMap);
//
//        claw = new Claw();
//        claw.init(hardwareMap, );
//
//        leftEncoder = hardwareMap.get(DcMotorEx.class, "leftEncoder");
//        leftEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        leftEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//
//        rightEncoder = hardwareMap.get(DcMotorEx.class, "rightEncoder");
//        rightEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        rightEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//
//        leftEncoder.setDirection(DcMotorEx.Direction.REVERSE);   // Adjust if needed
//        rightEncoder.setDirection(DcMotorEx.Direction.FORWARD);  // Adjust if needed
//
//        telemetry.addData("Status", "OpMode Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
//
//            claw.controlClaw(gamepad1.a, gamepad1.y);
//            claw.turnClaw(gamepad1.left_trigger, gamepad1.right_trigger);
//            claw.angleClaw(gamepad1.b, gamepad1.x);
//            claw.autoAlignClaw(telemetry);
//            telemetry.update();
//            if(gamepad1.left_bumper = true){
//                //we will do the auto align button nsuff here
//
//            }
//
////            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
////            telemetry.addData("Left Stick X", gamepad1.left_stick_x);
////
////            telemetry.addData("Left Encoder Ticks", leftEncoder.getCurrentPosition());
////            telemetry.addData("Right Encoder Ticks", rightEncoder.getCurrentPosition());
//
//            telemetry.update();
//        }
//    }
//}