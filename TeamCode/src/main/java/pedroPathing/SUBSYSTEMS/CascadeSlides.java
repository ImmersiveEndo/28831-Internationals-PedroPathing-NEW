package pedroPathing.SUBSYSTEMS;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CascadeSlides {
    private DcMotorEx slideMotor;

//    private PIDFCoefficients pidf;
//    public static double p=12,i=0,d=0,f=0;

    public static double Kp = 11;
    public static double Ki = 0.00002;
    public static double Kd = 0.002;
    public static double Kg = 0.064;
    public static double lastError = 0;

    public static double integralSum = 0;
    public static double a = 1; // a can be anything from 0 < a < 1
    public static double previousEstimate = 0;
    public static double currentEstimate = 0;
    double lastReference;
    private static final double STOP_POWER = 0.0;


    public void init(HardwareMap hardwareMap) {

//      pidf = new PIDFCoefficients(p, i, d, f);

        slideMotor = hardwareMap.get(DcMotorEx.class, "cascadeDrive"); // Ensure this matches your config
        slideMotor.setDirection(DcMotorEx.Direction.REVERSE);
        slideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        slideMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        slideMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);        //comment this out
    }

    public double P(Telemetry telemetry, double reference){
        double position = slideMotor.getCurrentPosition();

        double error = reference - position;

        double out = Kp * error;

        telemetry.addData("reference", reference);
        telemetry.addData("position", position);
        telemetry.addData("error", error);
        telemetry.update();

        return out;
    }

    public double PI(Telemetry telemetry, double reference, ElapsedTime time){
        double position = slideMotor.getCurrentPosition();

        double error = reference - position;
        integralSum = integralSum + (error * time.seconds());

        if(reference != lastReference) integralSum = 0;

        lastReference = reference;
        double out = Kp * error + Ki * integralSum;

        telemetry.addData("reference", reference);
        telemetry.addData("position", position);
        telemetry.addData("error", error);
        telemetry.addData("integral sum", integralSum);
        telemetry.addData("out", out);
        telemetry.update();

        return out;
    }

    public double PID(Telemetry telemetry, double reference, ElapsedTime time){
        double position = slideMotor.getCurrentPosition();

        double error = reference - position;

        double errorChange = (error - lastError);

        currentEstimate = (a * previousEstimate) + (1-a) * errorChange;
        previousEstimate = currentEstimate;

        double derivative = currentEstimate / time.seconds();

        integralSum = integralSum + (error * time.seconds());

        if(reference != lastReference) integralSum = 0;

        lastError = error;

        lastReference = reference;

        time.reset();

        double out = Kp * error + Ki * integralSum + Kd * derivative;

        telemetry.addData("reference", reference);
        telemetry.addData("position", position);
        telemetry.addData("error", error);
        telemetry.addData("error change", errorChange);
        telemetry.addData("derivative", derivative);
        telemetry.addData("integral sum", integralSum);
        telemetry.addData("out", out);
        telemetry.update();

        return out;
    }

    public double PIDF(Telemetry telemetry, double reference, ElapsedTime time){
        double position = slideMotor.getCurrentPosition();

        double error = reference - position;

        double errorChange = (error - lastError);

        currentEstimate = (a * previousEstimate) + (1-a) * errorChange;
        previousEstimate = currentEstimate;

        double derivative = currentEstimate / time.seconds();

        integralSum = integralSum + (error * time.seconds());

        if(reference != lastReference) integralSum = 0;

        lastError = error;

        lastReference = reference;

        time.reset();

        double out = Kp * error + Ki * integralSum + Kd * derivative + Kg;

        telemetry.addData("reference", reference);
        telemetry.addData("position", position);
        telemetry.addData("error", error);
        telemetry.addData("error change", errorChange);
        telemetry.addData("derivative", derivative);
        telemetry.addData("integral sum", integralSum);
        telemetry.addData("out", out);
        telemetry.update();

        return out;
    }

    public void moveToPosition(int reference, double power) {
        slideMotor.setTargetPosition(reference);
        slideMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(power);
    }

    public void move(double power) {
        slideMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        slideMotor.setPower(power);
    }

    public void moveSlides(boolean extension, boolean retraction, double power) {
        if (extension) {
            slideMotor.setPower(power);
        }
        else if (retraction) {
            slideMotor.setPower(-power);
        }
    }

    public void stop() {
        slideMotor.setPower(STOP_POWER);
    }

    public int getCurrentPosition() {
        try {
            return (slideMotor.getCurrentPosition());
        } catch (Exception e) {
            // Handle position retrieval errors
            e.printStackTrace();
            return 0;
        }
    }
}