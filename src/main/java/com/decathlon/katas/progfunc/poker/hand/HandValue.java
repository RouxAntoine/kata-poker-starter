package com.decathlon.katas.progfunc.poker.hand;

public enum HandValue {
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

    private final int value;

    HandValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
