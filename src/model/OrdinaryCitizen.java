package model;

public class OrdinaryCitizen extends Citizen {
    public OrdinaryCitizen(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        return "";
    }
}
