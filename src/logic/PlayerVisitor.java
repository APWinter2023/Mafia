package logic;

import model.*;

public interface PlayerVisitor {

    void detectiveVisit(Detective detective, Player target);
    void doctorVisit(Doctor doctor, Player target);
    void doctorLecterVisit(DoctorLecter doctorLecter, Player target);
    void godFatherVisit(GodFather godFather, Player target);
    void natashaVisit(Natasha natasha, Player target);
    void ordinaryMafiaVisit(OrdinaryMafia ordinaryMafia, Player target);
    void ordinaryCitizenVisit(OrdinaryCitizen ordinaryCitizen, Player target);
    void sniperVisit(Sniper sniper, Player target);

}
