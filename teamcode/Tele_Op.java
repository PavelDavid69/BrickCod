package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.Lansator;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;

@TeleOp
public class Tele_Op extends LinearOpMode {

    DriveTrain dt = new DriveTrain();
    Intake intake = new Intake();
    //Outtake outtake = new Outtake();
    //Lansator lansator = new Lansator();

    @Override
    public void runOpMode()
    {
        dt.init(hardwareMap);
        intake.init(hardwareMap);
        //outtake.init(hardwareMap);
        //lansator.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            dt.mecanumDrive(gamepad1);
            intake.turnOn(gamepad1);
            //intake.verificare();
            //outtake.ridicare(gamepad2);
            //outtake.coborare(gamepad2);
            //outtake.coboraretotal(gamepad2);
           // outtake.setPid();
           //outtake.punereIntake(gamepad2);
            //outtake.punerePanou(gamepad2);
            //outtake.lasare(gamepad2);
            //lansator.lansare(gamepad2);
        }
    }
    }