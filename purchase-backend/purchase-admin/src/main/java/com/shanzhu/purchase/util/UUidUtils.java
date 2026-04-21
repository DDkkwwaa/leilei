package com.shanzhu.purchase.util;

import java.util.UUID;

public class UUidUtils {

    /**
     * Generate a stable 9-digit positive id.
     */
    public static Integer uuid() {
        int hash = UUID.randomUUID().toString().replace("-", "").hashCode();
        int positive = Math.abs(hash);
        if (positive < 100000000) {
            positive += 100000000;
        }
        return Integer.valueOf(String.valueOf(positive).substring(0, 9));
    }

    public static void main(String[] args) {
        Integer uuid = uuid();
    }
}
