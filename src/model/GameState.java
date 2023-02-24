package model;

import java.util.ArrayList;
import java.util.List;

public class GameState { //todo
    private List<Player> allPlayers;
    private int round;
    private List<Player> alivePlayers;
    //private List<Mafia> mafiaPlayers;

    public GameState(){
        allPlayers = new ArrayList<>();
        alivePlayers = new ArrayList<>();
        round = 0;
    }

    public void addPlayer(Player player){
        allPlayers.add(player);
        alivePlayers.add(player);
    }

    public void killPlayer(Player player){
        alivePlayers.remove(player);
    }

    public void nextRound(){
        round ++;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public int getRound() {
        return round;
    }

    public List<Player> getAlivePlayers() {
        return alivePlayers;
    }
}
