package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pot {

    public static final int POKER_DEFAULT_POT_SIZE = 52;
    public static final int POKER_DEFAULT_HAND_SIZE = 5;

    private final int initialPotSize;
    public final ArrayList<Card> cards;
    private final int handSize;
    private final Random r = new Random();

    public Pot() {
        this(POKER_DEFAULT_POT_SIZE, POKER_DEFAULT_HAND_SIZE);
    }

    public Pot(int initialPotSize, int handSize) {
        this.initialPotSize = initialPotSize;
        this.cards = new ArrayList<>(initialPotSize);
        this.handSize = handSize;

        for (Color color : Color.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, color));
            }
        }
    }

    public Hand getRandomHand() {
        List<Card> randomListOfCards = new ArrayList<>(handSize);

        for (int i = 0; i < handSize; i++) {
            Card card = cards.remove(r.nextInt(cards.size()));
            randomListOfCards.add(card);
        }

        return new Hand(randomListOfCards);
    }

    public int getInitialPotSize() {
        return initialPotSize;
    }
}
