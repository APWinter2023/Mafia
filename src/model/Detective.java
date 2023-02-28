package model;

public class Detective extends Citizen {
    public Detective(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        if (target.detectIsMafia()) {
            return "MAFIA";
        } else {
            return "NO_MAFIA";
        }
    }
}
