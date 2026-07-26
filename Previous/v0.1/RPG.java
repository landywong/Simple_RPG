import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class RPG {
    public static void main(String[] args) {
    /*
     *Simple turn based RPG using JAVA - Landon Lwea
     * Player: controls 0-4 characters
     * Computer: controls 0-7 enemies
     *
     * Turn based actions input through Scanner
     * in varied stages with additional effects
     * Collectable items
     */

     // Welcome banner
        System.out.println("==============\n -SIMPLE-RPG- \n==============");

        // initialize scanner & random
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        //Final end stage & current
        final int endStage = 13;
        int stage = 1;

        //Adventurer & monster pool
        Player advent = new Player("Adventurer", 50, 50, 20, 20, 1);
        Mob slime = new Mob("Slime",10,10,2,2,1);
        Mob Rslime = new Mob("Red Slime",15,15,4,4,2);

        // Adventurer & mob containers
        ArrayList<Player> playList = new ArrayList<>();
        playList.add(advent);
        ArrayList<Mob> mobList = new ArrayList<>();
        mobList.add(slime);
        mobList.add(Rslime);

        // Adventurer team list & mob battle list
        ArrayList<Player> team = new ArrayList<>();
        ArrayList<Mob> battle = new ArrayList<>();

        // Main game loop
        while (stage < endStage) {
            if (stage == 1) {
                // Generate rand starters
                ArrayList<Player> starters = new ArrayList<>();
                starters.add(playList.get(rand.nextInt(playList.size())));
                starters.add(playList.get(rand.nextInt(playList.size())));
                starters.add(playList.get(rand.nextInt(playList.size())));
                // Player selects
                int options = 0;
                for (Player x : starters) {
                    x.select();
                    options += 1;
                    System.out.println(">> " + options);
                }
                System.out.println("Choose your adventurer.");
                String select = scanner.nextLine();
                team.add(starters.get(Integer.parseInt(select) - 1));
                System.out.println(team.get(0).getName() + " has joined your party!");
                // Display updated party
                System.out.println("Current Party:");
                for (Player x : team) {
                    x.display();
                }
            }

            // Load stage
            System.out.println("_________________________");
            System.out.println("Stage: " + stage);
            // Randomizer for mobs
            switch (stage) {
                case 1,2,3:
                    // Stage 1-3 spawn 1 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 4,6,7,8,9:
                    // Stage 4-9 spawn 3 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 5:
                    // Stage 5 - Mini-Boss
                    break;
                case 10:
                    // Stage 10 - Mini-Boss
                    break;
                case 11,12:
                    // Stage 11-12 spawn 5 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 13:
                    // Final stage - Boss
                    break;
            }

            // Display battle list
            for (Mob x : battle) {
                x.display();
            }
            System.out.println("_________________________");

            // Battle loop

            // Next stage & clear mob list
            battle.clear();
            stage += 1;
        }

        // Close scanner and exit game
        System.out.println("==============\n --BYE BYE-- \n==============");
        scanner.close();
    }
}

