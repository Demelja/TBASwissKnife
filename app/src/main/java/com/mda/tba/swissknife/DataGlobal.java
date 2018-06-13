package com.mda.tba.swissknife;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Owner on 2017-11-18.
 * deemmel@gmail.com
 */

// класс для хранения глобальных данных:: можно читать, можно записывать
// фактически, это моя персональная библиотечка всего-всего
// добавить описание com.mda.tba.swissknife.DataGlobal dg = com.mda.tba.swissknife.DataGlobal.getInstance(); и OdFan = dg.getOdFan();
// и ещё здесь общеиспользуемые методы:
// -- преобразование десятичного числа в число с дробной частью

public final class DataGlobal {
    private static DataGlobal mInstance;
    // =======================================================================
    public static DataGlobal getInstance() {
        if (mInstance == null) { mInstance = new DataGlobal(); }
        return mInstance;
    }

    // ================ Measurement Units ====================================
    private String measurementUnits = "Imperial"; // "Metric"


    // ================ Pitot Tube ===========================================
    private int sizeDuctImperial = 12, sizeDuctMetric = 300;
    private int setPoint4Imperial = 4, setPoint4Metric = 100;
    private int setPoint6Imperial = 6, setPoint6Metric = 150;
    private int setPoint10Imperial = 10, setPoint10Metric = 250;
    private int setPoint12Imperial = 12, setPoint12Metric = 300;
    private int setPoint15Imperial = 15, setPoint15Metric = 380;
    private int setPoint24Imperial = 24, setPoint24Metric = 610;
    private int setPoint30Imperial = 30, setPoint30Metric = 750;
    private int setPoint35Imperial = 35, setPoint35Metric = 890;
    private int setPoint48Imperial = 48, setPoint48Metric = 1220;
    private int setPoint63Imperial = 63, setPoint63Metric = 1600;
    private int setPoint80Imperial = 80, setPoint80Metric = 2030;
    private int setPoint99Imperial = 99, setPoint99Metric = 2515;


    // ================ Traverse =============================================
    private String ductworkShape = "rect"; // "circ", "oval"
    private String methodCalculation = "logt"; // "area"
    private float size1Imperial = 0.0f, size2Imperial = 0.0f;
    private int size1Metric = 0, size2Metric = 0;
    private int readingsA = 0, readingsB = 0;
    private float areaImperial = 0, areaMetric = 0;
    private int velocityImperial = 0;
    private float velocityMetric = 0;
    private int volumeImperial = 0, volumeMetric = 0;
    private int volumeDesignImperial = 0, volumeDesignMetric = 0;
    private int ratioVolume = 0;


    // =======================================================================
    private DataGlobal() {
        //	Log.w("MY_TAG", "com.mda.tba.swissknife.DataGlobal::com.mda.tba.swissknife.DataGlobal()");
    }

    public String getMeasurementUnits(int unit) {
        switch (unit) {
            case 0:
                measurementUnits = "Imperial";
                break;
            case 1:
                measurementUnits = "Metric";
                break;
            default:
        }
        return measurementUnits;
    }

    public String getMethodCalculation(int method) {
        switch (method) {
            case 0:
                measurementUnits = "logt";
                break;
            case 1:
                measurementUnits = "area";
                break;
            default:
        }
        return measurementUnits;
    }

    public int getSetPoint4(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint4Imperial : setPoint4Metric; }
    public void setSetPoint4(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint4Imperial = n;
        } else {
            setPoint4Metric = n;
        }
    }
    public int getSetPoint6(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint6Imperial : setPoint6Metric; }
    public void setSetPoint6(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint6Imperial = n;
        } else {
            setPoint6Metric = n;
        }
    }
    public int getSetPoint10(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint10Imperial : setPoint10Metric; }
    public void setSetPoint10(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint10Imperial = n;
        } else {
            setPoint10Metric = n;
        }
    }
    public int getSetPoint12(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint12Imperial : setPoint12Metric; }
    public void setSetPoint12(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint12Imperial = n;
        } else {
            setPoint12Metric = n;
        }
    }
    public int getSetPoint15(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint15Imperial : setPoint15Metric; }
    public void setSetPoint15(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint15Imperial = n;
        } else {
            setPoint15Metric = n;
        }
    }
    public int getSetPoint24(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint24Imperial : setPoint24Metric; }
    public void setSetPoint24(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint24Imperial = n;
        } else {
            setPoint24Metric = n;
        }
    }
    public int getSetPoint30(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint30Imperial : setPoint30Metric; }
    public void setSetPoint30(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint30Imperial = n;
        } else {
            setPoint30Metric = n;
        }
    }
    public int getSetPoint35(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint35Imperial : setPoint35Metric; }
    public void setSetPoint35(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint35Imperial = n;
        } else {
            setPoint35Metric = n;
        }
    }
    public int getSetPoint48(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint48Imperial : setPoint48Metric; }
    public void setSetPoint48(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint48Imperial = n;
        } else {
            setPoint48Metric = n;
        }
    }
    public int getSetPoint63(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint63Imperial : setPoint63Metric; }
    public void setSetPoint63(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint63Imperial = n;
        } else {
            setPoint63Metric = n;
        }
    }
    public int getSetPoint80(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint80Imperial : setPoint80Metric; }
    public void setSetPoint80(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint80Imperial = n;
        } else {
            setPoint80Metric = n;
        }
    }
    public int getSetPoint99(String savedUnit) {
        return (savedUnit.equals("Imperial")) ? setPoint99Imperial : setPoint99Metric; }
    public void setSetPoint99(int n, String savedUnit) {
        if (savedUnit.equals("Imperial")) {
            setPoint99Imperial = n;
        } else {
            setPoint99Metric = n;
        }
    }

    public String getDuctworkShape() { return ductworkShape; }
    public void setDuctworkShape(String t) { ductworkShape = t; }

    public float getSize1Imperial() { return size1Imperial; }
    public void setSize1Imperial(float f) { size1Imperial = f; }

    public float getSize2Imperial() { return size2Imperial; }
    public void setSize2Imperial(float f) { size2Imperial = f; }

    public int getSize1Metric() { return size1Metric; }
    public void setSize1Metric(int n) { size1Metric = n; }

    public int getSize2Metric() { return size2Metric; }
    public void setSize2Metric(int n) { size2Metric = n; }

    public int getReadingsA() { return readingsA; }
    public void setReadingsA(int n) { readingsA = n; }

    public int getReadingsB() { return readingsB; }
    public void setReadingsB(int n) { readingsB = n; }

    public float getAreaImperial() { return areaImperial; }
    public void setAreaImperial(float f) { areaImperial = f; }

    public float getAreaMetric() { return areaMetric; }
    public void setAreaMetric(float f) { areaMetric = f; }

    public int getVelocityImperial() { return velocityImperial; }
    public void setVelocityImperial(int n) { velocityImperial = n; }

    public float getVelocityMetric() { return velocityMetric; }
    public void setVelocityMetric(float f) { velocityMetric = f; }

    public int getVolumeImperial() { return volumeImperial; }
    public void setVolumeImperial(int n) { volumeImperial = n; }

    public int getVolumeMetric() { return volumeMetric; }
    public void setVolumeMetric(int n) { volumeMetric = n; }

    public int getVolumeDesignImperial() { return volumeDesignImperial; }
    public void setVolumeDesignImperial(int n) { volumeDesignImperial = n; }

    public int getVolumeDesignMetric() { return volumeDesignMetric; }
    public void setVolumeDesignMetric(int n) { volumeDesignMetric = n; }

    public int getRatioVolume() { return ratioVolume; }
    public void setRatioVolume(int n) { ratioVolume = n; }




    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // функция преобразования числа в дробь ¼½¾⅛⅜⅝⅞
	/* ========================================================================= */
    public String convertNumbers(float n) {
        String itog = "";
        String itogMant = "";
        float mant = 0;
        mant = n - (int) n;
        int i = (int) n;
        if (mant >= 0.9375f) {
            i = i + 1;
        } else if (mant >= 0.8125f) {
            itogMant = "⅞";
        } else if (mant >= 0.6875f) {
            itogMant = "¾";
        } else if (mant >= 0.5625f) {
            itogMant = "⅝";
        } else if (mant >= 0.4375f) {
            itogMant = "½";
        } else if (mant >= 0.3125f) {
            itogMant = "⅜";
        } else if (mant >= 0.1875f) {
            itogMant = "¼";
        } else if (mant >= 0.0625f) {
            itogMant = "⅛";
        }
        itog = i + itogMant;
        return itog;
    } // ======================================================================


    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // округление до digits знаков после запятой
    // !! 0 знаков сделать не может, использовать Math.round();
	/* ========================================================================= */
    public float roundUp(float value, int digits)
    {
        int pow = 10;
        for (int i = 1; i < digits; i++)
        {
            pow *= 10;
        }
        float tmp = value * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }



    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //
    //
	/* ========================================================================= */
    public boolean customArc(float startX, float startY, float endX, float endY, float startAngle, float sweepAngle, Canvas canvas, Paint paint) {

        float centerX = startX + (endX - startX) / 2;
        float centerY = startY + (endY - startY) / 2;
        float radius = (float) Math.sqrt((endX - centerX) * (endX - centerX) + (endY - centerY) * (endY - centerY));


        for (int deltaAngle = (int) Math.round(startAngle * Math.PI / 180 * 100); deltaAngle <= Math.round((startAngle + sweepAngle) * Math.PI / 180 * 100); deltaAngle++) {

            float pointX = (float) (centerX + radius * Math.sin((double)deltaAngle / 100));
            float pointY = (float) (centerX + radius * Math.cos((double)deltaAngle / 100));

            canvas.drawPoints(new float[] {pointX, pointY}, paint);

        }

        return true;
    }
}
