package com.decathlon.katas.progfunc.poker.actors;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.pot.Pot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dealer extends Person {

    /**
     * compare multiple {@link Hand} and return the strongest
     *
     * @return winner hand, could be multiple in case of equality
     */
    public List<Hand> getWinner(Hand... hands) {
        return Arrays.stream(hands)
                .collect(
                        Collectors.toMap(
                                this::computeHandValue,
                                List::of,
                                (hands1, hands2) -> Stream.concat(hands1.stream(), hands2.stream()).toList())
                )
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    public void dealIn(Pot pot, Player... players) {
        Arrays.stream(players)
                .forEach(player -> player.setHand(pot.getRandomHand()));
    }
}
