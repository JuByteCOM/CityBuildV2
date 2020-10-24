package de.crashmash.citybuild.manager.booster;

public class BoosterEntry {

    private final BoosterType type;
    private final int amount;

    public BoosterEntry(BoosterType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public BoosterType getType() {
        return this.type;
    }


    public int getAmount() {
        return this.amount;
    }
}
