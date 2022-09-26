package com.decathlon.katas.progfunc.poker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DealerTest {

    @Mock
    Hand hand1;

    @Mock
    Hand hand2;

    @Test
    public void hand2IsWinner() {
        // Given
        Dealer dealer = new Dealer();
        given(hand1.evaluate()).willReturn(HandValue.FOUR_OF_KIND);
        given(hand2.evaluate()).willReturn(HandValue.STRAIGHT_FLUSH);

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
        given(hand1.evaluate()).willReturn(HandValue.STRAIGHT_FLUSH);
        given(hand2.evaluate()).willReturn(HandValue.FOUR_OF_KIND);

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
        given(hand1.evaluate()).willReturn(HandValue.FOUR_OF_KIND);
        given(hand2.evaluate()).willReturn(HandValue.FOUR_OF_KIND);

        // When
        Optional<Hand> winnerHand = dealer.compare(hand1, hand2);

        // Then
        assertTrue(winnerHand.isEmpty());
    }
}