package fr.adservio.neobankbackend.utils;

import java.math.BigDecimal;

public class CompareBigDecimalValue {
    private  CompareBigDecimalValue()
    {
        throw new IllegalArgumentException("Utility class");
    }
    public static boolean comparator(BigDecimal firstNum, Operator operator, BigDecimal secondNum) {
        switch (operator) {
            case EQUALS:
                return firstNum.compareTo(secondNum) == 0;
            case LESS_THAN:
                return firstNum.compareTo(secondNum) < 0;
            case LESS_THAN_OR_EQUALS:
                return firstNum.compareTo(secondNum) <= 0;
            case GREATER_THAN:
                return firstNum.compareTo(secondNum) > 0;
            case GREATER_THAN_OR_EQUALS:
                return firstNum.compareTo(secondNum) >= 0;
        }

        throw new IllegalArgumentException("Will never reach here");
    }
}
