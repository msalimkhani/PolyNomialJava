package com.salimkhani.polynomial.Presintation;

import com.salimkhani.polynomial.application.interfaces.IPolyHelper;
import com.salimkhani.polynomial.domain.PolyNomial;
import com.salimkhani.polynomial.infrastructure.services.PolyHelper;

public class Program
{
    public static void main(String[] args) throws Exception {
        IPolyHelper helper = new PolyHelper();
        PolyNomial poly = helper.ExtractFromString("x^2+2x^3-2");
        PolyNomial poly2 = helper.ExtractFromString("3x^2+2x-6");
        System.out.println(poly);
        System.out.println(poly2);
        PolyNomial added = helper.AddTwoPolyNomial(poly, poly2);
        System.out.println(added);

        PolyNomial subed = helper.SubtractTwoPolyNomial(poly, poly2);
        System.out.println(subed);
    }
}
