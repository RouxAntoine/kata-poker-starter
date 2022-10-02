package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static com.decathlon.katas.progfunc.poker.Pot.POKER_DEFAULT_HAND_SIZE;
import static com.decathlon.katas.progfunc.poker.Pot.POKER_DEFAULT_POT_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class PotTest {

    private final Pot pot = new Pot(POKER_DEFAULT_POT_SIZE, POKER_DEFAULT_HAND_SIZE);

    @Test
    void checkPotIsComplete() {
        // When
        Pot pot = new Pot(POKER_DEFAULT_POT_SIZE, POKER_DEFAULT_HAND_SIZE);

        // Then
        Set<Card> FiftyTwoDistinctCards = Set.copyOf(pot.cards);
        assertEquals(52, FiftyTwoDistinctCards.size());
    }

    @Test
    void getRandomHand() {
        // When
        Hand randomHand = pot.getRandomHand();

        // Then
        assertNotNull(randomHand);
        assertEquals(5, randomHand.cards().size());
        assertEquals(POKER_DEFAULT_POT_SIZE - 5, pot.cards.size());

        // When
        Hand randomHand2 = pot.getRandomHand();

        // Then
        assertNotNull(randomHand2);
        assertEquals(5, randomHand2.cards().size());
        assertNotEquals(randomHand, randomHand2);
        assertEquals(POKER_DEFAULT_POT_SIZE - 2 * 5, pot.cards.size());
    }
}