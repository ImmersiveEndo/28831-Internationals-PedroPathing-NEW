//package pedroPathing.SUBSYSTEMS;;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import pedroPathing.SUBSYSTEMS.CascadeSlides;
//
//@TeleOp(name = "MotorTest", group = "Linear OpMode")
//public class TestingCodeforanythingreally extends LinearOpMode {
//    private CascadeSlides motor;
//
//    @Override
//    public void runOpMode() {
//        motor = new CascadeSlides();
//        motor.init(hardwareMap);
//
//        waitForStart();
//
//        while(opModeIsActive()) {
//            motor.moveSlides(gamepad1.left_bumper, gamepad1.right_bumper, 0.2);
//        }
//    }
//}
