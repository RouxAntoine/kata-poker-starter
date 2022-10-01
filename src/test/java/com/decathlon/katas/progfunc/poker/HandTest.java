package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

    @Test
    void hasNIdenticalCards() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.SEVEN, Color.SPADE),
                        new Card(Rank.KING, Color.HEART)
                )
        );

        // When
        boolean hasTwoIdenticalCard = hand.hasOneTimeNIdenticalCardsByCriteria(Card::rank, 2);

        // Then
        assertTrue(hasTwoIdenticalCard);
    }

    @Test
    void sortByMinRank() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.TWO, Color.DIAMOND),
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE)
                )
        );
        // When
        List<Card> sortedHand = hand.sortByMinRank();

        // Then
        assertEquals(sortedHand.get(0).rank(), Rank.ACE);
        assertEquals(sortedHand.get(1).rank(), Rank.TWO);
    }

    @Test
    void sortByMaxRank() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.TWO, Color.DIAMOND),
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE)
                )
        );
        // When
        List<Card> sortedHand = hand.sortByMaxRank();

        // Then
        assertEquals(sortedHand.get(0).rank(), Rank.TWO);
        assertEquals(sortedHand.get(4).rank(), Rank.ACE);
    }

    @Test
    void computeHandValue() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.TWO, Color.DIAMOND),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE)
                )
        );

        // When
        double value = hand.value(Tuple::max);

        // Then
        assertEquals(20, value);
    }
}
