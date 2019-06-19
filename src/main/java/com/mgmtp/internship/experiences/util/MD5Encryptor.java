package com.mgmtp.internship.experiences.util;

import com.mgmtp.internship.experiences.exceptions.MD5Exception;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hash password MD5.
 *
 * @author ttkngo
 */
public class MD5Encryptor {
    public static String encrypt(String srcText) throws MD5Exception {
        String enrText;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] srcTextBytes = new byte[0];
            srcTextBytes = srcText.getBytes(StandardCharsets.UTF_8);
            byte[] enrTextBytes = messageDigest.digest(srcTextBytes);
            BigInteger bigInteger = new BigInteger(1, enrTextBytes);
            enrText = bigInteger.toString(16);
            return enrText;
        } catch (Exception e) {
            throw new MD5Exception(e);
        }
    }
}
