package oop.project.hrs.backend;

import java.security.SecureRandom;
public class RandPassGen {
    private static final String CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";
    /* can be much better by creating multiple pools, one for Uppercase, one for lowercase and one for digits and special chars.
     but I'm too lazy to do such thing */
    private static final SecureRandom RANDOM = new SecureRandom();
    private final int minLength;
    public RandPassGen(int minLength) {
        this.minLength = minLength;
        if (minLength < 1) {
            throw new IllegalArgumentException("Value must be at least 1"); //I hope I'm using exceptions right lol
        }
    }
    public String generate() {
        StringBuilder password = new StringBuilder(minLength);
        for (int i = 0; i < minLength; i++) {
            password.append(CHAR.charAt(RANDOM.nextInt(CHAR.length())));
            /* Now my head hurts!
            RANDOM.nextInt(CHAR.length() picks a random number that's less than CHAR.
            CHAR.charAt self-explanatory.
            append() alao self-explanatory.
             */
        }
        return password.toString();
    }
}
