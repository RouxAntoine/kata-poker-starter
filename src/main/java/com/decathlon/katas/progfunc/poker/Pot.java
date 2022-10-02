package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.card.Card;
import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;
import com.decathlon.katas.progfunc.poker.hand.Hand;

import java.util.List;

public class Pot {

    public Hand getRandomHand() {
        return new Hand(
                List.of(
                        new Card(Rank.FIVE, Color.TREFLE),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.FIVE, Color.DIAMOND),
                        new Card(Rank.KING, Color.HEART)
                )
        );
    }
}
