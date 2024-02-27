package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class testservo extends LinearOpMode {

    private Servo servo;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class,"servo");

        waitForStart();
        while(opModeIsActive())
        {
            servo.setPosition(1);
            sleep(1000);
            servo.setPosition(0);
            sleep(29000);
        }

    }
}
