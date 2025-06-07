package pedroPathing.SUBSYSTEMS;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CascadePivot {
    private DcMotorEx pivotMotor;

//    private PIDFCoefficients pidf;
//    public static double p=12,i=0,d=0,f=0;

    public static double Kp = 0.001;
    public static double Ki = 0.00002;
    public static double Kd = 0.002;
    public static double Kg = 0.00115;
    public static double lastError = 0;

    public static double integralSum = 0;
    public static double a = 1; // a can be anything from 0 < a < 1
    public static double previousEstimate = 0;
    public static double currentEstimate = 0;
    double lastReference;
    private static final double STOP_POWER = 0.0;


    public void init(HardwareMap hardwareMap) {

//        pidf = new PIDFCoefficients(p, i, d, f);

        pivotMotor = hardwareMap.get(DcMotorEx.class, "pivotDrive"); // Ensure this matches your config
        pivotMotor.setDirection(DcMotorEx.Direction.FORWARD);
        pivotMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        pivotMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        pivotMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        pivotMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//      pivotMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public double P(Telemetry telemetry, double reference){
        double position = pivotMotor.getCurrentPosition();

        double error = reference - position;

        double out = Kp * error;

        telemetry.addData("reference", reference);
        telemetry.addData("position", position);
        telemetry.addData("error", error);
        telemetry.update();

        return out;
    }

    public double PI(Telemetry telemetry, double reference, ElapsedTime time){
        double position = pivotMotor.getCurrentPosition();

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
        double position = pivotMotor.getCurrentPosition();

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
        double position = pivotMotor.getCurrentPosition();

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
        pivotMotor.setTargetPosition(reference);

        pivotMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        pivotMotor.setPower(power);
    }

    public void move(double power) {
        pivotMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        pivotMotor.setPower(power);
    }

    public void movePivot(boolean up, boolean down) {
        if (up) {
            pivotMotor.setPower(1);
        }
        else if (down) {
            pivotMotor.setPower(-1);
        }
    }

    public void stop() {
        pivotMotor.setPower(STOP_POWER);
    }

    public int getCurrentPosition() {
        try {
            return (pivotMotor.getCurrentPosition());
        } catch (Exception e) {
            // Handle position retrieval errors
            e.printStackTrace();
            return 0;
        }
    }

}