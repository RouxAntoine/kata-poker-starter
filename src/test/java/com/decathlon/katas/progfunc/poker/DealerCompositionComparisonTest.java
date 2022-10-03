package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Dealer;
import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.hand.HandFixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerCompositionComparisonTest {

    private final Dealer dealer = new Dealer();

    @Test
    public void straightFlushVersusFourOfAKind() {
        // Given
        Hand hand1 = HandFixture.STRAIGHT_FLUSH_HAND;
        Hand hand2 = HandFixture.FOUR_OF_A_KIND_HAND;

        // When
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand1);

        // When
        winnersHand = dealer.getWinner(hand2, hand1);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand1);
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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
    }
}
