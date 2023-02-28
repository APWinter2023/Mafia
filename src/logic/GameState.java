package logic;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private final List<Player> allPlayers;
    private final Set<Player> alivePlayers;
    private final Set<Player> deadInLastRound;
    private Player silentInLastRound;
    private int round;
    private boolean day;
    private Joker joker; // if joker wins

    public GameState(List<Player> players) {
        this.allPlayers = Collections.unmodifiableList(players);
        this.alivePlayers = new HashSet<>(players);
        this.deadInLastRound = new HashSet<>();
        this.silentInLastRound = null;
        this.round = 0;
        this.day = true;
    }

    public List<Player> allPlayers() {
        return allPlayers;
    }

    public int getRound() {
        return round;
    }

    public List<Player> getAlivePlayers() {
        return new ArrayList<>(alivePlayers);
    }

    private boolean godFatherIsAlive() {
        return alivePlayers.stream().anyMatch(player -> player instanceof GodFather);
    }

    private void killPlayer(Player player) {
        alivePlayers.remove(player);
        player.setAlive(false);
    }

    public void nextDay() {
        deadInLastRound.clear();
        alivePlayers.stream()
                .filter(player -> player.isKillBySniper() && !player.isHealByLecter()).findAny()
                .ifPresent(deadInLastRound::add);
        if (godFatherIsAlive()) {
            alivePlayers.stream()
                    .filter(player -> player.isKillByGodfather() && !player.isHeal()).findAny()
                    .ifPresent(deadInLastRound::add);
        } else {
            alivePlayers.stream()
                    .filter(player -> player.getMafiaVotes() > 0)
                    .max(Comparator.comparing(Player::getMafiaVotes))
                    .ifPresent(deadInLastRound::add);
        }
        alivePlayers.forEach(this::killPlayer);
        silentInLastRound = alivePlayers.stream()
                .filter(Player::isMute)
                .findAny()
                .orElse(null);
        getAlivePlayers().forEach(Player::reset);
        day = true;
        round++;
    }

    public void nextNight() {
        alivePlayers.forEach(player -> player.setMute(false));
        Player executedPlayer = getExecutedPlayer();
        if (executedPlayer != null) {
            if (executedPlayer instanceof Joker) {
                joker = (Joker) executedPlayer;
            }
            killPlayer(executedPlayer);
        }
        day = false;
    }

    public boolean isDay() {
        return day;
    }

    public List<Player> getDeadPlayersInLastRound() {
        return new ArrayList<>(deadInLastRound);
    }

    public Player getSilentPlayersInLastRound() {
        return silentInLastRound;
    }

    public List<Player> getWinners() {
        if (joker != null) {
            List<Player> result = new ArrayList<>();
            result.add(joker);
            return result;
        }
        long mafiaCount = alivePlayers.stream().filter(player -> player instanceof Mafia).count();
        if (mafiaCount == 0) {
            return allPlayers.stream().filter(player -> player instanceof Citizen).collect(Collectors.toList());
        }
        if (2 * mafiaCount >= alivePlayers.size()) {
            return allPlayers.stream().filter(player -> player instanceof Mafia).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public Player getExecutedPlayer() {
        int t = alivePlayers.size() / 2 + 1;
        return alivePlayers.stream()
                .filter(player -> player.getVotes() >= t)
                .findAny()
                .orElse(null);
    }

}
