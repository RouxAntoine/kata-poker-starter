package com.decathlon.katas.progfunc.poker;

import java.util.List;

public class Person {

    public static final int TWO_CARD = 2;
    public static final int FIVE_CARD = 5;
    private static final int THREE_CARD = 3;
    private static final int FOUR_CARD = 4;
    public static final int REPEATED_TWO_TIME = 2;

    // Pair
    public boolean verifyHasPair(Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, TWO_CARD);
    }

    // Brelan
    public boolean verifyHasThreeOfAKind(Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, THREE_CARD);
    }

    // Carré
    public boolean verifyHasFourOfAKind(Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, FOUR_CARD);
    }

    // Full
    public boolean verifyHasFullHouse(Hand hand) {
        return verifyHasPair(hand) && verifyHasThreeOfAKind(hand);
    }

    // Double pair
    public boolean verifyHasTwoPair(Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_TWO_TIME;
    }

    // Flush
    public boolean verifyHasColor(Hand hand) {
        return hand.hasOneTimeNIdenticalCardsByCriteria(Card::color, FIVE_CARD);
    }

    // Suite
    public boolean verifyHasStraight(Hand hand) {
        List<Card> cardsSortedByMin = hand.sortByMinRank();
        int diffMin = cardsSortedByMin.get(cardsSortedByMin.size() -1 ).rank().getTuple().min() - cardsSortedByMin.get(0).rank().getTuple().min();

        List<Card> cardsSortedByMax = hand.sortByMaxRank();
        int diffMax = cardsSortedByMax.get(cardsSortedByMax.size() - 1).rank().getTuple().max() - cardsSortedByMax.get(0).rank().getTuple().max();

        return diffMin == FOUR_CARD || diffMax == FOUR_CARD;
    }

    // Quinte flush
    public boolean verifyHasStraightFlush(Hand hand) {
        return verifyHasStraight(hand) && verifyHasColor(hand);
    }
}
