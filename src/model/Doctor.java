package model;

import logic.PlayerVisitor;

public class Doctor extends Citizen{
    private int numberOfSelfSave;
    public Doctor(String name, int id) {
        super(name, id);
        numberOfSelfSave = 2;
    }

    public Doctor(String name) {
        this(name , -1);
    }

    public boolean selfSave(){
        if (numberOfSelfSave > 0){
            numberOfSelfSave --;
            setAlive(true);
            return true;
        }
        return false;
    }

    @Override
    public void accept(PlayerVisitor playerVisitor, Player target) {
        playerVisitor.doctorVisit(this,target);
    }
}
