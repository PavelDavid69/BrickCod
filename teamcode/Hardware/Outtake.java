package org.firstinspires.ftc.teamcode.Hardware;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedServo;
import org.firstinspires.ftc.teamcode.PID_classes.PID_coeficients;
import org.firstinspires.ftc.teamcode.PID_classes.PID_controller;
import org.firstinspires.ftc.teamcode.Constant;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


public class Outtake
{
    ElapsedTime delay = new ElapsedTime();
    DcMotorEx leftM, rightM;
    OptimisedMotor leftSlide = new OptimisedMotor(leftM);
    OptimisedMotor rightSlide = new OptimisedMotor(rightM);
    static Servo leftB, rightB;
    public static OptimisedServo leftBrat = new OptimisedServo(leftB);
    public static OptimisedServo rightBrat = new OptimisedServo(rightB);
    Servo gh;
    Servo rot;
    OptimisedServo gheara = new OptimisedServo(gh);
    OptimisedServo rotitor = new OptimisedServo(rot);
    PID_coeficients pid = new PID_coeficients(0.01,0.0,0.005);
    PID_controller lift = new PID_controller(pid);
    public NormalizedColorSensor senzorJos;
    public NormalizedColorSensor senzorSus;


    public int treapta = 1;
    public static double bratStanga = 0;
    public static double bratDreapta = 0;
    public static double rotitoare = 0;
    public static double carlig = 0;
    public boolean cobinst =  false;
    public boolean desch = true;




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
        rotitor.setName("rotitor",hwMap);

        senzorJos = hwMap.get(NormalizedColorSensor.class,"senzorJos");
        senzorSus = hwMap.get(NormalizedColorSensor.class,"senzorSus");
        senzorJos.setGain(2);
        senzorSus.setGain(2);
    }

    public void initPozOut()
    {
        bratStanga = Constant.pozServoBratStangaInit;
        bratDreapta = Constant.pozServoBratDreaptaInit;
        rotitoare = Constant.pozRotitorInit;
        carlig = Constant.deschis;

    }
    public void Servouri()
    {
        leftBrat.setPosition(bratStanga);
        rightBrat.setPosition(bratDreapta);
        rotitor.setPosition(rotitoare);
        gheara.setPosition(carlig);
    }
    public void ridicare()
    {
        if(cobinst) {
                cobinst = false;
        }
        else
        {
            treapta++;
        }
    }
    public void coborare()
    {
        {
            treapta--;
        }
    }
    public void coboraretotal()
    {
            cobinst = true;

    }
    public void setPid()
    {
        int liftTargetPosition = 0;

        if(cobinst) {
            delay.reset();
            bratStanga = Constant.pozServoBratStangaInit;
            bratDreapta = Constant.pozServoBratDreaptaInit;
            rotitoare = Constant.pozRotitorInit;
            if(delay.milliseconds() > 500) {
                liftTargetPosition = Constant.pixel_1_position;
            }
        }
        else {

                liftTargetPosition = Constant.pixel_1_position + (treapta - 1) * Constant.pixel_level_increment;

        }
        leftSlide.setPower(lift.update(leftSlide.getCurrentPosition(), liftTargetPosition));
        rightSlide.setPower(lift.update(leftSlide.getCurrentPosition(), liftTargetPosition));

    }


    public void punerePanou()
    {

            bratStanga = Constant.pozPozServoBratStangaPanou;
            bratDreapta = Constant.pozServoBratDreaptaPanou;
            rotitoare = Constant.pozRotitorPanou;


    }
    public void lasare() {

        carlig = Constant.inchis;
        desch = false;
    }





    public void verificare()
    {
        if(senzorJos.getNormalizedColors().green > 0.01 && senzorSus.getNormalizedColors().green > 0.01)
            carlig = Constant.inchis;
    }
}