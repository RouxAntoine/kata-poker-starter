package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Person;
import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.decathlon.katas.progfunc.poker.hand.HandFixture.COLOR_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.FOUR_OF_A_KIND_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.FULL_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.PAIR_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.ROYAL_FLUSH_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.ROYAL_STRAIGHT_FLUSH_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.STRAIGHT_FLUSH_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.STRAIGHT_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.THREE_OF_A_KIND_HAND;
import static com.decathlon.katas.progfunc.poker.hand.HandFixture.TWO_PAIR_HAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    private final Person person = new Person();

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
        Optional<Double> composedHandValue = person.computeAPair(PAIR_HAND);
        assertTrue(composedHandValue.isPresent());
        assertEquals(4000, composedHandValue.get());

        Double handValue = person.computeHandValue(PAIR_HAND);
        assertEquals(4000, handValue);
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
        assertFalse(person.computeAPair(hand).isPresent());
    }

    @Test
    @DisplayName("Assert hand contain a three of a kind")
    public void isHandAThreeOfAKind() {
        Optional<Double> composedHandValue = person.computeAThreeOfAKind(THREE_OF_A_KIND_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAPair(THREE_OF_A_KIND_HAND).isPresent());
        assertEquals(350_000, composedHandValue.get());

        Double handValue = person.computeHandValue(THREE_OF_A_KIND_HAND);
        assertEquals(350_000, handValue);
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
        assertFalse(person.computeAThreeOfAKind(hand).isPresent());
    }

    @Test
    @DisplayName("Assert hand contain a four of a kind")
    public void isHandAFourOfAKind() {
        Optional<Double> composedHandValue = person.computeAFourOfAKind(FOUR_OF_A_KIND_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAThreeOfAKind(FOUR_OF_A_KIND_HAND).isPresent());
        assertFalse(person.computeAPair(FOUR_OF_A_KIND_HAND).isPresent());
        assertFalse(person.computeATwoPair(FOUR_OF_A_KIND_HAND).isPresent());
        assertEquals(3_300_000_000d, composedHandValue.get());

        Double handValue = person.computeHandValue(FOUR_OF_A_KIND_HAND);
        assertEquals(3_300_000_000d, handValue);
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
        assertFalse(person.computeAFourOfAKind(hand).isPresent());
    }

    @Test
    @DisplayName("Assert that a hand is a full house")
    public void isHandAFullHouse() {
        Optional<Double> composedHandValue = person.computeAFullHouse(FULL_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAPair(FULL_HAND).isPresent());
        assertFalse(person.computeAThreeOfAKind(FULL_HAND).isPresent());
        assertFalse(person.computeATwoPair(FULL_HAND).isPresent());
        assertEquals(220_000_000, composedHandValue.get());

        Double handValue = person.computeHandValue(FULL_HAND);
        assertEquals(220_000_000, handValue);
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
        assertFalse(person.computeAFullHouse(hand).isPresent());
    }

    @Test
    @DisplayName("Assert that a hand contain two pair")
    public void isHandTwoPair() {
        Optional<Double> composedHandValue = person.computeATwoPair(TWO_PAIR_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAPair(TWO_PAIR_HAND).isPresent());
        assertEquals(33_000, composedHandValue.get());

        Double handValue = person.computeHandValue(TWO_PAIR_HAND);
        assertEquals(33_000, handValue);
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
        assertFalse(person.computeATwoPair(hand).isPresent());
    }

    @Test
    @DisplayName("Assert that a hand contain a color")
    public void isHandAColor() {
        Optional<Double> handValue = person.computeAColor(COLOR_HAND);
        assertTrue(handValue.isPresent());
        assertFalse(person.computeAStraightFlush(COLOR_HAND).isPresent());
        assertEquals(33_000_000, handValue.get());
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
        assertFalse(person.computeAColor(hand).isPresent());
    }

    @Test
    @DisplayName("Assert that a hand is a straight 1,2,3,4,5")
    public void isHandAStraightOnOne() {
        Optional<Double> composedHandValue = person.computeAStraight(STRAIGHT_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAStraightFlush(STRAIGHT_HAND).isPresent());
        assertEquals(1_500_000, composedHandValue.get());

        Double handValue = person.computeHandValue(STRAIGHT_HAND);
        assertEquals(1_500_000, handValue);
    }

    @Test
    @DisplayName("Assert that a hand is a straight 10,J,Q,K,1")
    public void isHandARoyalFlush() {
        Optional<Double> composedHandValue = person.computeAStraight(ROYAL_FLUSH_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAStraightFlush(ROYAL_FLUSH_HAND).isPresent());
        assertEquals(6_000_000, composedHandValue.get());

        Double handValue = person.computeHandValue(ROYAL_FLUSH_HAND);
        assertEquals(6_000_000, handValue);
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
        assertFalse(person.computeAStraight(hand).isPresent());
    }

    @Test
    @DisplayName("Assert that a hand is a straight 1, 2, 3, 4, 5 with same Color")
    public void isHandAStraightFlushOnOne() {
        Optional<Double> composedHandValue = person.computeAStraightFlush(STRAIGHT_FLUSH_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAColor(STRAIGHT_FLUSH_HAND).isPresent());
        assertFalse(person.computeAStraight(STRAIGHT_FLUSH_HAND).isPresent());
        assertEquals(15_000_000_000d, composedHandValue.get());

        Double handValue = person.computeHandValue(STRAIGHT_FLUSH_HAND);
        assertEquals(15_000_000_000d, handValue);
    }

    @Test
    @DisplayName("Assert that a hand is a straight 10, J, Q, K, A with same Color")
    public void isHandARoyalStraightFlush() {
        Optional<Double> composedHandValue = person.computeAStraightFlush(ROYAL_STRAIGHT_FLUSH_HAND);
        assertTrue(composedHandValue.isPresent());
        assertFalse(person.computeAColor(ROYAL_STRAIGHT_FLUSH_HAND).isPresent());
        assertFalse(person.computeAStraight(ROYAL_STRAIGHT_FLUSH_HAND).isPresent());
        assertEquals(60_000_000_000d, composedHandValue.get());

        Double handValue = person.computeHandValue(ROYAL_STRAIGHT_FLUSH_HAND);
        assertEquals(60_000_000_000d, handValue);
    }

    @Test
    @DisplayName("Assert high card hand value")
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
        double handValue = person.computeSimpleHandValue(hand);
        double handValueWithAce = person.computeSimpleHandValue(handWithAce);

        // Then
        assertEquals(440, handValue);
        assertEquals(520, handValueWithAce);
    }
}
