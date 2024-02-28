package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedServo;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class Intake {

    DcMotorEx intakeM;
    OptimisedMotor spinner = new OptimisedMotor(intakeM);
    Servo carl;
    Servo leftservo;
    OptimisedServo leftangleintake = new OptimisedServo(leftservo);
    Servo rightservo;
    OptimisedServo rightangleintake = new OptimisedServo(rightservo);
    OptimisedServo carlig = new Outtake().gheara;
    //ColorSensor senzorSus;
    //ColorSensor senzorJos;
    public void init(HardwareMap hwMap) {
        spinner.setName("Spinner", hwMap);
        spinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinner.setDirection(DcMotorSimple.Direction.REVERSE);

        leftangleintake.setName("leftangle", hwMap);
        rightangleintake.setName("rightangle", hwMap);

        //senzorSus = hwMap.get(ColorSensor.class,"senzorSus");
        //senzorJos = hwMap.get(ColorSensor.class, "senzorJos");
    }

    public void turnOn(Gamepad gamepad) {
        if (gamepad.left_trigger > 0 && gamepad.left_bumper) {
            leftangleintake.setPosition(1);
            rightangleintake.setPosition(0.7);
            spinner.setPower(-1);
        } else if (gamepad.left_trigger > 0)
        {
            leftangleintake.setPosition(1);
            rightangleintake.setPosition(0.7);
            spinner.setPower(1);
        }
        else
        {
            leftangleintake.setPosition(0);
            rightangleintake.setPosition(-0.3);
            spinner.setPower(0);
        }
    }
    //public void verificare()
    {
        //if(senzorJos.green() > 0 && senzorSus.green() > 0)
            //carlig.setPosition(1);
    }//
}



