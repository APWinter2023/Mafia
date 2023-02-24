package model;

public abstract class Citizen extends Player{
    public Citizen(String name, int id) {
        super(name, id);
    }

    public Citizen(String name) {
        super(name);
    }
}
