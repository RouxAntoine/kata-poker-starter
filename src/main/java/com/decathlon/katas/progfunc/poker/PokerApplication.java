package com.decathlon.katas.progfunc.poker;

import com.decathlon.katas.progfunc.poker.actors.Dealer;
import com.decathlon.katas.progfunc.poker.actors.Player;
import com.decathlon.katas.progfunc.poker.hand.Hand;
import com.decathlon.katas.progfunc.poker.pot.PokerPot;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class PokerApplication {

    public static void main(String[] args) {
        PokerPot pot = new PokerPot();
        Player player1 = new Player("Antoine");
        Player player2 = new Player("Bot-1");
        Dealer dealer = new Dealer();

        List<Player> players = List.of(player1, player2);
        dealer.dealIn(pot, players.toArray(Player[]::new));

        Map<Hand, Player> playerByHand = players.stream().collect(Collectors.toMap(Player::getHand, identity()));

        Hand player1Hand = player1.getHand();
        Hand player2Hand = player2.getHand();

        dealer.compare(player1Hand, player2Hand)
                .map(playerByHand::get)
                .ifPresentOrElse(
                        player -> System.out.printf("Winner is %s with hand %s\n", player.getName(), player.getHand().cards()),
                        () -> System.out.println("Exquo between all player")
                );
    }
}
