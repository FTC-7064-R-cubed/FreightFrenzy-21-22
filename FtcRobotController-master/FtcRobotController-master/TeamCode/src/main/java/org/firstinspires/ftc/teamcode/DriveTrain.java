package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Lucas Botbol
 */

@TeleOp
public class DriveTrain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("mfl");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("mbl");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("mfr");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("mbr");
        DcMotor carrousel = hardwareMap.dcMotor.get("mc") ;

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            double lt = gamepad1.left_trigger;
            double rt = gamepad1.right_trigger;

            if(gamepad1.x){
                carrousel.setPower(.9);
            }
            if(gamepad1.y){
                carrousel.setPower(-.9);
            }
            if(gamepad1.b){
                carrousel.setPower(0);
            }

            if(lt>0.1){
                motorFrontLeft.setPower(-lt);
                motorBackLeft.setPower(-lt);
                motorFrontRight.setPower(-lt);
                motorBackRight.setPower(-lt);
            }
            if(rt>0.1){
                motorFrontLeft.setPower(rt);
                motorBackLeft.setPower(rt);
                motorFrontRight.setPower(rt);
                motorBackRight.setPower(rt);
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}