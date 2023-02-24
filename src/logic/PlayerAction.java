package logic;

import model.*;

public class PlayerAction implements PlayerVisitor{
    @Override
    public void detectiveVisit(Detective detective, Player target) {

        if (target instanceof GodFather){
            if (((GodFather) target).isAsked())
                detective.detect(target.getId(),true);
            else
                detective.detect(target.getId(),false);
        }else {
            if(target instanceof Mafia)
                detective.detect(target.getId(),true);
            else
                detective.detect(target.getId(),false);
        }
    }

    @Override
    public void doctorVisit(Doctor doctor, Player target) {
        if (doctor.getId() == target.getId())
            doctor.selfSave();
        else
            target.setAlive(true);
    }

    @Override
    public void doctorLecterVisit(DoctorLecter doctorLecter, Player target) {
        if(target instanceof Mafia)
            target.setAlive(true);
    }

    @Override
    public void godFatherVisit(GodFather godFather, Player target) {

        target.setAlive(false);
    }

    @Override
    public void natashaVisit(Natasha natasha, Player target) {
        target.setMute(true);
    }

    @Override
    public void ordinaryMafiaVisit(OrdinaryMafia ordinaryMafia, Player target) {

    }

    @Override
    public void ordinaryCitizenVisit(OrdinaryCitizen ordinaryCitizen, Player target) {

    }

    @Override
    public void sniperVisit(Sniper sniper, Player target) {
        if(sniper.shot())
            target.setAlive(false);
    }

    @Override
    public void jokerVisit(Joker joker, Player target) {

    }
}
