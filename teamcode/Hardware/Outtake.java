package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedServo;
import org.firstinspires.ftc.teamcode.PID_classes.PID_coeficients;
import org.firstinspires.ftc.teamcode.PID_classes.PID_controller;
import org.firstinspires.ftc.teamcode.Constant;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

public class Outtake
{
    DcMotorEx leftM, rightM;
    OptimisedMotor leftSlide = new OptimisedMotor(leftM);
    OptimisedMotor rightSlide = new OptimisedMotor(rightM);
    Servo leftB, rightB;
    OptimisedServo leftBrat = new OptimisedServo(leftB);
    OptimisedServo rightBrat = new OptimisedServo(rightB);
    Servo gh;
    OptimisedServo gheara = new OptimisedServo(gh);
    PID_coeficients pid = new PID_coeficients(0.01,0.0,0.005);
    PID_controller lift = new PID_controller(pid);

    public int treapta = 1;
    public boolean cobinst =  false;


    public void init(HardwareMap hwMap)
    {
        leftSlide.setName("leftMotor", hwMap);
        rightSlide.setName("rightMotor",hwMap);
        leftSlide.setPower(0.0);
        rightSlide.setPower(0.0);

        leftSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftBrat.setName("leftBrat", hwMap);
        rightBrat.setName("rightBrat", hwMap);
        gheara.setName("gheara", hwMap);
    }
    public void ridicare(Gamepad gamepad)
    {
        if(cobinst) {
            if (gamepad.dpad_up)
                cobinst = false;
        }
            else if (gamepad.dpad_up)
        {
            treapta++;
            treapta = Range.clip(treapta, 1, 11);
        }
    }
    public void coborare(Gamepad gamepad)
    {
        if(gamepad.dpad_down)
        {
            treapta--;
            treapta = Range.clip(treapta, 1, 11);
        }
    }
    public void coboraretotal(Gamepad gamepad)
    {
        if(gamepad.triangle)
            cobinst = true;
    }
    public void setPid()
    {
        int liftTargetPosition = 0;
        if(cobinst)
            liftTargetPosition= Constant.pixel_1_position;
        else
            liftTargetPosition = Constant.pixel_1_position + (treapta - 1) * Constant.pixel_level_increment;
        leftSlide.setPower(lift.update(leftSlide.getCurrentPosition(), liftTargetPosition));
        rightSlide.setPower(lift.update(leftSlide.getCurrentPosition(), liftTargetPosition));
    }

    public void punerePanou(Gamepad gamepad)
    {
        if(gamepad.right_bumper)
        {
            leftBrat.setPosition(1);
            rightBrat.setPosition(1);
        }
    }
    public void punereIntake(Gamepad gamepad)
    {
        if(gamepad.left_bumper)
        {
            leftBrat.setPosition(0);
            rightBrat.setPosition(0);
        }
    }
    public void lasare(Gamepad gamepad)
    {
        if(gamepad.cross)
            gheara.setPosition(0);
    }
}