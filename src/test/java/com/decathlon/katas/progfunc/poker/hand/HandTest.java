package com.decathlon.katas.progfunc.poker.hand;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.card.criteria.Tuple;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.IconUIResource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        int hasTwoIdenticalCard = hand.howManyNIdenticalCardsByCriteria(Card::rank, 2);

        // Then
        assertEquals(1, hasTwoIdenticalCard);
    }

    @Test
    void hasNFollowingCard() {
        // Given
        Hand hand = HandFixture.STRAIGHT_HAND;

        // When
        boolean hasFiveFollowingCard = hand.hasNFollowingCard(5);

        // Then
        assertTrue(hasFiveFollowingCard);
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

    @Test
    void handContainedAKing() {
        // Given
        Hand handWithoutKing = new Hand(
                List.of(
                        new Card(Rank.TWO, Color.DIAMOND),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE)
                )
        );
        Hand handWithKing = new Hand(
                List.of(
                        new Card(Rank.TWO, Color.DIAMOND),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE)
                )
        );
        // When
        boolean doesHandContainedAKing = handWithoutKing.handContainedAKing();
        // Then
        assertFalse(doesHandContainedAKing);

        // When
        doesHandContainedAKing = handWithKing.handContainedAKing();
        // Then
        assertTrue(doesHandContainedAKing);
    }
}
