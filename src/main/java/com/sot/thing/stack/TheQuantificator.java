package com.sot.thing.stack;

import com.sot.thing.Thing;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by kevin on 9/24/2015.
 */
public class TheQuantificator {

    private static Random random = new Random(System.currentTimeMillis());

    public static Map<String,Double> multiplyByQty(Thing thing,int qty) {
        Map<String,Double> outputMap = new HashMap<String, Double>();
        outputMap.put("height",Double.valueOf(thing.getHeight()*qty));
        outputMap.put("width",Double.valueOf(thing.getWidth()*qty));
        outputMap.put("weight",Double.valueOf(thing.getWeight()*qty));
        outputMap.put("length",Double.valueOf(thing.getLength()*qty));
        outputMap.put("radius",Double.valueOf(thing.getRadius()*qty));
        outputMap.put("volume",Double.valueOf(thing.getVolume()*qty));
        outputMap.put("currentPrice",Double.valueOf(thing.getCurrentPrice()*qty));
        outputMap.put("originalPrice",Double.valueOf(thing.getOriginalPrice()*qty));

        double area = 0.0;
        // calculate area
        if (thing.getRadius()>0) {
            area = ((thing.getRadius() * Math.PI) * qty);
        } else {
            area = ((thing.getLength() * thing.getWidth()) * qty);
        }
        outputMap.put("area",area);

        return outputMap;
    }

    /**
     * Examples:
     * if (> 9), show no decimal
     * if (< 1), show to first significant digit
     * FOR EVERYTHING RIGHT OF THE DECIMAL
     * if ( 3.15<x<3.45 ) make it "1/3"
     * if ( 4.5<x<5.5 ) make it 1/2
     * if ( 6.15<x<6.45 ) make it "2/3"
     * @param decimal
     * @return
     */
    public static String friendlyNumberFormatter(BigDecimal decimal) {
        String qtyString = "Not Found";
        BigDecimal intermediateVal = new BigDecimal(0.0);
        // strip out all decimals from numbers larger than 9
        if (decimal.doubleValue() >= 25.0) {
            // first get the whole number component
            intermediateVal = decimal.setScale(0, BigDecimal.ROUND_HALF_UP);

            qtyString = getSpecialLargeIntegerString(intermediateVal);

        } else if (decimal.doubleValue()<25.0) {

            qtyString = getFriendlyFractionString(decimal);

        }

        return qtyString;
    }

    public static String createFractionString(BigDecimal decimal) {
        double x = decimal.doubleValue();
        double tolerance = 1.0E-6;
        double num1=1;
        double num2=0;
        double den1=0;
        double den2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = num1;
            num1 = a*num1+num2;
            num2 = aux;
            aux = den1;
            den1 = a*den1+den2;
            den2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-num1/den1) > x*tolerance);

        long denominator = (long)den1;
        // now round off the denominator to have 2 significant digits
        // it is possible to have run into a non-one numerator, so we will revisit
        if (num1 > 1) {
            // for the denominator down by rounding
            denominator = Double.valueOf(denominator / num1).longValue();
            num1 = 1;
        }

        // we know it to be non-zero
        if (denominator > 100) {
            // figure out hot many places it has
            int oom = Long.toString(denominator).length();
            // we pick more significant figures for larger numbers
            if (denominator >= 10000) {
                oom = 3;
            } else {
                oom = oom - 2;
            }

            long power = Double.valueOf(Math.pow(10,oom)).longValue();
            double scaled = (double)denominator / power;
            scaled = Math.round(scaled);
            denominator = Double.valueOf(scaled*power).longValue();
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        String denom = formatter.format(denominator);
        return "<sup>"+(int)num1+"</sup>&frasl;<sub>"+denom+"</sub>";
    }



    protected static String getFriendlyFractionString(BigDecimal decimal) {
        // get the components of the number
        int wholeComponent = (int)decimal.doubleValue();
        double fracComponent = decimal.doubleValue() - wholeComponent;
        String qtyString = "";

        if (decimal.doubleValue() < 0.1) {
            qtyString = createFractionString(decimal);
        } else if (decimal.doubleValue() >= 0.1) {
            if (((fracComponent < 0.20)) || (fracComponent > 0.90) ) {
                if (wholeComponent>0) {
                    qtyString = getSpecialSmallIntegerString(wholeComponent);
                } else {
                    int exponent = decimal.precision() - decimal.scale() - 1;
                    qtyString = decimal.round(new MathContext(exponent+2)).toPlainString();
                }
            } else if ((fracComponent >= 0.20) && (fracComponent < 0.30)) {
                if (wholeComponent>0) {
                    qtyString = wholeComponent + "-";
                }
                qtyString += "1/4";
            } else if ((fracComponent >= 0.30) && (fracComponent < 0.40)) {
                if (wholeComponent>0) {
                    qtyString = wholeComponent + "-";
                }
                qtyString += "1/3";
            } else if ((fracComponent >= 0.40) && (fracComponent < 0.60)) {
                if (wholeComponent>0) {
                    qtyString = wholeComponent + "-";
                }
                qtyString += "1/2";
            } else if ((fracComponent >= 0.60) && (fracComponent <= 0.70)) {
                if (wholeComponent>0) {
                    qtyString = wholeComponent + "-";
                }
                qtyString += "2/3";
            } else if ((fracComponent >= 0.70) && (fracComponent <= 0.90)) {
                if (wholeComponent>0) {
                    qtyString = wholeComponent + "-";
                }
                qtyString += "3/4";
            }
        }
        return qtyString;
    }

    /**
     * Determines if this number represents a special number. A Dozen, a Half-Dozen, a Six-Pack, One Miiiiilion, etc.
     * @param decimal
     * @return
     */
    private static String getSpecialLargeIntegerString(BigDecimal decimal) {
        String number = decimal.toPlainString();
        String returnVal = ""+number;
        switch(number) {
            case "144":
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "One Gross (144)";
                        break;
                    case 1:
                        returnVal = "144";
                        break;
                    default:
                        break;
                }
                break;
            case "1000":
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "One Thousand";
                        break;
                    case 1:
                        returnVal = "144";
                        break;
                    default:
                        break;
                }
                break;
            case "1000000":
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "One Miiiiiiillion";
                        break;
                    case 1:
                        returnVal = "144";
                        break;
                    default:
                        break;
                }
                break;
            case "1000000000":
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "One Biiiiiiillion";
                        break;
                    case 1:
                        returnVal = "144";
                        break;
                    default:
                        break;
                }
                break;
            default:
                // format it nicely
                DecimalFormat formatter = new DecimalFormat("#,###");
                returnVal = formatter.format(decimal);
                break;
        }
        return returnVal;
    }

    /**
     * Determines if this number represents a special number. A Dozen, a Half-Dozen, a Six-Pack, One Miiiiilion, etc.
     * @param specialNumber
     * @return
     */
    private static String getSpecialSmallIntegerString(int specialNumber) {
        String returnVal = ""+specialNumber;
        switch(specialNumber) {
            case 2:
                // 3 options: a pair of, a couple of, return int value
                switch(random.nextInt(3)) {
                    case 0:
                        returnVal = "A Couple of";
                        break;
                    case 1:
                        returnVal = "A Pair of";
                        break;
                    case 2:
                        returnVal = "2";
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch(random.nextInt(3)) {
                    case 0:
                        returnVal = "A Six-Pack of";
                        break;
                    case 1:
                        returnVal = "A Half-Dozen";
                        break;
                    case 2:
                        returnVal = "6";
                        break;
                    default:
                        break;
                }
                break;
            case 12:
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "A Dozen";
                        break;
                    case 1:
                        returnVal = "12";
                        break;
                    default:
                        break;
                }
                break;
            case 13:
                switch(random.nextInt(2)) {
                    case 0:
                        returnVal = "A Baker's Dozen";
                        break;
                    case 1:
                        returnVal = "13";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return returnVal;
    }

    /**
     * Takes a thing attribute value and divides it by another
     * @param divider You devide X
     * @param dividee By Y
     * @return A nicely formatted string representing the quantity
     */
    public static FillQty getFillQty(double divider, double dividee) {
        BigDecimal qty;

        FillQty fillQty = new FillQty();
        if (dividee == 0) {
            fillQty.setQtyString("0");
            fillQty.setQtyNumeric(new BigDecimal(0.0));
        } else if (divider == 0) {
            fillQty.setQtyString("Absolutely Zero");
            fillQty.setQtyNumeric(new BigDecimal(0.0));
        } else {
            qty = new BigDecimal(Math.abs(divider)).divide(new BigDecimal(Math.abs(dividee)),50,BigDecimal.ROUND_FLOOR);
            String formatted = TheQuantificator.friendlyNumberFormatter(qty);
            fillQty.setQtyString(formatted);
            fillQty.setQtyNumeric(qty);
        }
        return fillQty;

    }
}
