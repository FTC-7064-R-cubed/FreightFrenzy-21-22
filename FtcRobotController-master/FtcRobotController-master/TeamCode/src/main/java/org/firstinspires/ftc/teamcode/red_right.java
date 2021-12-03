package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

// Created by Lucas Botbol
// https://docs.revrobotics.com/kickoff-concepts/freight-frenzy-2021-2022/autonomous
// Make it park fully into the warehouse from the side closest to the warehouse in the red side

@Autonomous(name = "red_right")
public class red_right extends LinearOpMode {
    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;
    private DcMotor carrousel;

    //Convert from the counts per revolution of the encoder to counts per inch
    static final double HD_COUNTS_PER_REV = 28;
    static final double DRIVE_GEAR_REDUCTION = 20.15293;
    static final double WHEEL_CIRCUMFERENCE_MM = 90 * Math.PI;
    static final double DRIVE_COUNTS_PER_MM = (HD_COUNTS_PER_REV * DRIVE_GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE_MM;
    static final double DRIVE_COUNTS_PER_IN = DRIVE_COUNTS_PER_MM * 25.4;

    //Create elapsed time variable and an instance of elapsed time
    private ElapsedTime runtime = new ElapsedTime();

    private void drive(double power, double leftInches, double rightInches) {
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("mfl");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("mbl");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("mfr");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("mbr");

        int FrightTarget = 0;
        int BrightTarget = 0;
        int FleftTarget = 0;
        int BleftTarget = 0;
        if (opModeIsActive()) {
            // Create target positions
            FrightTarget = motorFrontRight.getCurrentPosition() + (int) (rightInches * DRIVE_COUNTS_PER_IN);
            BrightTarget = motorBackRight.getCurrentPosition() + (int) (rightInches * DRIVE_COUNTS_PER_IN);
            FleftTarget = motorFrontLeft.getCurrentPosition() + (int) (leftInches * DRIVE_COUNTS_PER_IN);
            BleftTarget = motorBackLeft.getCurrentPosition() + (int) (leftInches * DRIVE_COUNTS_PER_IN);

            // set target position
            motorBackLeft.setTargetPosition(BleftTarget);
            motorFrontLeft.setTargetPosition(FleftTarget);
            motorBackRight.setTargetPosition(BrightTarget);
            motorFrontRight.setTargetPosition(FrightTarget);

            //switch to run to position mode
            motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //run to position at the desiginated power

            motorBackLeft.setPower(power);
            motorFrontLeft.setPower(power);
            motorBackRight.setPower(power);
            motorFrontRight.setPower(power);

            // wait until both motors are no longer busy running to position
            while (opModeIsActive() && (motorBackLeft.isBusy() || motorFrontLeft.isBusy() || motorBackRight.isBusy() || motorFrontRight.isBusy())) {
            }

            // set motor power back to 0
            motorBackLeft.setPower(0);
            motorFrontLeft.setPower(0);
            motorBackRight.setPower(0);
            motorFrontRight.setPower(0);
        }
    }

    @Override
    public void runOpMode() {
        // already declared variables
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("mfl");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("mbl");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("mfr");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("mbr");
        DcMotor carrousel = hardwareMap.dcMotor.get("mc");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        if (opModeIsActive()) {

            //segment 1
            drive(0.9, 65, 65);
            runtime.reset();
//            while (opModeIsActive() && runtime.seconds() <= 7) {
//
//                //lift arm and hold
//                carrousel.setTargetPosition(720);
//                carrousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                carrousel.setPower(0.8);
//
//            }
        }

//        Now over here write and edit a couple of times the drive function to make it go to the warehouse
//        What need to do is figure out what position and configuration of variables within drive to make it go fully into the warehouse
//        Example: drive(0.4, 4, 4)


    }
}
