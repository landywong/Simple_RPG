public abstract class Entity {
    // Base for characters, containing health & mana
    private int health;
    private int mana;
    private String name;
    private int maxHeal;
    private int maxMana;

    // entries are their MAX stats
    public Entity (String name, int health, int maxHeal, int mana, int maxMana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.maxHeal = maxHeal;
        this.maxMana = maxMana;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getmaxHeal() {
        return maxHeal;
    }
    public void setmaxHeal(int heal) {
        this.maxHeal = heal;
    }

    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getmaxMana() {
        return maxMana;
    }
    public void setmaxMana(int mana) {
        this.maxMana = mana;
    }


    protected boolean dead = false;
    public boolean isDead() {
        if (health <= 0) {
            this.dead = true;
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("CHARACTER");
    }

    // empty mana etc. (Additions stats in further classes)
    public boolean isEmpty(int empty) {
        return empty <= 0;
    }
}
