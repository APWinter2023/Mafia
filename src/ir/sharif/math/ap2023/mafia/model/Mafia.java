package ir.sharif.math.ap2023.mafia.model;

public abstract class Mafia extends Player {
    public Mafia(String name, int id) {
        super(name, id);
    }


    @Override
    public boolean detectIsMafia() {
        return true;
    }
}

