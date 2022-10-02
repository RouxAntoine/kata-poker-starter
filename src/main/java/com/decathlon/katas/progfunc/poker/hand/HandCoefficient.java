package com.decathlon.katas.progfunc.poker.hand;

import static java.lang.Math.pow;

public enum HandCoefficient {
    // Quinte flush
    STRAIGHT_FLUSH(9),
    // Carré
    FOUR_OF_KIND(8),
    // Full
    FULL_HOUSE(7),
    // Couleur
    FLUSH(6),
    // Suite
    STRAIGHT(5),
    // Brelan
    THREE_OF_A_KIND(4),
    // Double pair
    TWO_PAIR(3),
    // Pair
    PAIR(2),
    // Carte Haute
    HIGH_CARD(1);

    private final int coefficient;

    HandCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public double computeHandValue(double handValue) {
        return pow(10, coefficient) * handValue;
    }
}
