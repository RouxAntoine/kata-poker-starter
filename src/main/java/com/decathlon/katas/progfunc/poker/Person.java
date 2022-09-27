package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.jetbrains.annotations.NotNull;

public class Person {

    public static final int TWO_CARD = 2;
    public static final int FIVE_CARD = 5;
    private static final int THREE_CARD = 3;
    private static final int FOUR_CARD = 4;
    private static final int REPEATED_ONE_TIME = 1;
    public static final int REPEATED_TWO_TIME = 2;

    public boolean verifyHasPair(@NotNull Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, TWO_CARD)
                && !this.verifyHasFullHouse(hand);
    }

    public boolean verifyHasThreeOfAKind(@NotNull Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, THREE_CARD)
                && !this.verifyHasFullHouse(hand);
    }

    public boolean verifyHasFourOfAKind(@NotNull Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, FOUR_CARD);
    }

    public boolean verifyHasFullHouse(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_ONE_TIME
                && hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, THREE_CARD);
    }

    public boolean verifyHasTwoPair(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_TWO_TIME;
    }

    public boolean verifyHasColor(@NotNull Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::color, FIVE_CARD)
                && !this.verifyHasStraightFlush(hand);
    }

    public boolean verifyHasStraight(@NotNull Hand hand) {
        return hand.hasNFollowingCard(FIVE_CARD)
                && ! this.verifyHasStraightFlush(hand);
    }

    public boolean verifyHasStraightFlush(@NotNull Hand hand) {
        return hand.hasNFollowingCard(FIVE_CARD) && hand.hasOneTimeNIdenticalCardsByCriteria(Card::color, FIVE_CARD);
    }

    public Integer computeHandValue(@NotNull Hand hand) {
//        if(verifyHasPair(hand)) {
//            Map<Rank, List<Card>> nIdenticalCardGroupBy = hand.getNIdenticalCardGroupBy(TWO_CARD, Card::rank)
//                    .;
//            return pow(10, HandValue.PAIR.getValue()) * nIdenticalCardGroupBy;
//        }
//        else {
            return hand.cards().stream()
                    .mapToInt(value -> value.rank().getTuple().max())
                    .sum();
//        }
    }
}
