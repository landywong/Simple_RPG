public class Player extends Entity implements LVL, display{
    /* extends Entity, adds level & player functions
     * implements LVL, which activates the leveling system
     */
    private int lvl;

    // entries are their MAX stats
    public Player(String name, int health, int maxHeal, int mana, int maxMana, int lvl) {
        super(name, health, maxHeal, mana, maxMana);
        this.lvl = lvl;
    }

    // Getters and setters
    public int getLvl() {return lvl;}

    public void select() {
        System.out.print("[" + getName() + "| HP: " + getmaxHeal() + "| MP: " + getmaxMana() + "]");
    }

    public void display() {
        System.out.println("[" + getName() + "| HP: " + getHealth() + "/" + getmaxHeal() + "| MP: " + getMana() + "/" + getmaxMana() + "| lvl: " + lvl + "]");
    }

    // lvling system
    @Override
    public void lvl1() {
        System.out.println("RESET...");
        display();
    }
    @Override
    public void lvl2() {
        System.out.println("Level Up!");
        setHealth(getHealth() + 20);
        setMana(getMana() + 10);
        lvl = 2;
        display();
    }
    @Override
    public void lvl3() {
        System.out.println("Level Up! (MAX)");
        setHealth(getHealth() + 10);
        setMana(getMana() + 5);
        lvl = 3;
        display();
    }

    protected int dmg = 3;
    public void attack(Entity x) {
        if (x.isDead()) {
            System.out.println(getName() + " takes an extra stab to be sure.");
        } else {
            System.out.println(getName() + " slashes for 10 damage!");
            x.setHealth(x.getHealth() - dmg);
        }
    }
}
