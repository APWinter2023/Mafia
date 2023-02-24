package model;

import logic.PlayerVisitor;

public class Doctor extends Citizen{
    public Doctor(String name, int id) {
        super(name, id);
    }

    public Doctor(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.doctorVisit(this,target);
    }
}
