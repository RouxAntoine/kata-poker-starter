package com.decathlon.katas.progfunc.poker.hand;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Criteria;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.card.criteria.Tuple;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.stream.Collectors.groupingBy;

/**
 * hand of five cards
 */
public record Hand(List<Card> cards) {

    public Hand {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("invalid cards passed to hand creation");
        }
    }

    public int howManyNIdenticalCardsByCriteria(Function<Card, Criteria> criteriaFunction, int numberOfCards) {
        return (int) cards()
                .stream()
                .collect(groupingBy(criteriaFunction))
                .values()
                .stream()
                .filter(cards -> cards.size() == numberOfCards)
                .count();
    }

    private List<Card> sortCardBy(ToIntFunction<Card> comparatorFunction) {
        return cards.stream().sorted(Comparator.comparingInt(comparatorFunction)).toList();
    }

    public boolean hasNFollowingCard(int numberOfFollowingCard) {
        List<Card> cardsSortedByMin = sortCardBy(card -> card.rank().getTuple().min());
        int diffMin = cardsSortedByMin.get(cardsSortedByMin.size() - 1).rank().getTuple().min() - cardsSortedByMin.get(0).rank().getTuple().min();

        List<Card> cardsSortedByMax = sortCardBy(o -> o.rank().getTuple().max());
        int diffMax = cardsSortedByMax.get(cardsSortedByMax.size() - 1).rank().getTuple().max() - cardsSortedByMax.get(0).rank().getTuple().max();

        return (diffMin == numberOfFollowingCard - 1 || diffMax == numberOfFollowingCard - 1);
    }

    public double value(ToIntFunction<Tuple> valueFunction) {
        return cards.stream()
                .map(card -> card.rank().getTuple())
                .mapToInt(valueFunction)
                .mapToDouble(Double::valueOf)
                .sum();
    }

    public boolean handContainedAKing() {
        return cards().stream().anyMatch(card -> card.rank().equals(Rank.KING));
    }
}
