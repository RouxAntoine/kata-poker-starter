package com.decathlon.katas.progfunc.poker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.decathlon.katas.progfunc.poker.HandFixture.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    private final Dealer dealer;

    public PersonTest() {
        this.dealer = new Dealer();
    }

    @Test
    @DisplayName("Assert that a hand is invalid without five card")
    public void isHandInvalidWithoutFiveCards() {
        assertThrows(IllegalArgumentException.class, () -> {
            Hand invalidHand = new Hand(List.of(
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.FOUR, Color.DIAMOND)
            ));
        });
    }

    @Test
    @DisplayName("Assert that a pair is well formed")
    public void isHandAPair() {
        assertTrue(dealer.verifyHasPair(PAIR_HAND));
    }

    @Test
    @DisplayName("Assert that a non pair is well formed")
    public void isHandNotAPair() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FOUR, Color.DIAMOND),
                new Card(Rank.TEN, Color.DIAMOND),
                new Card(Rank.SEVEN, Color.SPADE),
                new Card(Rank.KING, Color.HEART)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasPair(hand));
    }

    @Test
    @DisplayName("Assert hand contain a three of a kind")
    public void isHandAThreeOfAKind() {
        assertTrue(dealer.verifyHasThreeOfAKind(THREE_OF_A_KIND_HAND));
        assertFalse(dealer.verifyHasPair(THREE_OF_A_KIND_HAND));
    }

    @Test
    @DisplayName("Assert hand doesn't contain a three of a kind")
    public void isHandNotAThreeOfAKind() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FOUR, Color.DIAMOND),
                new Card(Rank.FIVE, Color.DIAMOND),
                new Card(Rank.SEVEN, Color.SPADE),
                new Card(Rank.KING, Color.HEART)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Assert hand contain a four of a kind")
    public void isHandAFourOfAKind() {
        assertTrue(dealer.verifyHasFourOfAKind(FOUR_OF_A_KIND_HAND));
        assertFalse(dealer.verifyHasThreeOfAKind(FOUR_OF_A_KIND_HAND));
        assertFalse(dealer.verifyHasPair(FOUR_OF_A_KIND_HAND));
    }

    @Test
    @DisplayName("Assert hand doesn't contain a four of a kind")
    public void isHandNotAFourOfAKind() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FOUR, Color.DIAMOND),
                new Card(Rank.FIVE, Color.DIAMOND),
                new Card(Rank.SEVEN, Color.SPADE),
                new Card(Rank.KING, Color.HEART)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasFourOfAKind(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a full house")
    public void isHandAFullHouse() {
        assertTrue(dealer.verifyHasFullHouse(FULL_HAND));
    }

    @Test
    @DisplayName("Assert that a hand is not a full house")
    public void isHandNotAFullHouse() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FIVE, Color.DIAMOND),
                new Card(Rank.FOUR, Color.DIAMOND),
                new Card(Rank.KING, Color.SPADE),
                new Card(Rank.FOUR, Color.HEART)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasFullHouse(hand));
    }

    @Test
    @DisplayName("Assert that a hand contain two pair")
    public void isHandTwoPair() {
        assertTrue(dealer.verifyHasTwoPair(TWO_PAIR_HAND));
    }

    @Test
    @DisplayName("Assert that a hand does not contain two pair")
    public void isHandNotTwoPair() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FIVE, Color.DIAMOND),
                new Card(Rank.FOUR, Color.DIAMOND),
                new Card(Rank.JACK, Color.HEART),
                new Card(Rank.KING, Color.SPADE)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasTwoPair(hand));
    }

    @Test
    @DisplayName("Assert that a hand does not contain two pair")
    public void isHandTwoIdenticalPair() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.FIVE, Color.DIAMOND),
                new Card(Rank.THREE, Color.DIAMOND)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasTwoPair(hand));
    }

    @Test
    @DisplayName("Assert that a hand contain a color")
    public void isHandAColor() {
        assertTrue(dealer.verifyHasColor(COLOR_HAND));
        assertFalse(dealer.verifyHasStraightFlush(COLOR_HAND));
    }

    @Test
    @DisplayName("Assert that a hand does not contain a color")
    public void isHandNotAColor() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.KING, Color.TREFLE),
                new Card(Rank.SEVEN, Color.TREFLE),
                new Card(Rank.FIVE, Color.HEART),
                new Card(Rank.THREE, Color.TREFLE)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasColor(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a straight 1,2,3,4,5")
    public void isHandAStraightOnACE() {
        assertTrue(dealer.verifyHasStraight(STRAIGHT_HAND));
        assertFalse(dealer.verifyHasStraightFlush(STRAIGHT_HAND));
    }

    @Test
    @DisplayName("Assert that a hand is a straight 10,J,Q,K,1")
    public void isHandARoyalFlush() {
        assertTrue(dealer.verifyHasStraight(ROYAL_FLUSH_HAND));
        assertFalse(dealer.verifyHasStraightFlush(ROYAL_FLUSH_HAND));
    }

    @Test
    @DisplayName("Assert that a hand is not a straight 1,2,4,5,6")
    public void isHandNotAStraight() {
        // Given
        Hand hand = new Hand(List.of(
                new Card(Rank.ACE, Color.TREFLE),
                new Card(Rank.TWO, Color.TREFLE),
                new Card(Rank.FOUR, Color.TREFLE),
                new Card(Rank.FIVE, Color.TREFLE),
                new Card(Rank.SIX, Color.HEART)
        ));

        // When
        // Then
        assertFalse(dealer.verifyHasStraight(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a straight with same Color")
    public void isHandAStraightFlush() {
        assertTrue(dealer.verifyHasStraightFlush(STRAIGHT_FLUSH_HAND));
    }
}
