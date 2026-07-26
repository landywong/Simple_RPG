public class Boss extends Entity {
    /* extends Entity, adds level & player functions
     * implements LVL, which increases damage in increments
     */
    private int lvl;
    private boolean isBoss;
    protected int dmg = 25;

    public Boss(String name, int health, int maxHeal, int mana, int maxMana, boolean isBoss) {
        super(name, health, maxHeal, mana, maxMana);
        this.isBoss = isBoss;
    }

    // Getters and setters
    public int getLvl() {return lvl;}

    public void display() {
        System.out.println("|" + getName() + ") HP: " + getHealth() + " DMG: " + dmg + "|");
    }

    public void attack(Entity x) {
        System.out.println(getName() + " casts doom ray "+ x.getName() +" for 25 damage!");
        x.setHealth(x.getHealth() - dmg);
    }
}