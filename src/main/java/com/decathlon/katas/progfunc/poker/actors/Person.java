package com.decathlon.katas.progfunc.poker.actors;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Tuple;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

import static com.decathlon.katas.progfunc.poker.hand.HandCoefficient.*;

public class Person {

    public static final int TWO_CARD = 2;
    private static final int THREE_CARD = 3;
    private static final int FOUR_CARD = 4;
    public static final int FIVE_CARD = 5;

    public static final int REPEATED_ONE_TIME = 1;
    public static final int REPEATED_TWO_TIME = 2;

    private boolean isPair(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_ONE_TIME;
    }

    private boolean isTwoPair(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_TWO_TIME;
    }

    private boolean isThreeOfAKind(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, THREE_CARD) == REPEATED_ONE_TIME;
    }

    private boolean isFourOfAKind(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::rank, FOUR_CARD) == REPEATED_ONE_TIME;
    }

    private boolean isColor(@NotNull Hand hand) {
        return hand.howManyNIdenticalCardsByCriteria(Card::color, FIVE_CARD) == REPEATED_ONE_TIME;
    }

    private boolean isStraight(@NotNull Hand hand) {
        return hand.hasNFollowingCard(FIVE_CARD);
    }

    private boolean isFullOfHouse(@NotNull Hand hand) {
        return isPair(hand) && isThreeOfAKind(hand);
    }

    private boolean isStraightFlush(@NotNull Hand hand) {
        return isStraight(hand) && isColor(hand);
    }

    public Optional<Double> computeAPair(@NotNull Hand hand) {
        if (isPair(hand) && !isFullOfHouse(hand)) {
            return Optional.of(PAIR.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAThreeOfAKind(@NotNull Hand hand) {
        if (isThreeOfAKind(hand) && !isFullOfHouse(hand)) {
            return Optional.of(THREE_OF_A_KIND.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAFourOfAKind(@NotNull Hand hand) {
        if (isFourOfAKind(hand)) {
            return Optional.of(FOUR_OF_KIND.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAFullHouse(@NotNull Hand hand) {
        if (isFullOfHouse(hand)) {
            return Optional.of(FULL_HOUSE.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeATwoPair(@NotNull Hand hand) {
        if (isTwoPair(hand)) {
            return Optional.of(TWO_PAIR.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAColor(@NotNull Hand hand) {
        if (isColor(hand) && !isStraightFlush(hand)) {
            return Optional.of(FLUSH.computeHandValue(hand.value(Tuple::max)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAStraight(@NotNull Hand hand) {
        if (isStraight(hand) && !isStraightFlush(hand)) {
            if (hand.handContainedAKing()) {
                return Optional.of(STRAIGHT.computeHandValue(hand.value(Tuple::max)));
            }
            return Optional.of(STRAIGHT.computeHandValue(hand.value(Tuple::min)));
        }
        return Optional.empty();
    }

    public Optional<Double> computeAStraightFlush(@NotNull Hand hand) {
        if (isStraightFlush(hand)) {
            if (hand.handContainedAKing()) {
                return Optional.of(STRAIGHT_FLUSH.computeHandValue(hand.value(Tuple::max)));
            }
            return Optional.of(STRAIGHT_FLUSH.computeHandValue(hand.value(Tuple::min)));
        }
        return Optional.empty();
    }

    public Double computeSimpleHandValue(@NotNull Hand hand) {
        return HIGH_CARD.computeHandValue(hand.value(Tuple::max));
    }

    public Double computeHandValue(@NotNull Hand hand) {
        return Stream.of(
                        computeAStraightFlush(hand),
                        computeAColor(hand),
                        computeAStraight(hand),
                        computeAFourOfAKind(hand),
                        computeAPair(hand),
                        computeATwoPair(hand),
                        computeAFullHouse(hand),
                        computeAThreeOfAKind(hand)
                )
                .flatMap(Optional::stream)
                .reduce(Double::sum)
                .orElseGet(() -> computeSimpleHandValue(hand));
    }
}
