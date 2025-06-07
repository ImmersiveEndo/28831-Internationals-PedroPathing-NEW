package pedroPathing.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import pedroPathing.SUBSYSTEMS.Claw;
import pedroPathing.SUBSYSTEMS.Drivetrain;
import pedroPathing.SUBSYSTEMS.CascadeSlides;
import pedroPathing.SUBSYSTEMS.CascadePivot;

@TeleOp(name = "PIDF_Testing", group = "Linear OpMode")
public class PIDF_Test extends LinearOpMode{
//    private Drivetrain drivetrain;
//    private Claw claw;
    private CascadeSlides slides;
//    private CascadePivot pivot;

    @Override
    public void runOpMode() {
//        drivetrain = new Drivetrain();
//        drivetrain.init(hardwareMap);

//        claw = new Claw();
//        claw.init(hardwareMap);

        slides = new CascadeSlides();
        slides.init(hardwareMap);

//        pivot = new CascadePivot();
//        pivot.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {
            int slidepos = slides.getCurrentPosition();
            slides.moveToPosition(500, 0.05);

        }
    }
}
