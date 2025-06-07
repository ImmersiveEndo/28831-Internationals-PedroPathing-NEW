//package pedroPathing.Autonomous;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
////import pedroPathing.Intake;
////import pedroPathing.MecanumDrive;
////import pedroPathing.Outtake;
//
//@Autonomous(name="Autoexample",group="AUTO")
//public class AutoExample extends LinearOpMode {
//    TrajectoryActionBuilder gotoSpecimen;
//    TrajectoryActionBuilder goawayfromSpecimen;
//    TrajectoryActionBuilder Sample1;
//    TrajectoryActionBuilder Sample2;
//    TrajectoryActionBuilder Sample3;
//    TrajectoryActionBuilder Basket1;
//    TrajectoryActionBuilder Basket2;
//    TrajectoryActionBuilder Basket3;
//
//    TrajectoryActionBuilder Samplepickup;
//    private Intake intake;
//    //    private Outtake outtake;
//    public class intakeclawopen implements Action{
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//            intake.IntakeClaw(0.3);
//            return false;
//        }
//    }
//    public Action Intakeclawopen(){
//        return new intakeclawopen();
//    }
//    public class intakeclawclose implements Action{
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//            intake.IntakeClaw(-1);
//            return false;
//        }
//    }
//    public Action Intakeclawclose(){
//        return new intakeclawclose();
//    }
//    public class intakeclawpivotdown implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
//            intake.IntakeClawPivot(0.7);
//            return false;
//        }
//    }
//    public Action Intakeclawpivotdown(){
//        return new intakeclawpivotdown();
//    }
//    public class intakeclawpivotup implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
//            intake.IntakeClawPivot(0);
//            return false;
//        }
//    }
//    public Action Intakeclawpivotup(){
//        return new intakeclawpivotup();
//    }
//    public class cascadeout implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
//            intake.moveslidefront(0.5,20);
//            return false;
//        }
//    }
//    public Action Cascadeout(){
//        return new cascadeout();
//    }
//    public class cascadestop implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
//            intake.moveslidefront(0,20);
//            return false;
//        }
//    }
//    public Action Cascadstop(){
//        return new cascadestop();
//    }
//    public class cascadein implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
//            intake.moveslideback(0.5,20);
//            return false;
//        }
//    }
//    public Action Cascadein(){
//        return new cascadein();
//    }
//    public class armtospecimen implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.arm(50,0.7);
//            return false;
//        }
//    }
//    public Action Armtospecimen(){
//        return new armtospecimen();
//    }
//    public class attatchspecimen implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.clawrotation(0);
//            return false;
//        }
//    }
//    public Action Attatchspecimen(){
//        return new attatchspecimen();
//    }
//    public class unattatchspecimen implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.clawrotation(0.45);
//            return false;
//        }
//    }
//    public Action Unattatchspecimen(){
//        return new unattatchspecimen();
//    }
//    public class clipspecimen implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.outclaw(1);
//            return false;
//        }
//    }
//    public Action Clipspecimen(){
//        return new clipspecimen();
//    }
//    public class unclipspecimen implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.outclaw(0.7);
//            return false;
//        }
//    }
//    public Action Unclipspecimen(){
//        return new unclipspecimen();
//    }
//    public class armtotransfer implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.arm(50,0.7);
//            return false;
//        }
//    }
//    public Action Armtotransfer(){
//        return new armtotransfer();
//    }
//    public class armtobasket implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket){
////            outtake.arm(75,0.7);
//            return false;
//        }
//    }
//    public Action Armtobasket(){
//        return new armtobasket();
//    }
//
//    @Override
//    public void runOpMode() {
//
//        intake = new Intake(hardwareMap);
////        outtake = new Outtake(hardwareMap);
//
//        Pose2d beginPose = new Pose2d(-6.6,-65.3,Math.toRadians(90));
//        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
//
//        gotoSpecimen = drive.actionBuilder(new Pose2d(-6.6,-65.3,Math.toRadians(90)))
//                .lineToY(-35);
//
//        goawayfromSpecimen = drive.actionBuilder(new Pose2d(-6.6,-35,Math.toRadians(90)))
//                .waitSeconds(2)
//                .lineToY(-32);
//
//        Sample1 = drive.actionBuilder(new Pose2d(-6.6,-32,Math.toRadians(90)))
//                .strafeTo(new Vector2d(-22, -48))
//                .splineToConstantHeading(new Vector2d(-54,-30),Math.PI/2)
//                .waitSeconds(1);
//
//        Basket1 = drive.actionBuilder(new Pose2d(-54,-30,Math.toRadians(90)))
//                .strafeTo(new Vector2d(-59,-59))
//                .turn(-Math.PI/4);
//
//        Sample2 = drive.actionBuilder(new Pose2d(-59,-59,Math.toRadians(90)))
//                .strafeTo(new Vector2d(-64,-30))
//                .waitSeconds(1);
//
//        Basket2 = drive.actionBuilder(new Pose2d(-64,-30,Math.toRadians(90)))
//                .strafeTo(new Vector2d(-59,-59))
//                .turn(-Math.PI/4)
//                .waitSeconds(1);
//
//        Sample3 = drive.actionBuilder(new Pose2d(-59,-59,Math.toRadians(90)))
//                .splineToLinearHeading(new Pose2d(-68,-36,Math.toRadians(65)),Math.toRadians(0))
//                .waitSeconds(1);
//        Basket3 = drive.actionBuilder(new Pose2d(-68,-36,Math.toRadians(90)))
//                .strafeTo(new Vector2d(-59,-59))
//                .turn(-Math.PI/4)
//                .waitSeconds(1);
//
//        waitForStart();
//
//        Clipspecimen();
//        Armtospecimen();
//        Cascadein();
//
//        if (isStopRequested()) return;
//
//        if (opModeIsActive()) {
//            Actions.runBlocking(
//                    new SequentialAction(
//                            Cascadein(),
//                            Clipspecimen(),
//                            new ParallelAction(
////                                    Armtospecimen(),
//                                    gotoSpecimen.build()
//                            ),
//                            Attatchspecimen(),
//                            goawayfromSpecimen.build(),
//                            Unclipspecimen(),
//                            new SleepAction(0.5),
//                            Unattatchspecimen(),
//                            Sample1.build(),
//                            new ParallelAction(
////                                    Cascadeout(),
//                                    Intakeclawpivotdown(),
//                                    Intakeclawopen()
//                            ),
//                            new SleepAction(1.5),
//                            Intakeclawclose(),
//                            new SleepAction(0.7),
//                            new ParallelAction(
//                                    Intakeclawpivotup(),
//                                    Cascadein(),
//                                    Basket1.build()
////                                    Armtotransfer()
//                            ),
//                            new SleepAction(0.1),
//                            Clipspecimen(),
//                            new SleepAction(0.5),
//                            Intakeclawopen(),
//                            new ParallelAction(
////                                    Armtobasket()
////                                    Liftup()
//                            ),
//                            new SleepAction(0.5),
//                            Unclipspecimen(),
//                            new ParallelAction(
////                                    Liftdown(),
//                                    Sample2.build(),
////                                    Cascadeout(),
//                                    Intakeclawpivotdown()
//                            ),
//                            new SleepAction(1.5),
//                            Intakeclawclose(),
//                            new SleepAction(0.7),
//                            new ParallelAction(
//                                    Intakeclawpivotup(),
//                                    new SleepAction(0.1),
//                                    Cascadein(),
//                                    Basket2.build()
////                                    Armtotransfer()
//                            ),
//                            Clipspecimen(),
//                            Intakeclawopen(),
//                            new ParallelAction(
//                                    Armtobasket()
////                                    Liftup()
//                            ),
//                            new SleepAction(0.5),
//                            Unclipspecimen(),
//                            new ParallelAction(
////                                    Liftdown(),
//                                    Sample3.build(),
////                                    Cascadeout(),
//                                    Intakeclawpivotdown()
//                            ),
//                            new SleepAction(1.5),
//                            Intakeclawclose(),
//                            new SleepAction(0.7),
//                            new ParallelAction(
//                                    Intakeclawpivotup(),
//                                    new SleepAction(0.1),
//                                    Cascadein(),
//                                    Basket3.build()
////                                    Armtotransfer()
//                            ),
//                            Clipspecimen(),
//                            Intakeclawopen(),
//                            new ParallelAction(
////                                    Armtobasket()
//                            ),
//                            new SleepAction(0.5 ),
//                            Unclipspecimen()
//
//                    )
//            );
//        }
//    }
//}