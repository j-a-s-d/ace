/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.constants;

import ace.Ace;

/**
 * Class providing digits, factors, fractions and values constants.
 */
public class NUMBERS extends Ace {
    
    // DIGITS
    
    public static final Short S0 = 0;
    public static final Short S1 = 1;
    public static final Short S2 = 2;
    public static final Short S3 = 3;
    public static final Short S4 = 4;
    public static final Short S5 = 5;
    public static final Short S6 = 6;
    public static final Short S7 = 7;
    public static final Short S8 = 8;
    public static final Short S9 = 9;
    
    public static final Integer I0 = 0;
    public static final Integer I1 = 1;
    public static final Integer I2 = 2;
    public static final Integer I3 = 3;
    public static final Integer I4 = 4;
    public static final Integer I5 = 5;
    public static final Integer I6 = 6;
    public static final Integer I7 = 7;
    public static final Integer I8 = 8;
    public static final Integer I9 = 9;
    
    public static final Long L0 = 0L;
    public static final Long L1 = 1L;
    public static final Long L2 = 2L;
    public static final Long L3 = 3L;
    public static final Long L4 = 4L;
    public static final Long L5 = 5L;
    public static final Long L6 = 6L;
    public static final Long L7 = 7L;
    public static final Long L8 = 8L;
    public static final Long L9 = 9L;
    
    public static final Float F0 = 0F;
    public static final Float F1 = 1F;
    public static final Float F2 = 2F;
    public static final Float F3 = 3F;
    public static final Float F4 = 4F;
    public static final Float F5 = 5F;
    public static final Float F6 = 6F;
    public static final Float F7 = 7F;
    public static final Float F8 = 8F;
    public static final Float F9 = 9F;
    
    public static final Double D0 = 0D;
    public static final Double D1 = 1D;
    public static final Double D2 = 2D;
    public static final Double D3 = 3D;
    public static final Double D4 = 4D;
    public static final Double D5 = 5D;
    public static final Double D6 = 6D;
    public static final Double D7 = 7D;
    public static final Double D8 = 8D;
    public static final Double D9 = 9D;
    
    // FACTORS
    
    public static final Integer FACTOR_SINGLE = I1;
    public static final Integer FACTOR_DOUBLE = I2;
    public static final Integer FACTOR_TRIPLE = I3;
    public static final Integer FACTOR_QUADRUPLE = I4;
    public static final Integer FACTOR_QUINTUPLE = I5;
    public static final Integer FACTOR_SEXTUPLE = I6;
    public static final Integer FACTOR_SEPTUPLE = I7;
    public static final Integer FACTOR_OCTUPLE = I8;
    public static final Integer FACTOR_NONUPLE = I9;
    public static final Integer FACTOR_DECUPLE = 10;
    public static final Integer FACTOR_CENTUPLE = FACTOR_DECUPLE * FACTOR_DECUPLE;
    public static final Integer FACTOR_MILLIDRUPLE = FACTOR_CENTUPLE * FACTOR_DECUPLE;
    public static final Integer FACTOR_MEGADRUPLE = FACTOR_MILLIDRUPLE * FACTOR_MILLIDRUPLE;
    public static final Integer FACTOR_GIGADRUPLE = FACTOR_MEGADRUPLE * FACTOR_MILLIDRUPLE;
    
    // FRACTIONS
    
    public static final Float FRACTION_HALF = F1 / F2;
    public static final Float FRACTION_THIRD = F1 / F3;
    public static final Float FRACTION_FOURTH = F1 / F4;
    public static final Float FRACTION_FIFTH = F1 / F5;
    public static final Float FRACTION_SIXTH = F1 / F6;
    public static final Float FRACTION_SEVENTH = F1 / F7;
    public static final Float FRACTION_EIGTH = F1 / F8;
    public static final Float FRACTION_NINTH = F1 / F9;
    public static final Float FRACTION_TENTH = 0.1f;
    public static final Float FRACTION_HUNDREDTH = 0.01f;
    public static final Float FRACTION_THOUSANDTH = 0.001f;
    
    // VALUES
    
    public static final Double VALUE_E = Math.E;
    public static final Double VALUE_LOG10E = Math.log10(Math.E);
    public static final Double VALUE_PI = Math.PI;
    public static final Double VALUE_PIDIV2 = Math.PI / D2;
    public static final Double VALUE_PIDIV4 = Math.PI / D4;
    public static final Double VALUE_1DIVPI = D1 / Math.PI;
    public static final Double VALUE_2DIVPI = D2 / Math.PI;
    public static final Double VALUE_2SQRTPI = D2 / Math.sqrt(Math.PI);
    public static final Double VALUE_SQRT2 = Math.sqrt(D2);
    public static final Double VALUE_1DIVSQRT2 = D1 / VALUE_SQRT2;
    public static final Double VALUE_PHI = 1.618033988749894D;
    
}
