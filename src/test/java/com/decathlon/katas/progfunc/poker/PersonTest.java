package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.decathlon.katas.progfunc.poker.HandFixture.COLOR_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.FOUR_OF_A_KIND_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.FULL_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.PAIR_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.ROYAL_FLUSH_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.STRAIGHT_FLUSH_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.STRAIGHT_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.THREE_OF_A_KIND_HAND;
import static com.decathlon.katas.progfunc.poker.HandFixture.TWO_PAIR_HAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    private final Person person;

    public PersonTest() {
        this.person = new Person();
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
        assertTrue(person.verifyHasPair(PAIR_HAND));
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
        assertFalse(person.verifyHasPair(hand));
    }

    @Test
    @DisplayName("Assert hand contain a three of a kind")
    public void isHandAThreeOfAKind() {
        assertTrue(person.verifyHasThreeOfAKind(THREE_OF_A_KIND_HAND));
        assertFalse(person.verifyHasPair(THREE_OF_A_KIND_HAND));
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
        assertFalse(person.verifyHasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Assert hand contain a four of a kind")
    public void isHandAFourOfAKind() {
        assertTrue(person.verifyHasFourOfAKind(FOUR_OF_A_KIND_HAND));
        assertFalse(person.verifyHasThreeOfAKind(FOUR_OF_A_KIND_HAND));
        assertFalse(person.verifyHasPair(FOUR_OF_A_KIND_HAND));
        assertFalse(person.verifyHasTwoPair(FOUR_OF_A_KIND_HAND));
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
        assertFalse(person.verifyHasFourOfAKind(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a full house")
    public void isHandAFullHouse() {
        assertTrue(person.verifyHasFullHouse(FULL_HAND));
        assertFalse(person.verifyHasPair(FULL_HAND));
        assertFalse(person.verifyHasThreeOfAKind(FULL_HAND));
        assertFalse(person.verifyHasTwoPair(FULL_HAND));
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
        assertFalse(person.verifyHasFullHouse(hand));
    }

    @Test
    @DisplayName("Assert that a hand contain two pair")
    public void isHandTwoPair() {
        assertTrue(person.verifyHasTwoPair(TWO_PAIR_HAND));
        assertFalse(person.verifyHasPair(TWO_PAIR_HAND));
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
        assertFalse(person.verifyHasTwoPair(hand));
    }

    @Test
    @DisplayName("Assert that a hand contain a color")
    public void isHandAColor() {
        assertTrue(person.verifyHasColor(COLOR_HAND));
        assertFalse(person.verifyHasStraightFlush(COLOR_HAND));
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
        assertFalse(person.verifyHasColor(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a straight 1,2,3,4,5")
    public void isHandAStraightOnACE() {
        assertTrue(person.verifyHasStraight(STRAIGHT_HAND));
        assertFalse(person.verifyHasStraightFlush(STRAIGHT_HAND));
    }

    @Test
    @DisplayName("Assert that a hand is a straight 10,J,Q,K,1")
    public void isHandARoyalFlush() {
        assertTrue(person.verifyHasStraight(ROYAL_FLUSH_HAND));
        assertFalse(person.verifyHasStraightFlush(ROYAL_FLUSH_HAND));
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
        assertFalse(person.verifyHasStraight(hand));
    }

    @Test
    @DisplayName("Assert that a hand is a straight with same Color")
    public void isHandAStraightFlush() {
        assertTrue(person.verifyHasStraightFlush(STRAIGHT_FLUSH_HAND));
        assertFalse(person.verifyHasColor(STRAIGHT_FLUSH_HAND));
        assertFalse(person.verifyHasStraight(STRAIGHT_FLUSH_HAND));
    }

    @Test
    public void evaluateHighCard() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.SIX, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE)
                )
        );
        Hand handWithAce = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.ACE, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.QUEEN, Color.TREFLE),
                        new Card(Rank.KING, Color.TREFLE)
                )
        );
        // When
        Integer handValue = person.computeHandValue(hand);
        Integer handWiInteger = person.computeHandValue(handWithAce);

        // Then
        assertEquals(44, handValue);
        assertEquals(52, handWiInteger);
    }

    @Test
    public void evaluatePair() {
        // Given
        Hand hand = new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.SIX, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.TREFLE),
                        new Card(Rank.EIGHT, Color.DIAMOND),
                        new Card(Rank.KING, Color.TREFLE)
                )
        );

        // When
        Integer handValue = person.computeHandValue(hand);

        // Then
        assertEquals(824, handValue);
    }
}
