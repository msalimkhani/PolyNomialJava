package com.salimkhani.polynomial.application.interfaces;

import com.salimkhani.polynomial.domain.PolyNomial;

import java.util.List;

public interface IPolyHelper
{
    PolyNomial ExtractFromString(String polyString) throws Exception;
    PolyNomial AddTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception;
    PolyNomial SubtractTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception;
    PolyNomial MultiplyTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception;
    PolyNomial DivideTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception;
}
