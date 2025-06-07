//package pedroPathing.Autonomous;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.PoseVelocity2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import pedroPathing.MecanumDrive;
//
//@Autonomous(name = "testautonomous", group = "Autonomous")
//public class Auto123 extends LinearOpMode {
//
//    @Override
//    public void runOpMode() {
//        Pose2d startPose = new Pose2d(0, 0, 0);
//        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        drive.setDrivePowers(new PoseVelocity2d(0.5, 0, 0));
//        sleep(2000);
//        drive.setDrivePowers(new PoseVelocity2d(0, 0, 0));
//    }
//}