package org.firstinspires.ftc.teamcode.Hardware;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.LimitSwitch;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedMotor;
import org.firstinspires.ftc.teamcode.Hardware_Optimisations.OptimisedServo;
import org.firstinspires.ftc.teamcode.PID_classes.PDFController;

import org.firstinspires.ftc.teamcode.Constant;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Config
public class Outtake
{
    public ElapsedTime delay = new ElapsedTime();
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
    public static volatile double p = 0.0095;
    public static volatile double d = 0.001
            ;
    public static volatile double f = 0.09;
    PDFController lift = new PDFController(p,d,f);
    public NormalizedColorSensor senzorJos;
    public NormalizedColorSensor senzorSus;


    public int treapta = 1;
    public int liftTargetPosition = 0;
    public static double bratStanga = 0;
    public static double bratDreapta = 0;
    public static double rotitoare = 0;
    public static double carlig = 0;
    public boolean cob =  false;
    public boolean rid = true;
    public boolean full = false;
    public DigitalChannel bt;
    LimitSwitch buton = new LimitSwitch(bt);

    private final PDFController pdfController = new PDFController(p, d, f);

    public static volatile int pixelLevel = Constant.PIXEL_LEVEL_MIN;



    public void init(HardwareMap hwMap)
    {
        leftSlide.setName("leftMotor", hwMap);
        rightSlide.setName("rightMotor",hwMap);
        leftSlide.setPower(0.0);
        rightSlide.setPower(0.0);

        buton.setName("BUTON",hwMap);

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public void coboraretotal() {
        bratStanga = Constant.pozServoBratStangaInit;
        bratDreapta = Constant.pozServoBratDreaptaInit;
        rotitoare = Constant.pozRotitorInit;
        leftSlide.setPower(-1);
        rightSlide.setPower(-1);
    }
    public void pixelLevelDecrement()
    {
        pixelLevel--;
    }
    public void pixelLevelIncrement() {
        pixelLevel++;
    }

    public void rid()
    {
        rid = true;
        cob = false;
    }
    public void cob()
    {
        rid = false;
        cob = true;
    }
    public void setPid()
    {
        pdfController.setCoefficients(p, d, f);

        if (cob) {
            if (buton.isPressed()) {

                leftSlide.setPower(0.0);
                rightSlide.setPower(0.0);
                leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } else {
                leftSlide.setPower(Constant.LOWER_POWER);
                rightSlide.setPower(Constant.LOWER_POWER);
            }
        } else if (rid) {
            pixelLevel = Range.clip(pixelLevel, Constant.PIXEL_LEVEL_MIN, Constant.PIXEL_LEVEL_MAX);

            liftTargetPosition = Constant.PIXEL_LEVEL_BASE + Constant.PIXEL_LEVEL_INCREMENT * pixelLevel;

            double power = pdfController.update(liftTargetPosition, leftSlide.getCurrentPosition());
            leftSlide.setPower(power);
            rightSlide.setPower(power);
        }

    }


    public void punerePanou()
    {

            bratStanga = Constant.pozPozServoBratStangaPanou;
            bratDreapta = Constant.pozServoBratDreaptaPanou;
            rotitoare = Constant.pozRotitorPanou;

    }
    public void lasare() {
        if(senzorJos.getNormalizedColors().green > 0.2 && full)
           carlig = Constant.deschis;

    }
    public void verificare()
    {
        if(senzorJos.getNormalizedColors().green > 0.02 && !full)
        {
                carlig = Constant.inchis;
                full = true;
        }

    }
    public void updt(Telemetry telemetry)
    {
        telemetry.addData("Motor stanga",leftSlide.getCurrentPosition());
        telemetry.addData("Motor dreapta",rightSlide.getCurrentPosition());
        telemetry.update();
    }
}