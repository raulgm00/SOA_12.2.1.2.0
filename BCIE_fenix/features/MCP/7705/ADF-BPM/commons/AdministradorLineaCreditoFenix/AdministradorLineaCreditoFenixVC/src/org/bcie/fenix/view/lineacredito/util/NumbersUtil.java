package org.bcie.fenix.view.lineacredito.util;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Clase para utilerias de n&uacute;s
 * @author Jonathan Ruiz
 */
public class NumbersUtil {

    /**
     *M&eacute;todo para convertir una cadena de texto a BigDecimal
     * @param patternFormat patron de formato
     * @param sNumber n&uacute;mero con formato
     * @return n&uacute;mero como BigDecimal
     * @throws ParseException
     */
    public static BigDecimal stringToBigDecimal(String patternFormat, String sNumber) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(Constants.CARACTER_COMA);
        symbols.setDecimalSeparator(Constants.CARACTER_PUNTO);
        DecimalFormat decimalFormat = new DecimalFormat(); //(patternFormat, symbols);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal bdNumber = (BigDecimal) decimalFormat.parse(sNumber);
        return bdNumber;
    }
}
