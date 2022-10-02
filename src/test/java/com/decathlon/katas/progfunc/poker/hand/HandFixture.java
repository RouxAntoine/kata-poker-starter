package com.decathlon.katas.progfunc.poker.hand;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;

import java.util.List;

public class HandFixture {

    public static Hand STRAIGHT_FLUSH_HAND = new Hand(
            List.of(
                    new Card(Rank.ACE, Color.TREFLE),
                    new Card(Rank.TWO, Color.TREFLE),
                    new Card(Rank.THREE, Color.TREFLE),
                    new Card(Rank.FOUR, Color.TREFLE),
                    new Card(Rank.FIVE, Color.TREFLE)
            )
    );

    public static Hand ROYAL_STRAIGHT_FLUSH_HAND = new Hand(
            List.of(
                    new Card(Rank.ACE, Color.TREFLE),
                    new Card(Rank.JACK, Color.TREFLE),
                    new Card(Rank.QUEEN, Color.TREFLE),
                    new Card(Rank.KING, Color.TREFLE),
                    new Card(Rank.TEN, Color.TREFLE)
            )
    );

    public static Hand ROYAL_FLUSH_HAND = new Hand(
            List.of(
                    new Card(Rank.TEN, Color.TREFLE),
                    new Card(Rank.JACK, Color.TREFLE),
                    new Card(Rank.QUEEN, Color.TREFLE),
                    new Card(Rank.KING, Color.TREFLE),
                    new Card(Rank.ACE, Color.HEART)
            )
    );

    public static Hand STRAIGHT_HAND = new Hand(
            List.of(
                    new Card(Rank.ACE, Color.TREFLE),
                    new Card(Rank.TWO, Color.TREFLE),
                    new Card(Rank.THREE, Color.TREFLE),
                    new Card(Rank.FOUR, Color.TREFLE),
                    new Card(Rank.FIVE, Color.HEART)
            )
    );

    public static Hand COLOR_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.KING, Color.TREFLE),
                    new Card(Rank.SEVEN, Color.TREFLE),
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.THREE, Color.TREFLE)
            )
    );

    public static Hand TWO_PAIR_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.SIX, Color.TREFLE),
                    new Card(Rank.EIGHT, Color.TREFLE),
                    new Card(Rank.EIGHT, Color.DIAMOND),
                    new Card(Rank.SIX, Color.TREFLE)
            )
    );

    public static Hand FULL_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.FOUR, Color.DIAMOND),
                    new Card(Rank.FOUR, Color.SPADE),
                    new Card(Rank.FOUR, Color.HEART)
            )
    );

    public static Hand FOUR_OF_A_KIND_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.KING, Color.HEART)
            )
    );

    public static Hand THREE_OF_A_KIND_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.TREFLE),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.SEVEN, Color.SPADE),
                    new Card(Rank.KING, Color.HEART)
            )
    );

    public static Hand PAIR_HAND = new Hand(
            List.of(
                    new Card(Rank.FIVE, Color.DIAMOND),
                    new Card(Rank.SIX, Color.TREFLE),
                    new Card(Rank.EIGHT, Color.TREFLE),
                    new Card(Rank.EIGHT, Color.DIAMOND),
                    new Card(Rank.KING, Color.TREFLE)
            )
    );
}
