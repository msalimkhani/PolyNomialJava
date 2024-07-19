package com.salimkhani.polynomial.domain;

public class PolyPart
{
    private double coeff;
    private int power;

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) throws Exception {
        if(coeff != 0)
            this.coeff = coeff;
        else
            throw new Exception("Coeff must be unzero value");
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public PolyPart(double coeff, int power) throws Exception {
        if(coeff != 0)
            this.coeff = coeff;
        else
            throw new Exception("Coeff must be unzero value");
        this.power = power;
    }
    public static PolyPart New(double coeff, int power) throws Exception {
        return new PolyPart(coeff, power);
    }
    public double eval(double x)
    {
        return coeff * Math.pow(x, power);
    }
    public  boolean equalsPower(PolyPart part)
    {
        return getPower() == part.getPower();

    }
    @Override
    public String toString() {
        if(coeff > 0)
        {
            if (1 == coeff)
            {
                return " + X^" + power;
            }
            var ecti = (int) coeff;
            var rem = coeff - ecti;
            if(power != 0)
            {
                if(rem == 0)
                    return  " + " + ecti + "x^" + power;
                return " + " + coeff + "x^" + power;
            }
            else {
                if(rem == 0)
                    return " + " + String.valueOf(ecti);
                return " + " + String.valueOf(coeff);
            }
        }
        else if (coeff < 0)
        {
            coeff *= -1;
            var ecti = (int) coeff;
            var rem = coeff - ecti;
            if(power != 0)
            {
                if(rem == 0)
                    return  " - " +  ecti + "x^" + power;
                return " - " +  coeff + "x^" + power;
            }
            else {
                if(rem == 0)
                    return " - " +  String.valueOf(ecti);
                return " - " +  String.valueOf(coeff);
            }
        }
        else
        {
            return "";
        }
    }
}
