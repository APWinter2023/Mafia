package model;

import logic.PlayerVisitor;

public class Natasha extends Mafia{
    public Natasha(String name, int id) {
        super(name, id);
    }

    public Natasha(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.natashaVisit(this,target);
    }
}
