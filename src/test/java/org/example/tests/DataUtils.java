package org.example.tests;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataUtils {

    public static String generate(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();

        return IntStream.range(0, length)
                .map(i -> random.nextInt(chars.length()))
                .mapToObj(index -> String.valueOf(chars.charAt(index)))
                .collect(Collectors.joining());
    }
}
