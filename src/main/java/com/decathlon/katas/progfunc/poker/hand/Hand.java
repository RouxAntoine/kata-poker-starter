package com.decathlon.katas.progfunc.poker.hand;

import com.decathlon.katas.progfunc.poker.Card;
import com.decathlon.katas.progfunc.poker.Criteria;
import com.decathlon.katas.progfunc.poker.HandValue;
import com.decathlon.katas.progfunc.poker.Tuple;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.stream.Collectors.groupingBy;

/**
 * hand of cards (5)
 * use this as you want, refactor it as often as needed
 *
 * @author deadbrain
 */
public record Hand(List<Card> cards) {

    public static final int REPEATED_ONE_TIME = 1;

    public Hand {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("invalid cards passed to hand creation");
        }
    }

    public boolean hasOneTimeNIdenticalCardsByCriteria(Function<Card, Criteria> criteriaFunction, int numberOfCards) {
        return howManyNIdenticalCardsByCriteria(criteriaFunction, numberOfCards) == REPEATED_ONE_TIME;
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

    public List<Card> sortByMinRank() {
        return sortBy(o -> o.rank().getTuple().min());
    }

    public List<Card> sortByMaxRank() {
        return sortBy(o -> o.rank().getTuple().max());
    }

    private List<Card> sortBy(ToIntFunction<Card> comparatorFunction) {
        return cards.stream().sorted(Comparator.comparingInt(comparatorFunction)).toList();
    }

    public boolean hasNFollowingCard(int numberOfFollowingCard) {
        List<Card> cardsSortedByMin = sortByMinRank();
        int diffMin = cardsSortedByMin.get(cardsSortedByMin.size() - 1).rank().getTuple().min() - cardsSortedByMin.get(0).rank().getTuple().min();

        List<Card> cardsSortedByMax = sortByMaxRank();
        int diffMax = cardsSortedByMax.get(cardsSortedByMax.size() - 1).rank().getTuple().max() - cardsSortedByMax.get(0).rank().getTuple().max();

        return (diffMin == numberOfFollowingCard - 1 || diffMax == numberOfFollowingCard - 1);
    }

    public int value(ToIntFunction<Tuple> valueFunction) {
        return cards.stream()
                .map(card -> card.rank().getTuple())
                .mapToInt(valueFunction)
                .sum();
    }

    public HandValue evaluate() {
        return null;
    }
}
