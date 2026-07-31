public class Stage {
    // battle stage including 5 stages with 3 tiers of special effects
    private int tier;
    private int stage;
    private int type;

    public Stage(int stage, int tier, int type) {
        this.stage = stage;
        this.tier = tier;
        this.type = type;
    }

    // getter and setters
    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
