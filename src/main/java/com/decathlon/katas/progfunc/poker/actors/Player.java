package com.decathlon.katas.progfunc.poker.actors;

import com.decathlon.katas.progfunc.poker.hand.Hand;

public class Player extends Person {
    private Hand hand;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
