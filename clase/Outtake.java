package org.firstinspires.ftc.teamcode.clase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Outtake {

    public DcMotor Slider1 = null;
    public DcMotor Slider2 = null;
    public Servo Brat1 = null;
    public Servo Brat2 = null;
    public Servo Carlig = null;
    public ElapsedTime Cadere = new ElapsedTime();

    public void Ridicare()
    {

    }
    public void Coborare()
    {

    }
    public void Asezare()
    {
        Brat1.setPosition(1);
        Brat2.setPosition(1);
    }
    public void Punere()
    {
        Cadere.reset();
        Carlig.setPosition(0);
        if(Cadere.seconds() > 1)
        {
            Carlig.setPosition(1);
        }

    }
}
