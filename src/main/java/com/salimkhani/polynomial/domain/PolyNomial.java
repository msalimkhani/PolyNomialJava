package com.salimkhani.polynomial.domain;

import com.salimkhani.polynomial.application.interfaces.IMathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PolyNomial
{
    private List<PolyPart> polyParts;
    public PolyNomial(List<PolyPart> polyParts) {
        this.polyParts = polyParts;
    }
    public PolyNomial()
    {
        this.polyParts = new ArrayList<>();
    }
    public static PolyNomial New(List<PolyPart> polyParts)
    {
        return new PolyNomial(polyParts);
    }
    public List<PolyPart> getPolyParts() {
        return polyParts;
    }

    public void setPolyParts(List<PolyPart> polyParts) {
        this.polyParts = polyParts;
    }
    public double eval(int x)
    {
        double res = 0;
        for (var item : polyParts)
        {
            res += item.eval(x);
        }
        return res;
    }

    @Override
    public String toString() {
        return polyParts.stream().map(PolyPart::toString).collect(Collectors.joining(" + "));
    }
}
