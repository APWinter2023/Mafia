package model;

public class Natasha extends Mafia {
    public Natasha(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        target.setMute(true);
        return "";
    }
}
