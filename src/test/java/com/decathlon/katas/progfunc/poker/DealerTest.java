package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Dealer;
import com.decathlon.katas.progfunc.poker.actors.Player;
import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.pot.PokerPot;
import com.decathlon.katas.progfunc.poker.pot.Pot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.decathlon.katas.progfunc.poker.hand.HandFixture.*;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private final Dealer dealer = new Dealer();
    private final Pot pot = new PokerPot();

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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand1);
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
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
    }

    @Test
    public void threeHandComparison() {
        // Given
        Hand hand1 = COLOR_HAND;
        Hand hand2 = FOUR_OF_A_KIND_HAND;
        Hand hand3 = FULL_HAND;

        // When
        List<Hand> winnersHand = dealer.getWinner(hand1, hand2, hand3);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(winnersHand.get(0), hand2);
    }

    @Test
    public void handsAreIdentical() {
        // Given
        Hand hand1 = FOUR_OF_A_KIND_HAND;

        // When
        List<Hand> winnersHand = dealer.getWinner(hand1, hand1);

        // Then
        assertFalse(winnersHand.isEmpty());
        assertEquals(2, winnersHand.size());
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
        assertEquals(5, player1.getHand().cards().size());
        assertNotNull(player2.getHand());
        assertEquals(5, player2.getHand().cards().size());
        assertNotNull(player3.getHand());
        assertEquals(5, player3.getHand().cards().size());

        assertNotEquals(player1.getHand().cards(), player2.getHand().cards());
        assertNotEquals(player1.getHand().cards(), player3.getHand().cards());
        assertNotEquals(player2.getHand().cards(), player3.getHand().cards());
    }
}