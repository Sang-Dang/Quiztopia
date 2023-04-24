package com.fpt.assignment.util;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

public class CodeGenerator {
    private CodeGenerator() {}

    private static final int CODE_LENGTH = 6;
    private static final int MAX_CACHE_SIZE = 10;
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();
    private static volatile Queue<String> codeCache = new LinkedList<>();

    public static synchronized String generateCode() {
        String code;
        do {
            code = generateRandomCode();
        } while(codeCache.contains(code));
        codeCache.add(code);
        if(codeCache.size() > MAX_CACHE_SIZE) {
            codeCache.remove();
        }
        return code;
    }

    private static String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(ALLOWED_CHARACTERS.length());
            sb.append(ALLOWED_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; i++) {
            System.out.println(i + ": " + generateCode());
        }
    }
}
