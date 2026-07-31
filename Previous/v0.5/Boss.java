public class Boss extends Entity {
    /*
     * extends Entity, adds level & player functions
     */
    private boolean isFinal;
    protected int dmg = 25;

    public Boss(String name, int health, int maxHeal, int mana, int maxMana, boolean isFinal) {
        super(name, health, maxHeal, mana, maxMana);
        this.isFinal = isFinal;
    }

    // Setters & getters
    public boolean isFinal() {
        return isFinal;
    }

    public void display() {
        System.out.println("|" + getName() + ") HP: " + getHealth() + " DMG: " + dmg + "|");
    }

    public void attack(Entity x) {
        System.out.println(getName() + " casts doom ray "+ x.getName() +" for 25 damage!");
        x.setHealth(x.getHealth() - dmg);
    }
}