package model;

import logic.PlayerVisitor;

public class DoctorLecter extends Mafia{
    public DoctorLecter(String name, int id) {
        super(name, id);
    }

    public DoctorLecter(String name) {
        super(name);
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.doctorLecterVisit(this,target);
    }
}
