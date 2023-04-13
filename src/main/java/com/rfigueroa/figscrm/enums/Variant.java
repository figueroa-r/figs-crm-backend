package com.rfigueroa.figscrm.enums;

import java.util.Random;

public enum Variant {
    PRIMARY,
    SECONDARY,
    ERROR,
    WARNING,
    INFO,
    SUCCESS;


    private static final Random random = new Random();

    public static Variant randomVariant() {
        Variant[] variants = values();
        return variants[random.nextInt(variants.length)];
    }

    // React/MUI requires variant to be in lowercase form when styling components
    public String toString() { // may not be best practice, use custom variantLower() method instead?
        return name().toLowerCase();
    }
}
