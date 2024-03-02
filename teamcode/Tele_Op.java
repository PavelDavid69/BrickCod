package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.Lansator;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;

@TeleOp
public class Tele_Op extends LinearOpMode {

    DriveTrain dt = new DriveTrain();
    Intake intake = new Intake();
    Outtake outtake = new Outtake();
    //Lansator lansator = new Lansator();


    @Override
    public void runOpMode()
    {
        dt.init(hardwareMap);
        intake.init(hardwareMap);
        outtake.init(hardwareMap);
        //lansator.init(hardwareMap);
      //  while(opModeInInit()) {
            intake.initPozInt();
            outtake.initPozOut();
   //     }




        waitForStart();

        while(opModeIsActive()){

            dt.mecanumDrive(gamepad1);
            if(gamepad1.left_trigger > 0 && gamepad1.left_bumper)
            intake.turnOnReverse();
            else if (gamepad1.left_trigger > 0)
            {
              intake.turnOn();
            }
            else
                intake.turnOff();

            intake.initPozInt();
            outtake.verificare();
            outtake.Servouri();
            if(gamepad2.dpad_up)
            outtake.ridicare();
            if(gamepad2.dpad_down)
            outtake.coborare();
                if (gamepad2.triangle)
            outtake.coboraretotal();
            outtake.setPid();
            if(gamepad2.right_bumper)
            outtake.punerePanou();
            if(gamepad2.triangle)
            outtake.lasare();
            //lansator.lansare(gamepad2);

        }
    }
    }