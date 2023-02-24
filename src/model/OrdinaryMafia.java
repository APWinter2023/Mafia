package model;

import logic.PlayerVisitor;

public class OrdinaryMafia extends Mafia{
    public OrdinaryMafia(String name, int id) {
        super(name, id);
    }

    public OrdinaryMafia(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.ordinaryMafiaVisit(this,target);
    }
}
