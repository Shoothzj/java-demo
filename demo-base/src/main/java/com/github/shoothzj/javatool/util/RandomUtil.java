package com.github.shoothzj.javatool.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static<E> E chooseOne(List<? extends E> list) {
        return list.get(nextInt(list.size()));
    }

    public byte[] getSecRanByte(int len) {
        byte[] bytes = new byte[len];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

}
