package model;

public class Joker extends Player {
    public Joker(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        return "";
    }

    @Override
    public boolean detectIsMafia() {
        return false;
    }
}
