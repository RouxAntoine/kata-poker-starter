package com.decathlon.katas.progfunc.poker.card.criteria;

/**
 * Color of any card
 */
public enum Color implements Criteria {
    SPADE("♠️"),
    HEART("♥️"),
    TREFLE("♣️"),
    DIAMOND("♦️")
    ;

    private final String logo;

    Color(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }
}
