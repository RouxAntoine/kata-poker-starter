package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

class DealerTest {

    @Test
    public void hand2IsWinner() {
        // Given
        Dealer dealer = new Dealer();
        Hand hand1 = HandFixture.FOUR_OF_A_KIND_HAND;
        Hand hand2 = HandFixture.STRAIGHT_FLUSH_HAND;

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand2);
    }

    @Test
    public void hand1IsWinner() {
        // Given
        Dealer dealer = new Dealer();
        Hand hand1 = HandFixture.STRAIGHT_FLUSH_HAND;
        Hand hand2 = HandFixture.FOUR_OF_A_KIND_HAND;

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isPresent());
        assertEquals(winnerHand.get(), hand1);
    }

    @Test
    public void handsIsExEquo() {
        // Given
        Dealer dealer = new Dealer();
        Hand hand1 = HandFixture.FOUR_OF_A_KIND_HAND;

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand1);

        // Then
        assertTrue(winnerHand.isEmpty());
    }
}