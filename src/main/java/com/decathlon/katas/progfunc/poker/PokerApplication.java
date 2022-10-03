package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Dealer;
import com.decathlon.katas.progfunc.poker.actors.Player;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.pot.PokerPot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class PokerApplication {

    public static void main(String[] args) {
        PokerPot pot = new PokerPot();
        Dealer dealer = new Dealer();
        Player[] players = new Player[]{
                new Player("Antoine"),
                new Player("Bot-1"),
                new Player("Bot-2")
        };

        dealer.dealIn(pot, players);

        Map<Hand, Player> playerByHand = Arrays.stream(players)
                .collect(Collectors.toMap(Player::getHand, identity()));

        Hand[] playersHand = Arrays.stream(players).map(Player::getHand).toArray(Hand[]::new);
        dealer.getWinner(playersHand)
                .stream()
                .map(playerByHand::get)
                .forEach(player -> System.out.printf("Winner is %s with hand %s\n", player.getName(), player.getHand()));
    }
}
