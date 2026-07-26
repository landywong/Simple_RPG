public class Tests {
    public static void main(String[] args) {
        System.out.println("Testing....");

        // Test player
        Player dude = new Player("Sora", 100,100,50,50,1);
        dude.display();
        // lvl up
        dude.lvl1();
        dude.lvl2();
        dude.lvl3();

        // Test mob
        Mob slime = new Mob("Slime", 50, 50, 2,2,1);
        slime.display();

        //attacks
        dude.attack(slime);
        slime.display();
        slime.attack(dude);
        dude.display();
        slime.setHealth(0);
        dude.attack(slime);
    }
}
