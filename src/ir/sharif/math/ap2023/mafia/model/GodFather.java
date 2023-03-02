package ir.sharif.math.ap2023.mafia.model;

public class GodFather extends Mafia {
    private boolean asked = false;

    public GodFather(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        target.setKillByGodfather(true);
        return "";
    }

    @Override
    public boolean detectIsMafia() {
        if (asked) {
            return true;
        } else {
            asked = true;
            return false;
        }
    }

    public boolean isAsked() {
        return asked;
    }

}
