package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Dealer;
import com.decathlon.katas.progfunc.poker.actors.Player;
import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.decathlon.katas.progfunc.poker.hand.HandFixture.FOUR_OF_A_KIND_HAND;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private final Dealer dealer = new Dealer();
    private final Pot pot = new Pot();

    @Test
    public void compositionStraightFlushWinnerWithHighestHandValue() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.TWO, Color.TREFLE),
                        new Card(Rank.THREE, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.FIVE, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.JACK, Color.TREFLE),
                        new Card(Rank.TEN, Color.TREFLE)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }

    @Test
    public void compositionTwoPairWinnerWithHighestHandValue() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.ACE, Color.SPADE),
                        new Card(Rank.KING, Color.TREFLE),
                        new Card(Rank.KING, Color.HEART),
                        new Card(Rank.QUEEN, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.HEART),
                        new Card(Rank.ACE, Color.DIAMOND),
                        new Card(Rank.KING, Color.DIAMOND),
                        new Card(Rank.KING, Color.SPADE),
                        new Card(Rank.JACK, Color.HEART)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand1);
    }

    @Test
    public void noCompositionHighestHandWin() {
        // Given
        Hand hand1 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.SPADE),
                        new Card(Rank.KING, Color.HEART),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.FOUR, Color.TREFLE),
                        new Card(Rank.THREE, Color.TREFLE)
                )
        );
        Hand hand2 = new Hand(
                List.of(
                        new Card(Rank.ACE, Color.HEART),
                        new Card(Rank.KING, Color.DIAMOND),
                        new Card(Rank.JACK, Color.HEART),
                        new Card(Rank.SIX, Color.SPADE),
                        new Card(Rank.FIVE, Color.DIAMOND)
                )
        );

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }

    @Test
    public void handsIsIdentical() {
        // Given
        Hand hand1 = FOUR_OF_A_KIND_HAND;

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand1);

        // Then
        assertTrue(winnerHand.isEmpty());
    }

    @Test
    public void dealInCards() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        // When
        dealer.dealIn(pot, player1, player2, player3);

        // Then
        assertNotNull(player1.getHand());
        assertNotNull(player2.getHand());
        assertNotNull(player3.getHand());
    }
}