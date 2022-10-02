package com.decathlon.katas.progfunc.poker.actors;

import com.decathlon.katas.progfunc.poker.Pot;
import com.decathlon.katas.progfunc.poker.hand.Hand;

import java.util.Arrays;
import java.util.Optional;

public class Dealer extends Person {

    /**
     * compare two {@link Hand} and return the strongest
     *
     * @return {@link Optional#empty()} when two {@link Hand} are equal
     */
    public Optional<Hand> compare(Hand hand1, Hand hand2) {
        Double hand1Value = computeHandValue(hand1);
        Double hand2Value = computeHandValue(hand2);

        if(hand1Value > hand2Value) {
            return Optional.of(hand1);
        } else if (hand2Value > hand1Value) {
            return Optional.of(hand2);
        } else {
            return Optional.empty();
        }
    }

    public void dealIn(Pot pot, Player ...players) {
        Arrays.stream(players)
                .forEach(player -> player.setHand(pot.getRandomHand()));
    }
}
