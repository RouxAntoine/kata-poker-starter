package com.decathlon.katas.progfunc.poker.card;

import com.decathlon.katas.progfunc.poker.card.criteria.Color;
import com.decathlon.katas.progfunc.poker.card.criteria.Rank;

/**
 * a card contains references to a rank & color
 */
public record Card(Rank rank, Color color) {
}
