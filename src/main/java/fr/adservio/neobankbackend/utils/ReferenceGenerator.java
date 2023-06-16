package fr.adservio.neobankbackend.utils;
import java.util.Random;

public class ReferenceGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int REFERENCE_LENGTH = 10;

    private ReferenceGenerator()
    {
        throw new IllegalStateException("Utility class");
    }
    public static String generateReference() {
        Random random = new Random();
        StringBuilder reference = new StringBuilder(REFERENCE_LENGTH);
        for (int i = 0; i < REFERENCE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            reference.append(randomChar);
        }
        return reference.toString();
    }

}