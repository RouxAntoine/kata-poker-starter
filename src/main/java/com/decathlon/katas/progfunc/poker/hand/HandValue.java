package com.decathlon.katas.progfunc.poker.hand;

import com.decathlon.katas.progfunc.poker.Card;

import java.util.function.Function;

public interface HandValue {

    Integer getFactor();

    Function<Card, Integer> computeValue();

}
