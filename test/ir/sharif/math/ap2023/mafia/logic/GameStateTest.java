package ir.sharif.math.ap2023.mafia.logic;

import ir.sharif.math.ap2023.mafia.model.*;
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

    @Test
    public void test2() {
        GodFather godFather = new GodFather("Aref", 1);
        Sniper sniper = new Sniper("sniper", 2);
        Doctor doctor = new Doctor("doctor", 3);
        DoctorLecter doctorLecter = new DoctorLecter("dc", 4);
        Natasha natasha = new Natasha("natasha", 5);
        Joker joker = new Joker("joker", 6);
        Detective detective = new Detective("detective", 7);
        OrdinaryCitizen ordinaryCitizen = new OrdinaryCitizen("oc", 8);
        OrdinaryCitizen ordinaryCitizen1 = new OrdinaryCitizen("cs", 9);
        List<Player> players = new ArrayList<>();
        players.add(godFather);
        players.add(sniper);
        players.add(doctor);
        players.add(doctorLecter);
        players.add(natasha);
        players.add(joker);
        players.add(detective);
        players.add(ordinaryCitizen);
        players.add(ordinaryCitizen1);

        GameState gameState = new GameState(players);
        // check beginning
        assertEquals(9, gameState.getAlivePlayers().size());
        assertEquals(0, gameState.getRound());
        assertEquals(0, gameState.getWinners().size());
        assertNull(gameState.getExecutedPlayer());
        assertEquals(0, gameState.getDeadPlayersInLastRound().size());
        assertNull(gameState.getSilentPlayersInLastRound());
        assertTrue(gameState.isDay());

        //start night
        gameState.nextNight();
        assertEquals(9, gameState.getAlivePlayers().size());
        assertEquals(0, gameState.getRound());
        assertEquals(0, gameState.getWinners().size());
        assertNull(gameState.getExecutedPlayer());
        assertEquals(0, gameState.getDeadPlayersInLastRound().size());
        assertNull(gameState.getSilentPlayersInLastRound());
        assertFalse(gameState.isDay());

        //godfather kill doctor
        godFather.action(doctor);
        //doctor save himself
        doctor.action(doctor);
        //natasha mute joker
        natasha.action(joker);
        assertEquals(9, gameState.getAlivePlayers().size());
        assertFalse(gameState.isDay());
        assertNull(gameState.getSilentPlayersInLastRound());

        gameState.nextDay();
        assertTrue(gameState.isDay());
        assertTrue(doctor.isAlive());
        assertTrue(godFather.isAlive());
        assertFalse(natasha.isMute());
        assertTrue(joker.isMute());
        assertEquals(1, doctor.getNumberOfSelfSave());
        assertEquals(0, gameState.getWinners().size());
        assertEquals(1, gameState.getRound());
        assertEquals(9, gameState.getAlivePlayers().size());
        assertEquals(joker, gameState.getSilentPlayersInLastRound());
        assertEquals(0, gameState.getDeadPlayersInLastRound().size());

        //vote
        joker.vote(joker);
        doctor.vote(joker);
        godFather.vote(joker);
        natasha.vote(doctor);

        gameState.nextNight();
        assertFalse(gameState.isDay());
        assertNull(gameState.getExecutedPlayer());
        assertEquals(9, gameState.getAlivePlayers().size());
        assertTrue(joker.isAlive());

        //sniper shot godfather
        sniper.action(godFather);
        //doctorlecter save godfather
        doctorLecter.action(godFather);
        //godfather kill doctor
        godFather.action(doctor);
        //detective detect natasha
        String result = detective.action(natasha);
        assertEquals(result, "MAFIA");
        gameState.nextDay();
        assertEquals(1, sniper.getNumberOfBulletsLeft());
        assertEquals(8, gameState.getAlivePlayers().size());
        assertFalse(doctor.isAlive());
        assertTrue(godFather.isAlive());
        assertNull(gameState.getSilentPlayersInLastRound());

        //vote to execute detective
        godFather.vote(detective);
        natasha.vote(detective);
        detective.vote(godFather);
        sniper.vote(detective);
        joker.vote(detective);
        doctorLecter.vote(detective);

        gameState.nextNight();
        assertEquals(detective, gameState.getExecutedPlayer());
        assertFalse(detective.isAlive());
        assertFalse(gameState.isDay());
        assertEquals(7, gameState.getAlivePlayers().size());
        assertEquals(0, gameState.getWinners().size());
        assertEquals(2, gameState.getRound());

        //godfather kill sniper
        godFather.action(sniper);
        //sniper kill joker
        sniper.action(joker);
        //docterlecter save joker!
        doctorLecter.action(joker);

        //mafia win
        gameState.nextDay();
        assertEquals(0, sniper.getNumberOfBulletsLeft());
        assertFalse(godFather.isAsked());
        assertEquals(6, gameState.getAlivePlayers().size());
        assertTrue(gameState.getWinners().contains(godFather));
        assertTrue(gameState.getWinners().contains(natasha));
        assertTrue(gameState.getWinners().contains(doctorLecter));
        assertEquals(3, gameState.getWinners().size());
    }

    private GodFather godFather;
    private Natasha natasha;
    private DoctorLecter doctorLecter;
    private OrdinaryMafia mafia;
    private Detective detective;
    private Doctor doctor;
    private Sniper sniper;
    private OrdinaryCitizen citizen1, citizen2, citizen3, citizen4;
    private Joker joker;

    private ArrayList<Player> players;

    public void init() {
        godFather = new GodFather("nima", 0);
        detective = new Detective("soheil", 1);
        natasha = new Natasha("rabi", 2);
        doctorLecter = new DoctorLecter("zeinab", 3);
        mafia = new OrdinaryMafia("tabas", 4);
        doctor = new Doctor("ali", 5);
        sniper = new Sniper("arman", 6);
        citizen1 = new OrdinaryCitizen("ali2", 7);
        citizen2 = new OrdinaryCitizen("ali barg", 8);
        citizen3 = new OrdinaryCitizen("firoozeh", 9);
        citizen4 = new OrdinaryCitizen("hossein", 10);
        joker = new Joker("farzad", 11);
        players = new ArrayList<>();
        players.add(godFather);
        players.add(sniper);
        players.add(doctor);
        players.add(doctorLecter);
        players.add(natasha);
        players.add(mafia);
        players.add(joker);
        players.add(detective);
        players.add(citizen1);
        players.add(citizen2);
        players.add(citizen3);
        players.add(citizen4);

    }

    @Test
    public void test3() {
        init();
        GameState gameState = new GameState(players);

        gameState.nextNight();
        // **** night 1
        assertEquals(0, gameState.getRound());
        mafia.action(citizen1);
        natasha.action(doctor);
        doctorLecter.action(natasha);
        sniper.action(natasha);
        godFather.action(citizen1);
        doctor.action(detective);
        assertEquals("MAFIA", detective.action(mafia));
        // **** day 1 :
        // citizen 1 is dead, natasha is not dead (after sniper shot), doctor is muted
        gameState.nextDay();
        assertEquals(1, gameState.getRound());
        assertEquals(11, gameState.getAlivePlayers().size());
        assertTrue(doctor.isMute());
        assertTrue(natasha.isAlive());
        assertFalse(citizen1.isAlive());

        // voting
        godFather.vote(citizen2);
        mafia.vote(citizen2);
        natasha.vote(citizen2);
        doctorLecter.vote(citizen2);
        detective.vote(mafia);
        doctor.vote(mafia);
        sniper.vote(natasha);
        joker.vote(mafia);
        citizen2.vote(mafia);
        citizen3.vote(mafia);
        citizen4.vote(mafia);

        // **** night 2
        // mafia is executed
        gameState.nextNight();
        assertFalse(mafia.isAlive());
        godFather.action(doctor);
        natasha.action(joker);
        doctorLecter.action(godFather);
        assertEquals("NO_MAFIA", detective.action(godFather));
        doctor.action(doctor);
        sniper.action(citizen3);

        // **** day 2
        // doctor heal himself, citizen3 shot by sniper, godfather healed by doctolecter, detective asked for god father
        gameState.nextDay();
        assertEquals(2, gameState.getRound());
        assertTrue(godFather.isAsked());
        assertEquals(9, gameState.getAlivePlayers().size());
        assertEquals(0, sniper.getNumberOfBulletsLeft());
        assertEquals(1, doctor.getNumberOfSelfSave());
        assertTrue(joker.isMute());

        // voting
        godFather.vote(citizen2);
        natasha.vote(citizen2);
        doctorLecter.vote(citizen2);
        detective.vote(natasha);
        doctor.vote(natasha);
        sniper.vote(natasha);
        joker.vote(sniper);
        citizen2.vote(godFather);
        citizen4.vote(sniper);
        // **** night 3
        // no one is executed
        gameState.nextNight();
        assertEquals(9, gameState.getAlivePlayers().size());
        godFather.action(citizen2);
        natasha.action(citizen4);
        doctorLecter.action(natasha);
        assertEquals("MAFIA", detective.action(godFather));
        doctor.action(doctor);
        sniper.action(godFather); // godfather shouldn't die


        // **** day 3
        // citizen2 is dead, citizen 4 is silent, doctor healed himself,sniper shot godfather
        gameState.nextDay();
        assertEquals(8, gameState.getAlivePlayers().size());
        assertTrue(godFather.isAlive());
        assertEquals(0, doctor.getNumberOfSelfSave());

        // no voting

        // **** night 4
        gameState.nextNight();
        godFather.action(doctor);
        doctor.action(doctor);
        // other actions don't matter

        //  **** day 4
        // doctor should be dead
        gameState.nextDay();
        assertFalse(doctor.isAlive());
        // voting
        godFather.vote(joker);
        natasha.vote(joker);
        doctorLecter.vote(joker);
        detective.vote(joker);
        doctor.vote(joker);
        sniper.vote(joker);

        gameState.nextNight();
        // game is over, joker won
        assertEquals(6, gameState.getAlivePlayers().size());
        assertTrue(gameState.getWinners().contains(joker));
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
}