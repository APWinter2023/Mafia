package ir.sharif.math.ap2023.mafia.model;

public class Doctor extends Citizen {
    private int numberOfSelfSave;

    public Doctor(String name, int id) {
        super(name, id);
        numberOfSelfSave = 2;
    }

    @Override
    public String action(Player target) {
        if (this.equals(target)) {
            boolean saveResult = selfSave();
            if (!saveResult) {
                return "NO_SAVE";
            }
        }
        return "";
    }

    private boolean selfSave() {
        if (numberOfSelfSave > 0) {
            numberOfSelfSave--;
            setHeal(true);
            return true;
        }
        return false;
    }

    public int getNumberOfSelfSave() {
        return numberOfSelfSave;
    }
}
