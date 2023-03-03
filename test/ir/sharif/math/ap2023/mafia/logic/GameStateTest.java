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
        assertNull(gameState.getSilentPlayerInLastRound());
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
        assertNull(gameState.getSilentPlayerInLastRound());
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
        assertNull(gameState.getSilentPlayerInLastRound());
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
        assertNull(gameState.getSilentPlayerInLastRound());
        assertTrue(gameState.isDay());

        //start night
        gameState.nextNight();
        assertEquals(9, gameState.getAlivePlayers().size());
        assertEquals(0, gameState.getRound());
        assertEquals(0, gameState.getWinners().size());
        assertNull(gameState.getExecutedPlayer());
        assertEquals(0, gameState.getDeadPlayersInLastRound().size());
        assertNull(gameState.getSilentPlayerInLastRound());
        assertFalse(gameState.isDay());

        //godfather kill doctor
        godFather.action(doctor);
        //doctor save himself
        doctor.action(doctor);
        //natasha mute joker
        natasha.action(joker);
        assertEquals(9, gameState.getAlivePlayers().size());
        assertFalse(gameState.isDay());
        assertNull(gameState.getSilentPlayerInLastRound());

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
        assertEquals(joker, gameState.getSilentPlayerInLastRound());
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
        assertNull(gameState.getSilentPlayerInLastRound());

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

    @Test
    public void test3() {
        GodFather godFather = new GodFather("nima", 0);
        Detective detective = new Detective("soheil", 1);
        Natasha natasha = new Natasha("rabi", 2);
        DoctorLecter doctorLecter = new DoctorLecter("zeinab", 3);
        OrdinaryMafia mafia = new OrdinaryMafia("tabas", 4);
        Doctor doctor = new Doctor("ali", 5);
        Sniper sniper = new Sniper("arman", 6);
        OrdinaryCitizen citizen1 = new OrdinaryCitizen("ali2", 7);
        OrdinaryCitizen citizen2 = new OrdinaryCitizen("ali barg", 8);
        OrdinaryCitizen citizen3 = new OrdinaryCitizen("firoozeh", 9);
        OrdinaryCitizen citizen4 = new OrdinaryCitizen("hossein", 10);
        Joker joker = new Joker("farzad", 11);
        List<Player> players = new ArrayList<>();
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
        GameState gameState = new GameState(players);

        gameState.nextNight();
        // **** night 1
        assertEquals(2, sniper.getNumberOfBulletsLeft());
        assertEquals(2, doctor.getNumberOfSelfSave());
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
        assertEquals(1, sniper.getNumberOfBulletsLeft());
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
        String shotResult = sniper.action(godFather); // godfather shouldn't die
        assertEquals("NO_BULLETS", shotResult);

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
        String saveResult = doctor.action(doctor);
        assertEquals("NO_SAVE", saveResult);
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

    @Test
    public void test4() {
        GodFather godFather = new GodFather("nima", 0);
        Detective detective = new Detective("soheil", 1);
        Natasha natasha = new Natasha("rabi", 2);
        DoctorLecter doctorLecter = new DoctorLecter("zeinab", 3);
        OrdinaryMafia mafia1 = new OrdinaryMafia("tabas1", 41);
        OrdinaryMafia mafia2 = new OrdinaryMafia("tabas2", 42);
        OrdinaryMafia mafia3 = new OrdinaryMafia("tabas3", 43);
        Doctor doctor = new Doctor("ali", 5);
        Sniper sniper = new Sniper("arman", 6);
        OrdinaryCitizen citizen1 = new OrdinaryCitizen("ali2", 7);
        OrdinaryCitizen citizen2 = new OrdinaryCitizen("ali barg", 8);
        OrdinaryCitizen citizen3 = new OrdinaryCitizen("firoozeh", 9);
        OrdinaryCitizen citizen4 = new OrdinaryCitizen("hossein", 10);
        Joker joker = new Joker("farzad", 11);
        List<Player> players = new ArrayList<>();
        players.add(godFather);
        players.add(sniper);
        players.add(doctor);
        players.add(doctorLecter);
        players.add(natasha);
        players.add(mafia1);
        players.add(mafia2);
        players.add(mafia3);
        players.add(joker);
        players.add(detective);
        players.add(citizen1);
        players.add(citizen2);
        players.add(citizen3);
        players.add(citizen4);
        GameState gameState = new GameState(players);

        gameState.nextNight();
        // **** night 1
        assertEquals(2, sniper.getNumberOfBulletsLeft());
        assertEquals(2, doctor.getNumberOfSelfSave());
        assertEquals(0, gameState.getRound());
        natasha.action(citizen2);
        doctorLecter.action(natasha);
        sniper.action(godFather);
        godFather.action(citizen1);
        doctor.action(detective);
        assertEquals("MAFIA", detective.action(natasha));
        // **** day 1 :
        // citizen 1 is dead, godfather is dead, citizen2 is muted
        gameState.nextDay();
        assertEquals(1, gameState.getRound());
        assertEquals(1, sniper.getNumberOfBulletsLeft());
        assertEquals(12, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        assertFalse(citizen1.isMute());
        assertTrue(natasha.isAlive());
        assertFalse(citizen1.isAlive());
        assertFalse(godFather.isAlive());
        assertTrue(gameState.getDeadPlayersInLastRound().contains(citizen1));
        assertTrue(gameState.getDeadPlayersInLastRound().contains(godFather));

        // voting
        mafia1.vote(citizen2);
        natasha.vote(citizen2);
        doctorLecter.vote(citizen2);
        detective.vote(natasha);
        doctor.vote(natasha);
        sniper.vote(natasha);
        joker.vote(natasha);
        citizen2.vote(natasha);
        citizen3.vote(natasha);
        citizen4.vote(natasha);
        mafia2.vote(mafia3);
        mafia3.vote(mafia2);

        // **** night 2
        // natasha is executed
        gameState.nextNight();
        assertFalse(natasha.isAlive());
        assertEquals(11, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        mafia1.action(doctor);
        mafia2.action(doctor);
        mafia3.action(joker);
        doctorLecter.action(mafia1);
        doctor.action(doctor);
        sniper.action(mafia1);
        assertEquals("NO_MAFIA", detective.action(joker));

        // **** day 2
        // doctor heal himself, mafia1 is shot by sniper but saved by doctor lecter
        gameState.nextDay();
        assertEquals(2, gameState.getRound());
        assertEquals(11, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        assertEquals(0, sniper.getNumberOfBulletsLeft());
        assertEquals(1, doctor.getNumberOfSelfSave());

        // voting
        mafia1.vote(citizen2);
        mafia2.vote(doctor);
        mafia3.vote(doctor);
        doctorLecter.vote(citizen2);
        detective.vote(doctorLecter);
        doctor.vote(doctorLecter);
        sniper.vote(doctorLecter);
        joker.vote(sniper);
        citizen2.vote(sniper);
        citizen4.vote(sniper);
        // **** night 3
        // no one is executed
        gameState.nextNight();
        assertEquals(11, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        mafia1.action(citizen2);
        mafia2.action(citizen2);
        mafia3.action(doctor);
        doctorLecter.action(mafia1);
        assertEquals("NO_MAFIA", detective.action(citizen4));
        doctor.action(doctor);
        assertEquals("NO_BULLETS", sniper.action(mafia1));

        // **** day 3
        // citizen2 is dead, doctor healed himself,sniper cant shot mafia1
        gameState.nextDay();
        assertFalse(citizen2.isAlive());
        assertEquals(10, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        assertTrue(mafia1.isAlive());
        assertEquals(0, doctor.getNumberOfSelfSave());

        // no voting
        mafia1.vote(doctor);
        mafia2.vote(doctor);
        mafia3.vote(doctor);
        doctorLecter.vote(doctor);
        detective.vote(doctor);
        doctor.vote(doctor);
        sniper.vote(doctor);
        joker.vote(sniper);
        citizen3.vote(sniper);
        citizen4.vote(sniper);
        // **** night 4
        gameState.nextNight();
        assertFalse(doctor.isAlive());
        assertEquals(doctor, gameState.getExecutedPlayer());
        assertEquals(9, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));

        mafia1.action(detective);
        mafia2.action(citizen3);
        mafia3.action(sniper);
        // other actions don't matter

        //  **** day 4
        // doctor should be dead
        gameState.nextDay();
        assertEquals(8, gameState.getAlivePlayers().size());
        assertTrue(gameState.getAlivePlayers().stream().allMatch(Player::isAlive));
        assertFalse(detective.isAlive() && citizen3.isAlive() && sniper.isAlive());
        // game is over mafia wins
        assertEquals(6, gameState.getWinners().size());
        assertTrue(gameState.getWinners().contains(godFather));
        assertTrue(gameState.getWinners().contains(doctorLecter));
        assertTrue(gameState.getWinners().contains(natasha));
        assertTrue(gameState.getWinners().contains(mafia1));
        assertTrue(gameState.getWinners().contains(mafia2));
        assertTrue(gameState.getWinners().contains(mafia3));
    }

    @Test
    public void test5(){
        Sniper sniper = new Sniper("sniper" , 1);
        Detective detective = new Detective("detective" , 2);
        GodFather godFather = new GodFather("father" , 3);
        OrdinaryMafia mafia = new OrdinaryMafia("mafia" , 4);
        OrdinaryCitizen citizen1 = new OrdinaryCitizen("citizen1" , 5);
        OrdinaryCitizen citizen2 = new OrdinaryCitizen("citizen2" , 6);

        List<Player> players = new ArrayList<>();
        players.add(godFather);
        players.add(sniper);
        players.add(detective);
        players.add(mafia);
        players.add(citizen1);
        players.add(citizen2);

        GameState gameState = new GameState(players);

        gameState.nextNight();
        // detective detect godfather
        assertEquals("NO_MAFIA" , detective.action(godFather));
        //sniper shot citizen1
        assertEquals("", sniper.action(citizen1));

        gameState.nextDay();
        //round 1
        assertTrue(godFather.isAsked());
        assertTrue(godFather.isAlive());
        assertFalse(citizen1.isAlive());
        assertTrue(sniper.isAlive());
        assertEquals(1 , sniper.getNumberOfBulletsLeft());

        //no one vote
        gameState.nextNight();
        assertTrue(gameState.getWinners().isEmpty());
        assertNull(gameState.getExecutedPlayer());
        assertNull(gameState.getSilentPlayerInLastRound());
        assertEquals(5 , gameState.getAlivePlayers().size());
        assertEquals(1 , gameState.getRound());

        //detective detect godfather
        assertEquals("MAFIA" , detective.action(godFather));
        //sniper shot mafia
        assertEquals("" , sniper.action(mafia));

        gameState.nextDay();
        //round 2
        assertTrue(gameState.getWinners().isEmpty());
        assertEquals(2 , gameState.getRound());
        assertTrue(gameState.getAlivePlayers().contains(sniper));
        assertEquals( 4, gameState.getAlivePlayers().size());
        assertTrue(godFather.isAlive());
        assertTrue(godFather.isAsked());
        assertFalse(godFather.isMute());
        assertFalse(mafia.isAlive());
        assertFalse(mafia.isHeal());
        assertEquals(0 , sniper.getNumberOfBulletsLeft());

        // no one vote
        gameState.nextNight();
        //detective detect godfather
        assertEquals("MAFIA" , detective.action(godFather));
        //sniper can't shot
        assertEquals("NO_BULLETS" , sniper.action(detective));
        //godfather kill himself!
        godFather.action(godFather);

        gameState.nextDay();
        // round 3
        // city won
        assertTrue(gameState.getWinners().contains(citizen1));
        assertEquals(4 , gameState.getWinners().size());
        assertFalse(godFather.isAlive());
        assertEquals(0 , sniper.getNumberOfBulletsLeft());
        assertEquals(3 , gameState.getRound());
        assertNull(gameState.getExecutedPlayer());
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
}