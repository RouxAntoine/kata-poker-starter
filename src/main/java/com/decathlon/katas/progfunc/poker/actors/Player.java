package com.decathlon.katas.progfunc.poker.actors;

import com.decathlon.katas.progfunc.poker.hand.Hand;

public class Player extends Person {

    private Hand hand;
    private final String name;

    public Player() {
        this("Bot");
    }

    public Player(String playerName) {
        this.name = playerName;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }
}
