package org.intothedeep.ld48.framework.util;

import java.util.Random;

/**
 * Created by aidan on 13/04/14.
 */
public class CommonMath {
    private static Random random;

    /**
     * Get a random number between 2 values
     * @param min the lower bound
     * @param max the upper bound
     * @return random value between upper and lower bounds
     */
    public static float randomInRange(float min, float max) {
        return getRandomInstance().nextFloat() * (max - min) + min;
    }

    public static Random getRandomInstance() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }

}
