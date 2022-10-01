package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Person {

    public static final int TWO_CARD = 2;
    public static final int FIVE_CARD = 5;
    private static final int THREE_CARD = 3;
    private static final int FOUR_CARD = 4;
    private static final int REPEATED_ONE_TIME = 1;
    public static final int REPEATED_TWO_TIME = 2;

    public Optional<Double> computeHasPair(@NotNull Hand hand) {
        if (hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, TWO_CARD) && this.computeHasFullHouse(hand).isEmpty()) {
            return Optional.of( pow(10, HandValue.PAIR.getValue()) * hand.value(Tuple::max));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasThreeOfAKind(@NotNull Hand hand) {
        if(hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, THREE_CARD)
                && this.computeHasFullHouse(hand).isEmpty()) {
            return Optional.of(pow(10, HandValue.THREE_OF_A_KIND.getValue()) * hand.value(Tuple::max));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasFourOfAKind(@NotNull Hand hand) {
        if(hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, FOUR_CARD)) {
            return Optional.of( pow(10, HandValue.FOUR_OF_KIND.getValue()) * hand.value(Tuple::max));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasFullHouse(@NotNull Hand hand) {
        if(hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_ONE_TIME
                && hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, THREE_CARD)) {
            return Optional.of( pow(10, HandValue.FULL_HOUSE.getValue()) * hand.value(Tuple::max));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasTwoPair(@NotNull Hand hand) {
        if (hand.howManyNIdenticalCardsByCriteria(Card::rank, TWO_CARD) == REPEATED_TWO_TIME) {
            return Optional.of(pow(10, HandValue.TWO_PAIR.getValue()) * hand.value(Tuple::max));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasColor(@NotNull Hand hand) {
        if(hand.hasOneTimeNIdenticalCardsByCriteria(Card::color, FIVE_CARD)
                && this.computeHasStraightFlush(hand).isEmpty()) {
            return Optional.of(pow(10, HandValue.FLUSH.getValue()) * hand.value(Tuple::max));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasStraight(@NotNull Hand hand) {
        if(hand.hasNFollowingCard(FIVE_CARD)
                && this.computeHasStraightFlush(hand).isEmpty()) {
            if(hand.cards().stream().anyMatch(card -> card.rank().equals(Rank.KING))) {
                return Optional.of(pow(10, HandValue.STRAIGHT.getValue()) * hand.value(Tuple::max));
            } else {
                return Optional.of(pow(10, HandValue.STRAIGHT.getValue()) * hand.value(Tuple::min));
            }
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Double> computeHasStraightFlush(@NotNull Hand hand) {
        if(hand.hasNFollowingCard(FIVE_CARD) && hand.hasOneTimeNIdenticalCardsByCriteria(Card::color, FIVE_CARD)) {
            if(hand.cards().stream().anyMatch(card -> card.rank().equals(Rank.KING))) {
                return Optional.of(pow(10, HandValue.STRAIGHT_FLUSH.getValue()) * hand.value(Tuple::max));
            }
            else {
                return Optional.of(pow(10, HandValue.STRAIGHT_FLUSH.getValue()) * hand.value(Tuple::min));
            }
        }
        else {
            return Optional.empty();
        }
    }

    public Double computeSimpleHandValue(@NotNull Hand hand) {
        return hand.value(Tuple::max);
    }

    public Double computeHandValue(Hand hand) {
        return Stream.of(
                        computeHasStraightFlush(hand),
                        computeHasColor(hand),
                        computeHasStraight(hand),
                        computeHasFourOfAKind(hand),
                        computeHasPair(hand),
                        computeHasTwoPair(hand),
                        computeHasFullHouse(hand),
                        computeHasThreeOfAKind(hand)
                )
                .flatMap(Optional::stream)
                .reduce(Double::sum)
                .orElseGet(() -> computeSimpleHandValue(hand));
    }
}
