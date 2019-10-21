package com.yard.stockmanager.useful;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encoding {
    public static String encodeToMD5(String val) {
        String result = "";

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(val.getBytes(), 0, val.length());
            result = new BigInteger(1, m.digest()).toString(16);
        }

        catch (Exception e) {
            Error.messageAndLog("Error ao codificar String");
        }

        return result;
    }
}