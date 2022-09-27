package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;

import java.util.Comparator;
import java.util.Optional;

public class Dealer extends Person {

    /**
     * compare two {@link Hand} and return the strongest
     *
     * @return {@link Optional#empty()} when two {@link Hand} are equal
     */
    public Optional<Hand> compare(Hand hand1, Hand hand2) {
        HandValue hand1Value = hand1.evaluate();
        HandValue hand2Value = hand2.evaluate();

        int handComparisonResult = Comparator
                .comparing(HandValue::getValue)
                .compare(hand1Value, hand2Value);
        if(handComparisonResult < 0) {
            return Optional.of(hand2);
        } else if (handComparisonResult > 0) {
            return Optional.of(hand1);
        } else {
            // TODO: compare hand value
            return Optional.empty();
        }
    }
}
