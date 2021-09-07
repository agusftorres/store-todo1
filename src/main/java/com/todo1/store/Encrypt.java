package com.todo1.store;

import java.util.Base64;

public class Encrypt {

    public static String encrypt(byte[] something) {
        return Base64.getEncoder().encodeToString(something);
    }

    public static String decrypt(byte[] something) {
        byte[] somethingDecode = Base64.getDecoder().decode(something);
        return new String(somethingDecode);
    }
}
