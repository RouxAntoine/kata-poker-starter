package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.hand.HandFixture;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerCompositionComparisonTest {

    private final Dealer dealer = new Dealer();

    @Test
    public void straightFlushVersusFourOfAKind() {
        // Given
        Hand hand1 = HandFixture.STRAIGHT_FLUSH_HAND;
        Hand hand2 = HandFixture.FOUR_OF_A_KIND_HAND;

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand1);

        // When
        winnerHand = dealer.compare(hand2, hand1);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand1);
    }

    @Test
    public void threeOfAKindVersusTwoPair() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.KING, Color.DIAMOND),
                        new Card(Rank.JACK, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.DIAMOND),
                        new Card(Rank.JACK, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.SEVEN, Color.SPADE),
                        new Card(Rank.KING, Color.HEART)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }

    @Test
    public void threeOfAKindVersusPair() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.KING, Color.DIAMOND),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.DIAMOND),
                        new Card(Rank.JACK, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.SEVEN, Color.SPADE),
                        new Card(Rank.KING, Color.HEART)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }

    @Test
    public void PairVersusFourOfAKind() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.KING, Color.DIAMOND),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.DIAMOND),
                        new Card(Rank.JACK, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.SPADE),
                        new Card(Rank.KING, Color.HEART)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }
}
