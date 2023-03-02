package ir.sharif.math.ap2023.mafia.model;

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
        if (numberOfBulletsLeft > 0){
            numberOfBulletsLeft --;
            return true;
        }
        return false;
    }
}
