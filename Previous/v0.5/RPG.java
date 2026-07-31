import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class RPG {
    // User selection error method
    private static int select(String input, int max) {
        int value = Integer.parseInt(input);
        if (value < 1 || value > max) {
            throw new IllegalArgumentException("Choice must be between 1 and " + max);
        }
        return value;
    }

    // Battle loop next stage & game over
    private static boolean checkAlive(ArrayList<? extends Entity> list) {
        int dead = 0;
        for (Entity c : list) {
            if (c.isDead()) {
                dead += 1;
            }
        }

        // If team is wiped
        if (list.isEmpty()) {
            return false;
        }
        if (dead >= list.size()) {
            return false;
        }
        return true;
    }

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
        Player warrior = new Player("Warrior", 70, 70, 15, 15, 1);
        Player wizard = new Player("Wizard", 40, 40, 35, 35, 1);
        Mob slime = new Mob("Slime", 20, 20, 2, 2, 1);
        Mob Rslime = new Mob("Red Slime", 35, 35, 4, 4, 2);
        Boss dragon = new Boss("Red Dragon", 200,200,75,75,true);
        Boss slimeK = new Boss("Slime King", 100,100,15,15,false);
        Boss slimeQ = new Boss("Slime Queen", 80,80,25,25,false);

        // Adventurer & mob containers
        ArrayList<Player> playList = new ArrayList<>();
        playList.add(advent);
        playList.add(warrior);
        playList.add(wizard);

        ArrayList<Mob> mobList = new ArrayList<>();
        mobList.add(slime);
        mobList.add(Rslime);

        ArrayList<Boss> bosses = new ArrayList<>();
        // FinaL
        bosses.add(dragon);
        // Mini
        bosses.add(slimeK);
        bosses.add(slimeQ);

        // Adventurer team list & mob battle list
        ArrayList<Player> team = new ArrayList<>();
        ArrayList<Entity> battle = new ArrayList<>();

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

                // Try catch for selection
                System.out.println("Select an option: ");
                int choice = -1;
                boolean validChoice = false;
                while (!validChoice) {
                    String select = scanner.nextLine();
                    try {
                        choice = select(select, starters.size());
                        validChoice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number (1, 2, or 3).");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + ". Try again.");
                    }
                }
                team.add(starters.get(choice - 1));

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
                case 1, 2, 3:
                    // Stage 1-3 spawn 1 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 4, 6, 7, 8, 9:
                    // Stage 4-9 spawn 3 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 5:
                    // Stage 5 - Mini-Boss
                    while (battle.isEmpty()) {
                        Boss boss = bosses.get(rand.nextInt(playList.size() + 1));
                        if (!boss.isFinal()) {
                            battle.add(boss);
                        }
                    }
                    break;
                case 10:
                    // Stage 10 - Mini-Boss
                    while (battle.isEmpty()) {
                        Boss boss = bosses.get(rand.nextInt(playList.size() + 1));
                        if (!boss.isFinal()) {
                            battle.add(boss);
                        }
                    }
                    break;
                case 11, 12:
                    // Stage 11-12 spawn 5 mob
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    battle.add(mobList.get(rand.nextInt(playList.size() + 1)));
                    break;
                case 13:
                    // Stage 10 - Final Boss
                    while (battle.isEmpty()) {
                        Boss boss = bosses.get(rand.nextInt(playList.size() + 1));
                        if (boss.isFinal()) {
                            battle.add(boss);
                        }
                    }
                    break;
            }

            // Display battle list
            for (Entity x : battle) {
                x.display();
            }
            System.out.println("_________________________");

            /*
             * Battle loop
             *
             * counts rounds that pass
             * player selection try catchs
             * play selects attack, analyze, item
             */
            int round = 1;
            while (checkAlive(team) && checkAlive(battle)) {
                // Turn order goes player to mob
                ArrayList<Entity> fightOrder = new ArrayList<>();
                for (Player x : team) {
                    fightOrder.add(x);
                }
                for (Entity x : battle) {
                    fightOrder.add(x);
                }

                // Player turn
                System.out.println("[Select an option: 1-fight 2-analyze 3-item]");
                // Try catch for selection
                int choice = -1;
                boolean validChoice = false;
                while (!validChoice) {
                    String select = scanner.nextLine();
                    try {
                        choice = select(select, 3);
                        validChoice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number (1, 2, or 3).");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + ". Try again.");
                    }
                }

                // Switch case for options
                switch (choice) {
                    case 1:
                        // fight
                        break;
                    case 2:
                        // analyze
                        break;
                    case 3:
                        // item
                        break;
                }
            }

            // Next stage & clear mob list
            battle.clear();
            stage += 1;
        }

        // Close scanner and exit game
        System.out.println("==============\n --BYE BYE-- \n==============");
        scanner.close();
    }
}

