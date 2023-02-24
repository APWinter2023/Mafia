package model;

import logic.PlayerVisitor;

public class GodFather extends Mafia{

    private boolean asked=false;

    public GodFather(String name, int id) {
        super(name, id);
    }

    public GodFather(String name) {
        super(name);
    }

    public boolean isAsked() {
        return asked;
    }

    public void setAsked(boolean asked) {
        this.asked = asked;
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.godFatherVisit(this,target);
    }
}
