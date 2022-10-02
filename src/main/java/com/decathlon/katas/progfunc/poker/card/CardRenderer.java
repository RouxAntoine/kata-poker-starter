package com.decathlon.katas.progfunc.poker.card;

import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;

import java.util.Map;

public class CardRenderer {

    final static Map<Color, Map<Rank, String>> CARD_ASSETS = Map.of(
            // trefle
            Color.TREFLE, Map.ofEntries(
                    Map.entry(Rank.ACE, "🃑"),
                    Map.entry(Rank.TWO, "🃒"),
                    Map.entry(Rank.THREE, "🃓"),
                    Map.entry(Rank.FOUR, "🃔"),
                    Map.entry(Rank.FIVE, "🃕"),
                    Map.entry(Rank.SIX, "🃖"),
                    Map.entry(Rank.SEVEN, "🃗"),
                    Map.entry(Rank.EIGHT, "🃘"),
                    Map.entry(Rank.NINE, "🃙"),
                    Map.entry(Rank.TEN, "🃚"),
                    Map.entry(Rank.JACK, "🃛"),
                    Map.entry(Rank.QUEEN, "🃜"),
                    Map.entry(Rank.KING, "🃞")
            ),
            // carreau
            Color.DIAMOND, Map.ofEntries(
                    Map.entry(Rank.ACE, "🃁"),
                    Map.entry(Rank.TWO, "🃂"),
                    Map.entry(Rank.THREE, "🃃"),
                    Map.entry(Rank.FOUR, "🃄"),
                    Map.entry(Rank.FIVE, "🃅"),
                    Map.entry(Rank.SIX, "🃆"),
                    Map.entry(Rank.SEVEN, "🃇"),
                    Map.entry(Rank.EIGHT, "🃈"),
                    Map.entry(Rank.NINE, "🃉"),
                    Map.entry(Rank.TEN, "🃊"),
                    Map.entry(Rank.JACK, "🃋"),
                    Map.entry(Rank.QUEEN, "🃍"),
                    Map.entry(Rank.KING, "🃎")
            ),
            // coeur
            Color.HEART, Map.ofEntries(
                    Map.entry(Rank.ACE, "🂱"),
                    Map.entry(Rank.TWO, "🂲"),
                    Map.entry(Rank.THREE, "🂳"),
                    Map.entry(Rank.FOUR, "🂴"),
                    Map.entry(Rank.FIVE, "🂵"),
                    Map.entry(Rank.SIX, "🂶"),
                    Map.entry(Rank.SEVEN, "🂷"),
                    Map.entry(Rank.EIGHT, "🂸"),
                    Map.entry(Rank.NINE, "🂹"),
                    Map.entry(Rank.TEN, "🂺"),
                    Map.entry(Rank.JACK, "🂻"),
                    Map.entry(Rank.QUEEN, "🂽"),
                    Map.entry(Rank.KING, "🂾")
            ),
            // pic
            Color.SPADE, Map.ofEntries(
                    Map.entry(Rank.ACE, "🂡"),
                    Map.entry(Rank.TWO, "🂢"),
                    Map.entry(Rank.THREE, "🂣"),
                    Map.entry(Rank.FOUR, "🂤"),
                    Map.entry(Rank.FIVE, "🂥"),
                    Map.entry(Rank.SIX, "🂦"),
                    Map.entry(Rank.SEVEN, "🂧"),
                    Map.entry(Rank.EIGHT, "🂨"),
                    Map.entry(Rank.NINE, "🂩"),
                    Map.entry(Rank.TEN, "🂪"),
                    Map.entry(Rank.JACK, "🂫"),
                    Map.entry(Rank.QUEEN, "🂭"),
                    Map.entry(Rank.KING, "🂮")
            )
    );
}
