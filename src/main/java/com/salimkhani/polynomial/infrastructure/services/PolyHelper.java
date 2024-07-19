package com.salimkhani.polynomial.infrastructure.services;

import com.salimkhani.polynomial.application.interfaces.IPolyHelper;
import com.salimkhani.polynomial.domain.PolyNomial;
import com.salimkhani.polynomial.domain.PolyPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PolyHelper implements IPolyHelper {
    @Override
    public PolyNomial ExtractFromString(String polyString) throws Exception {
        List<PolyPart> parts = new ArrayList<>();
        String formatted = polyString
                .toLowerCase(Locale.ROOT)
                .replace("+", "@+")
                .replace("-", "@-");
        String[] splitted = formatted.split("@", -2);
        for (var item : splitted)
        {
            boolean powState = item.contains("^");
            boolean xState = item.contains("x");
            if(powState && xState)
            {
                String[] sp = item.replace("@", "").split("\\^", -2);
                String sp0 = sp[0].replace("x", "");
                if(sp0.equals("+") || sp0.equals("-") || sp0.isEmpty())
                {
                    sp0 += "1";
                }
                var part = PolyPart.New(Double.parseDouble(sp0), Integer.parseInt(sp[1]));
                parts.add(part);
            }
            if(!powState && xState)
            {
                String sp = item.replace("@", "");
                String sp0 = sp.replace("x", "");
                if(sp0.equals("+") || sp0.equals("-") || sp0.isEmpty())
                {
                    sp0 += "1";
                }
                var part = PolyPart.New(Double.parseDouble(sp0), 1);
                parts.add(part);
            }
            if(!powState && !xState)
            {
                String sp = item.replace("@", "");
                var part = PolyPart.New(Double.parseDouble(sp), 0);
                parts.add(part);
            }
        }
        return PolyNomial.New(parts);
    }

    private List<PolyPart> simplify(List<PolyPart> terms) throws Exception {
        terms.sort((a, b) -> b.getPower() - a.getPower());
        List<PolyPart> simplified = new ArrayList<>();
        for (var part : terms)
        {
            if(!simplified.isEmpty() &&
                    simplified.getLast().getPower() == part.getPower())
            {
                simplified.getLast().setCoeff(simplified.getLast().getCoeff() + part.getCoeff());
            }
            else
            {
                simplified.add(part);
            }
        }
        return simplified;
    }

    @Override
    public PolyNomial AddTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception {
        var aParts = a.getPolyParts();
        var bParts = b.getPolyParts();
        var resParts = new ArrayList<PolyPart>(aParts);
        resParts.addAll(bParts);

        return new PolyNomial(simplify(resParts));
    }

    @Override
    public PolyNomial SubtractTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception {
        List<PolyPart> resParts = new ArrayList<>(a.getPolyParts());
        for (var part : b.getPolyParts())
        {
            resParts.add(PolyPart.New(-part.getCoeff(), part.getPower()));
        }
        return PolyNomial.New(simplify(resParts));
    }

    @Override
    public PolyNomial MultiplyTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception {
        List<PolyPart> resParts = new ArrayList<>();
        for(PolyPart aPart : a.getPolyParts())
        {
            for (PolyPart bPart : b.getPolyParts())
            {
                resParts.add(PolyPart.New(aPart.getCoeff() * bPart.getCoeff(), aPart.getPower() + bPart.getPower()));
            }
        }
        return PolyNomial.New(simplify(resParts));
    }

    @Override
    public PolyNomial DivideTwoPolyNomial(PolyNomial a, PolyNomial b) throws Exception {
        List<PolyPart> quotient = new ArrayList<>();

        List<PolyPart> remainder = new ArrayList<>(a.getPolyParts());

        while (!remainder.isEmpty() && remainder.getFirst().getPower() >= b.getPolyParts().getFirst().getPower())
        {
            int powerDiff = remainder.getFirst().getPower() - b.getPolyParts().getFirst().getPower();
            double coeffQuotient = remainder.getFirst().getCoeff() / b.getPolyParts().getFirst().getCoeff();
            var partQuotient = PolyPart.New(coeffQuotient, powerDiff);
            quotient.add(partQuotient);

            var temp = new ArrayList<PolyPart>();
            for(var term : b.getPolyParts())
            {
                temp.add(PolyPart.New(term.getCoeff() * coeffQuotient,term.getPower() + powerDiff));
            }
            remainder = SubtractTwoPolyNomial(PolyNomial.New(remainder), PolyNomial.New(temp)).getPolyParts();
        }
        return PolyNomial.New(simplify(quotient));
    }
}
