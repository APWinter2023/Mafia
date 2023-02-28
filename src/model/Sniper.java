package model;

public class Sniper extends Citizen {
    private int numberOfBulletsLeft;

    public Sniper(String name, int id) {
        super(name, id);
        numberOfBulletsLeft = 2;
    }

    @Override
    public String action(Player target) {
        if (shot()) {
            target.setKillBySniper(true);
            return "";
        } else {
            return "NO_BULLETS";
        }
    }

    public int getNumberOfBulletsLeft() {
        return numberOfBulletsLeft;
    }

    public boolean shot() {
        return numberOfBulletsLeft-- > 0;
    }
}
