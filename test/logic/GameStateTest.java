package logic;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameStateTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
        GodFather godFather = new GodFather("lachin", 1);
        Mafia mafia = new OrdinaryMafia("khashayar", 2);
        Citizen citizen1 = new OrdinaryCitizen("erfan", 3);
        Citizen citizen2 = new OrdinaryCitizen("aylar", 4);
        Citizen citizen3 = new OrdinaryCitizen("arab ali", 5);
        Citizen citizen4 = new OrdinaryCitizen("sadat", 6);
        List<Player> players = new ArrayList<>();
        players.add(godFather);
        players.add(mafia);
        players.add(citizen1);
        players.add(citizen2);
        players.add(citizen3);
        players.add(citizen4);
        GameState gameState = new GameState(players);
        // check beginning
        assertEquals(6, gameState.getAlivePlayers().size());
        assertEquals(0, gameState.getRound());
        assertEquals(0, gameState.getWinners().size());
        assertNull(gameState.getExecutedPlayer());
        assertEquals(0, gameState.getDeadPlayersInLastRound().size());
        assertNull(gameState.getSilentPlayersInLastRound());
        assertTrue(gameState.isDay());
        // start night
        gameState.nextNight();
        assertFalse(gameState.isDay());
        assertEquals(6, gameState.getAlivePlayers().size());
        // godfather kill citizen1
        godFather.action(citizen1);
        // next day citizen1 is dead
        gameState.nextDay();
        assertEquals(0, gameState.getWinners().size());
        assertEquals(1, gameState.getRound());
        assertEquals(5, gameState.getAlivePlayers().size());
        assertNull(gameState.getSilentPlayersInLastRound());
        assertEquals(1, gameState.getDeadPlayersInLastRound().size());
        assertEquals(citizen1, gameState.getDeadPlayersInLastRound().get(0));
        assertFalse(citizen1.isAlive());
        // citizen vote to mafia
        citizen2.vote(mafia);
        citizen3.vote(mafia);
        citizen4.vote(mafia);
        mafia.vote(godFather);
        godFather.vote(citizen1);
        // next night. mafia is executed
        gameState.nextNight();
        assertFalse(mafia.isAlive());
        assertEquals(mafia, gameState.getExecutedPlayer());
        assertEquals(4, gameState.getAlivePlayers().size());
        assertFalse(gameState.isDay());
        // godfather kill citizen2
        godFather.action(citizen2);
        gameState.nextDay();
        assertEquals(0, gameState.getWinners().size());
        assertEquals(2, gameState.getRound());
        assertEquals(3, gameState.getAlivePlayers().size());
        assertNull(gameState.getSilentPlayersInLastRound());
        assertEquals(1, gameState.getDeadPlayersInLastRound().size());
        assertEquals(citizen2, gameState.getDeadPlayersInLastRound().get(0));
        assertFalse(citizen2.isAlive());
        // citizen vote to godfather
        citizen3.vote(godFather);
        citizen4.vote(godFather);
        godFather.vote(citizen1);
        // next night. godfather is executed
        gameState.nextNight();
        assertFalse(godFather.isAlive());
        assertEquals(godFather, gameState.getExecutedPlayer());
        assertEquals(2, gameState.getAlivePlayers().size());
        assertFalse(gameState.isDay());
        // citizens wins
        assertEquals(4, gameState.getWinners().size());
        assertTrue(gameState.getWinners().contains(citizen1));
        assertTrue(gameState.getWinners().contains(citizen2));
        assertTrue(gameState.getWinners().contains(citizen3));
        assertTrue(gameState.getWinners().contains(citizen4));
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
}