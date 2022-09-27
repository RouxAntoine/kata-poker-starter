package com.decathlon.katas.progfunc.poker.hand.composition;

import com.decathlon.katas.progfunc.poker.Card;
import com.decathlon.katas.progfunc.poker.hand.HandValue;

import java.util.function.Function;

public class StraightFlush implements HandValue {
    @Override
    public Integer getFactor() {
        return 9;
    }

    @Override
    public Function<Card, Integer> computeValue() {
        return (Card card) -> { return card.rank().getTuple().max() * 9; };
    }
}
