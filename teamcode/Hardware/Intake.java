package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedServo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Intake {

    DcMotorEx intakeM;
    OptimisedMotor spinner = new OptimisedMotor(intakeM);

    Servo leftservo;
    OptimisedServo leftangleintake = new OptimisedServo(leftservo);
    Servo rightservo;
    OptimisedServo rightangleintake = new OptimisedServo(rightservo);

    public void init(HardwareMap hwMap) {
        spinner.setName("Spinner", hwMap);
        spinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinner.setDirection(DcMotorSimple.Direction.REVERSE);

        leftangleintake.setName("leftangle", hwMap);
        rightangleintake.setName("rightangle", hwMap);
    }

    public void turnOn(Gamepad gamepad) {
        if (gamepad.right_trigger > 0) {
            leftangleintake.setPosition(1);
            rightangleintake.setPosition(0.7);
            spinner.setPower(1);
        } else {
            leftangleintake.setPosition(0);
            rightangleintake.setPosition(-0.3);
            spinner.setPower(0);
        }
    }
}




