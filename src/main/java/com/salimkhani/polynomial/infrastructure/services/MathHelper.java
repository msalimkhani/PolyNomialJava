package com.salimkhani.polynomial.infrastructure.services;

import com.salimkhani.polynomial.application.interfaces.IMathHelper;

public class MathHelper implements IMathHelper {
    @Override
    public boolean IsInt(double value) {
        var excti = (int) value;
        return (value - excti) == 0;
    }
}
