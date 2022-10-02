package com.decathlon.katas.progfunc.poker.pot;

public non-sealed class PokerPot extends Pot {

    public static final int POKER_DEFAULT_POT_SIZE = 52;
    public static final int POKER_DEFAULT_HAND_SIZE = 5;

    public PokerPot() {
        super(POKER_DEFAULT_POT_SIZE, POKER_DEFAULT_HAND_SIZE);
    }
}
