package model;

import logic.PlayerVisitor;

public class Joker extends Player{
    public Joker(String name, int id) {
        super(name, id);
    }

    public Joker(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.jokerVisit(this,target);
    }
}
