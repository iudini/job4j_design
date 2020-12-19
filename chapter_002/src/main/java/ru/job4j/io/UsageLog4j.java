package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte vByte = 1;
        short vShort = 2;
        int vInt = 3;
        long vLong = 4L;
        float vFloat = 5.0F;
        double vDouble = 6.0;
        boolean vBoolean = true;
        char vChar = 'Y';
        LOG.debug("byte is {}, short is {}, int is {}, long is {}, float is {}, double is {}, boolean is {}, char is {}",
                vByte, vShort, vInt, vLong, vFloat, vDouble, vBoolean, vChar);
//        LOG.trace("trace message");
//        LOG.debug("debug message");
//        LOG.info("info message");
//        LOG.warn("warn message");
//        LOG.error("error message");
    }
}
