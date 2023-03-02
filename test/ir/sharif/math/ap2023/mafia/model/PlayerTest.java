package ir.sharif.math.ap2023.mafia.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private GodFather godFather;
    private Natasha natasha;
    private DoctorLecter doctorLecter;
    private OrdinaryMafia mafia;
    private Detective detective;
    private Doctor doctor;
    private Sniper sniper;
    private OrdinaryCitizen citizen1, citizen2;
    private Joker joker;

    @Before
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
        joker = new Joker("farzad", 9);

    }

    public void setValues() {
        detective.setMute(true);
        citizen1.setAlive(false);
        natasha.setAlive(false);
        citizen1.setHeal(true);


    }

    @Test
    public void playerGetterSetterTest() {
        assertEquals(1, detective.getId());
        assertEquals("tabas", mafia.getName());
        assertEquals("farzad", joker.getName());
        setValues();

        assertTrue(detective.isMute());
        assertFalse(citizen1.isAlive());
        assertFalse(natasha.isAlive());
        assertTrue(citizen1.isHeal());

        assertEquals(2, doctor.getNumberOfSelfSave());
        assertEquals(2, sniper.getNumberOfBulletsLeft());

    }

    @Test
    public void inheritanceTest() {
        assertTrue(godFather instanceof Mafia);
        assertTrue(godFather instanceof Player);
        assertTrue(mafia instanceof Mafia);
        assertTrue(natasha instanceof Mafia);
        assertTrue(doctorLecter instanceof Mafia);
        assertTrue(detective instanceof Citizen);
        assertTrue(detective instanceof Player);
        assertTrue(doctor instanceof Citizen);
        assertTrue(sniper instanceof Citizen);
        assertTrue(citizen1 instanceof Citizen);
        assertTrue(citizen2 instanceof Citizen);
        assertTrue(joker instanceof Player);
    }

    @After
    public void tearDown() throws Exception {
    }
}