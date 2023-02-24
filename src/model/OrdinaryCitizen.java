package model;

import logic.PlayerVisitor;

public class OrdinaryCitizen extends Citizen{
    public OrdinaryCitizen(String name, int id) {
        super(name, id);
    }

    public OrdinaryCitizen(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.ordinaryCitizenVisit(this,target);
    }
}
