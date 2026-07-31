public class Mob extends Entity implements LVL{
    /* extends Entity, adds level & player functions
     * implements LVL, which increases damage in increments
     */
    private int lvl;
    protected int dmg = 10;

    public Mob(String name, int health, int maxHeal, int mana, int maxMana, int lvl) {
        super(name, health, maxHeal, mana, maxMana);
        this.lvl = lvl;
    }

    // Getters and setters
    public int getLvl() {return lvl;}

    public void display() {
        System.out.println("[" + getName() + "| HP: " + getHealth() + "/" + getmaxHeal() + "| lvl: " + lvl + "]");
    }

    // lvling system
    @Override
    public void lvl1() {
        lvl = 1;
    }
    @Override
    public void lvl2() {
        setHealth(getHealth() + 10);
        dmg += 5;
        lvl = 2;
    }
    @Override
    public void lvl3() {
        setHealth(getHealth() + 20);
        dmg += 10;
        lvl = 3;
    }

    public void attack(Entity x) {
            System.out.println(getName() + " slashes "+ x.getName() +" for 10 damage!");
            x.setHealth(x.getHealth() - dmg);
    }
}
