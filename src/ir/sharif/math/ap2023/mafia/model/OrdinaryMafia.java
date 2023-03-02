package ir.sharif.math.ap2023.mafia.model;

public class OrdinaryMafia extends Mafia {
    public OrdinaryMafia(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        target.increaseMafiaVote();
        return "";
    }
}
