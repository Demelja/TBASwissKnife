package com.mda.tba.swissknife;

/**
 * Created by Owner on 2018-02-05.
 * deemmel@gmail.com
 *
 * внутренние рассчет ведутся в мм, мм.кв, мм/с, мм.куб/с
 *
 */

public class DataSwissknife {
    private static DataSwissknife mInstance;
    // ========================================================================
    public static DataSwissknife getInstance() {
        if (mInstance == null) { mInstance = new DataSwissknife(); }
        return mInstance;
    }

    // ================ internal          =====================================
    private long size1 = 0, size2 = 0, velocity = 0, designVolume = 0;



    // ================ Public  sizes (mm) ====================================
    public long get_Size1() { return size1; }
    public long get_Size2() { return size2; }


    // ================ Public  SIZE1 (mm) ====================================
    public String getSize1(int unitCurrent) {
        String sizeCurrent = "";
        switch (unitCurrent) {
            case 0: // "inch":
                sizeCurrent = convertNumbers(size1 / 25.4f);
                break;
            case 1: // "mm":
                sizeCurrent = String.valueOf(size1);
                break;
            default:
        }
        return sizeCurrent;
    }
    public void setSize1(String sizeCurrent, int unitCurrent) {
        float sizeC = Float.parseFloat(sizeCurrent);
        switch (unitCurrent) {
            case 0: // "inch":
                size1 = Math.round(sizeC * 25.4f);
                break;
            case 1: // "mm":
                size1 = Math.round(sizeC);
                break;
            default:
        }
    }
    // ================ Public  SIZE2  (mm) ===================================
    public String getSize2(int unitCurrent) {
        String sizeCurrent = "";
        switch (unitCurrent) {
            case 0: // "inch":
                sizeCurrent = convertNumbers(size2 / 25.4f);
                break;
            case 1: // "mm":
                sizeCurrent = String.valueOf(size2);
                break;
            default:
        }
        return sizeCurrent;
    }
    public void setSize2(String sizeCurrent, int unitCurrent) {
        float sizeC = Float.parseFloat(sizeCurrent);
        switch (unitCurrent) {
            case 0: //"inch":
                size2 = Math.round(sizeC * 25.4f);
                break;
            case 1: //"mm":
                size2 = Math.round(sizeC);
                break;
            default:
        }
    }
    // ================ Public  AREA  (sq.mm)   ===============================
    public String getArea(int unitCurrent, String shapeCurrent) {
        String areaCurrent = "";
        switch (unitCurrent) {
            case 0: //"sq.ft":
                areaCurrent = String.valueOf(roundUp(calculateArea(shapeCurrent) / 92903.04f, 2));
                break;
            case 1: //"sq.m":
                areaCurrent = String.valueOf(roundUp(calculateArea(shapeCurrent) / 1000000.0f, 3));
                break;
            default:
        }
        return areaCurrent;
    }
    // ================ Public  VELOCITY  (mm/s) ==============================
    public String getVelocity(int unitCurrent) {
        String velocityCurrent = "";
        switch (unitCurrent) {
            case 0: //"fpm":
                velocityCurrent = String.valueOf(Math.round(velocity / 5.08f));
                break;
            case 1: //"m/s":
                velocityCurrent = String.valueOf(roundUp(velocity / 1000.0f, 3));
                break;
            default:
        }
        return velocityCurrent;
    }
    public void setVelocity(String velocityCurrent, int unitCurrent) {
        float velocityC = Float.parseFloat(velocityCurrent);
        switch (unitCurrent) {
            case 0: //"fpm":
                velocity = Math.round(velocityC * 5.08f);
                break;
            case 1: //"m/s":
                velocity = Math.round(velocityC * 1000.0f);
                break;
            default:
        }
    }
    // ================ Public  VOLUME  (cu.mm/s) =============================
    public String getVolume(int unitCurrent, String shapeCurrent) {
        String volumeCurrent = "";
        switch (unitCurrent) {
            case 0: //"cfm":
                volumeCurrent = String.valueOf(Math.round(calculateVolume(shapeCurrent) / 471947.4432f));
                break;
            case 1: //"l/s":
                volumeCurrent = String.valueOf(roundUp(calculateVolume(shapeCurrent) / 1000000.0f, 1));
                break;
            case 2: //"cu.m/h":
                volumeCurrent = String.valueOf(roundUp(calculateVolume(shapeCurrent) * 0.0000036f, 3));
                break;
            default:
        }
        return volumeCurrent;
    }
    // ================ Public  DESIGN  (cu.mm/s) =============================
    public String getDesign(int unitCurrent, String shapeCurrent, String pretextRatio, String unitText) {
        String designRateText = "";
        float designCurrent = 0.0f;
        String designCurrentText = "";
        float designRate = 0.0f;
        if (designVolume > 0) {
            switch (unitCurrent) {
                case 0: //"cfm":
                    designCurrent = designVolume / 471947.4432f;
                    designCurrentText = String.valueOf(Math.round(designCurrent));
                    break;
                case 1: //"l/s":
                    designCurrent = designVolume / 1000000.0f;
                    designCurrentText = String.valueOf(roundUp(designCurrent, 1));
                    break;
                case 2: //"cu.m/h":
                    designCurrent = designVolume * 0.0000036f;
                    designCurrentText = String.valueOf(roundUp(designCurrent, 3));
                    break;
                default:
            }
            designRate = Float.parseFloat(getVolume(unitCurrent, shapeCurrent)) / designCurrent * 100.0f;
            designRateText = String.valueOf(Math.round(designRate)) + "%"
                    + pretextRatio + designCurrentText + " " + unitText;
        }
        return designRateText;
    }
    public void setDesign(String volume, int unitCurrent) {
        switch (unitCurrent) {
            case 0: //"cfm":
                designVolume = (long) (Math.round(Float.parseFloat(volume) * 471947.4432f));
                break;
            case 1: //"l/s":
                designVolume = (long) (Math.round(Float.parseFloat(volume) * 1000000.0f));
                break;
            case 2: //"cu.m/h":
                designVolume = (long) (Math.round(Float.parseFloat(volume) / 0.0000036f));
                break;
            default:
        }
    }





    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // функция преобразования числа в дробь ¼½¾⅛⅜⅝⅞
	// ========================================================================
    private String convertNumbers(float n) {
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
	// ========================================================================
    private float roundUp(float value, int digits)
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
    // функция расчета площади в зависимости от сечения воздуховода
    // ========================================================================
    private long calculateArea(String shapeCurrent) {
        long a = 0;
        switch (shapeCurrent) {
            case "circ":
                a = Math.round(Math.PI * size2 * size2 / 4);
                break;
            case "rect":
                a = Math.round(size1 * size2);
                break;
            case "oval":
                if (size1 > size2) {
                    // присвоить ReadingsA количество сечений для КРУГЛОГО по Size2, а ReadingsB - ПРЯМОУГОЛЬНИК по Size2
                    a = Math.round((size1 - size2) * size2 + Math.PI * size2 * size2 / 4);
                } else {
                    // присвоить ReadingsA количество сечений для ПРЯМОУГОЛЬНИК по Size1, а ReadingsB - КРУГЛОГО по Size1
                    a = Math.round((size2 - size1) * size1 + Math.PI * size1 * size1 / 4);
                }
                break;
            default:
        }
        return a;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // функция расчета об’ема воздуха в зависимости от сечения воздуховода
    // ========================================================================
    private long calculateVolume(String shapeCurrent) {
        long v = 0;
        switch (shapeCurrent) {
            case "circ":
                v = Math.round(velocity * Math.PI * size2 * size2 / 4);
                break;
            case "rect":
                v = Math.round(velocity * size1 * size2);
                break;
            case "oval":
                if (size1 > size2) {
                    // присвоить ReadingsA количество сечений для КРУГЛОГО по Size2, а ReadingsB - ПРЯМОУГОЛЬНИК по Size2
                    v = Math.round(velocity * ((size1 - size2) * size2 + Math.PI * size2 * size2 / 4));
                } else {
                    // присвоить ReadingsA количество сечений для ПРЯМОУГОЛЬНИК по Size1, а ReadingsB - КРУГЛОГО по Size1
                    v = Math.round(velocity * ((size2 - size1) * size1 + Math.PI * size1 * size1 / 4));
                }
                break;
            default:
        }
        return v;
    }

}
