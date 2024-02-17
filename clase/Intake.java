package org.firstinspires.ftc.teamcode.clase;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake{

    public DcMotor MIntake = null;
    public Servo ServoStanga = null;
    public Servo ServoDreapta = null;


    public void IntakeF()
    {
        ServoDreapta.setPosition(1);
        ServoStanga.setPosition(-1);
        MIntake.setPower(1);
    }
    public void IntakeN()
    {
        ServoDreapta.setPosition(0);
        ServoStanga.setPosition(0);
        MIntake.setPower(0);

    }
}

