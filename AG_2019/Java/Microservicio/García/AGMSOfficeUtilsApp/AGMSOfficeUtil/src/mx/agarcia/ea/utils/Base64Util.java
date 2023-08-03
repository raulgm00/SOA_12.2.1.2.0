package mx.agarcia.ea.utils;

import java.util.Base64;


import javax.xml.xpath.XPathFunctionException;

public class Base64Util {

    public static String encodeBase64(final String input) throws XPathFunctionException {
        String enc = null;
        try {
            enc = Base64.getEncoder().encodeToString( input.getBytes() );
        } catch (Exception exc) {
            throw new XPathFunctionException("Failed to Base64-encode your input string");
        }
        return enc;
    }


    public static byte[] decodeBase64(final byte[] input) throws Exception {
        byte[]  dec = null;
        try {
            dec =  Base64.getMimeDecoder().decode( input );
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Failed to Base64-decode your input string", exc);
        }
        return dec;
    }
}
