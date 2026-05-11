import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Main driver class for the Dice Battle Arena game.
 * Creates fighters, manages combat rounds, and displays battle reports.
 * 
 * @author Engr. Violdan E. Bayocot
 * @version 1.0
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int roundNumber = 1;
    
    /**
     * Main entry point of the Dice Battle Arena game.
     * Sets up fighters, runs the battle, and displays results.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("     DICE BATTLE ARENA - TOURNAMENT     ");
        System.out.println("========================================");
        
        // Create and store fighters in ArrayList
        ArrayList<Fighter> fighters = createFighters();
        
        // Display initial fighter roster
        displayFighterRoster(fighters);
        
        // Demonstrate all overloaded methods before battle
        demonstrateOverloadedMethods();
        
        // Wait for user to start the battle
        System.out.println("\nPress Enter to begin the battle...");
        scanner.nextLine();
        
        // Run the main battle
        runBattleArena(fighters);
        
        // Display final results
        displayFinalResults(fighters);
        
        scanner.close();
    }
    
    /**
     * Creates the initial roster of fighters for the battle.
     * Creates at least 5 fighters with different stats.
     * 
     * @return ArrayList containing all created fighters
     */
    private static ArrayList<Fighter> createFighters() {
        ArrayList<Fighter> fighters = new ArrayList<>();
        
        fighters.add(new Fighter("Goblin Warrior", 80, 15));
        fighters.add(new Fighter("Orc Berserker", 120, 20));
        fighters.add(new Fighter("Elf Archer", 70, 18));
        fighters.add(new Fighter("Dwarf Paladin", 100, 12));
        fighters.add(new Fighter("Dark Mage", 60, 25));
        fighters.add(new Fighter("Dragon Knight", 140, 16));
        
        return fighters;
    }
    
    /**
     * Displays the initial roster of all fighters.
     * 
     * @param fighters ArrayList of fighters to display
     */
    private static void displayFighterRoster(ArrayList<Fighter> fighters) {
        System.out.println("\n=== FIGHTER ROSTER ===");
        System.out.println("========================================");
        
        for (Fighter fighter : fighters) {
            System.out.println(BattleUtils.formatFighterStatus(fighter));
        }
        
        System.out.println("========================================");
        System.out.println("Total Fighters: " + fighters.size());
    }
    
    /**
     * Demonstrates all overloaded methods from BattleUtils.
     * Shows method overloading with different signatures.
     */
    private static void demonstrateOverloadedMethods() {
        System.out.println("\n=== METHOD OVERLOADING DEMONSTRATION ===");
        
        // Demonstrate rollAttack overloads
        System.out.println("\n--- rollAttack() Overloads ---");
        int basicRoll = BattleUtils.rollAttack();
        System.out.println("Basic roll (1d6): " + basicRoll);
        
        int bonusRoll = BattleUtils.rollAttack(5);
        System.out.println("Roll with +5 bonus: " + bonusRoll);
        
        int weaponRoll = BattleUtils.rollAttack("sword", 3);
        System.out.println("Sword attack (1d8) with +3 bonus: " + weaponRoll);
        
        // Demonstrate formatLog overloads
        System.out.println("\n--- formatLog() Overloads ---");
        System.out.println(BattleUtils.formatLog("Battle begins!"));
        System.out.println(BattleUtils.formatLog("Goblin", 8));
        System.out.println(BattleUtils.formatLog("Goblin", "Orc", 12));
    }
    
    /**
     * Runs the main battle arena loop.
     * Continues until only one fighter remains.
     * 
     * @param fighters ArrayList of fighters in the battle
     */
    private static void runBattleArena(ArrayList<Fighter> fighters) {
        System.out.println("\n=== BATTLE BEGINS ===");
        
        while (getAliveFighterCount(fighters) > 1) {
            System.out.println("\n" + BattleUtils.formatLog("ROUND " + roundNumber));
            System.out.println("----------------------------------------");
            
            // Shuffle turn order each round
            Collections.shuffle(fighters);
            
            // Each living fighter takes a turn
            for (Fighter attacker : fighters) {
                if (attacker.isAlive()) {
                    executeFighterTurn(attacker, fighters);
                    
                    // Check if battle should end
                    if (getAliveFighterCount(fighters) <= 1) {
                        break;
                    }
                }
            }
            
            // Display round status
            displayRoundStatus(fighters);
            roundNumber++;
            
            // Brief pause between rounds
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Executes a single fighter's turn in combat.
     * 
     * @param attacker The fighter taking the turn
     * @param fighters ArrayList of all fighters
     */
    private static void executeFighterTurn(Fighter attacker, ArrayList<Fighter> fighters) {
        ArrayList<Fighter> targets = getValidTargets(attacker, fighters);
        
        if (targets.isEmpty()) {
            return; // No valid targets
        }
        
        // Select random target
        Fighter target = targets.get(random.nextInt(targets.size()));
        
        // Choose attack type randomly to demonstrate different overloads
        int damage;
        String attackType;
        
        int attackChoice = random.nextInt(3);
        switch (attackChoice) {
            case 0:
                // Basic attack
                damage = BattleUtils.calculateDamage(BattleUtils.rollAttack(), attacker.getAttackPower());
                attackType = "basic";
                break;
            case 1:
                // Bonus attack
                damage = BattleUtils.calculateDamage(BattleUtils.rollAttack(2), attacker.getAttackPower());
                attackType = "boosted";
                break;
            default:
                // Weapon attack
                String[] weapons = {"dagger", "sword", "axe", "hammer"};
                String weapon = weapons[random.nextInt(weapons.length)];
                damage = BattleUtils.calculateDamage(BattleUtils.rollAttack(weapon, 1), attacker.getAttackPower());
                attackType = weapon + " weapon";
                break;
        }
        
        // Apply damage
        target.takeDamage(damage);
        
        // Log the attack
        System.out.println(BattleUtils.formatLog(attacker.getName(), target.getName(), damage));
        System.out.println("  " + attacker.getName() + " uses " + attackType + " attack!");
        
        // Check if target was defeated
        if (!target.isAlive()) {
            System.out.println(BattleUtils.formatLog(target.getName() + " has been defeated!"));
        }
    }
    
    /**
     * Gets a list of valid targets for an attacker.
     * 
     * @param attacker The fighter looking for targets
     * @param fighters ArrayList of all fighters
     * @return ArrayList of valid (living and not self) targets
     */
    private static ArrayList<Fighter> getValidTargets(Fighter attacker, ArrayList<Fighter> fighters) {
        ArrayList<Fighter> targets = new ArrayList<>();
        
        for (Fighter fighter : fighters) {
            if (fighter.isAlive() && !fighter.equals(attacker)) {
                targets.add(fighter);
            }
        }
        
        return targets;
    }
    
    /**
     * Gets the count of living fighters.
     * 
     * @param fighters ArrayList of fighters to check
     * @return Number of living fighters
     */
    private static int getAliveFighterCount(ArrayList<Fighter> fighters) {
        int count = 0;
        for (Fighter fighter : fighters) {
            if (fighter.isAlive()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Displays the status of all fighters at the end of a round.
     * 
     * @param fighters ArrayList of fighters to display
     */
    private static void displayRoundStatus(ArrayList<Fighter> fighters) {
        System.out.println("\n--- End of Round " + roundNumber + " Status ---");
        
        // Sort fighters by HP (descending) for better display
        Fighter[] sortedFighters = fighters.toArray(new Fighter[0]);
        Arrays.sort(sortedFighters, (a, b) -> Integer.compare(b.getHp(), a.getHp()));
        
        for (Fighter fighter : sortedFighters) {
            if (fighter.isAlive()) {
                System.out.println(BattleUtils.formatFighterStatus(fighter));
            }
        }
        
        System.out.println("Fighters remaining: " + getAliveFighterCount(fighters));
    }
    
    /**
     * Displays the final battle results and winner.
     * 
     * @param fighters ArrayList of all fighters
     */
    private static void displayFinalResults(ArrayList<Fighter> fighters) {
        System.out.println("\n========================================");
        System.out.println("           BATTLE COMPLETE             ");
        System.out.println("========================================");
        
        // Find and announce the winner
        Fighter winner = null;
        for (Fighter fighter : fighters) {
            if (fighter.isAlive()) {
                winner = fighter;
                break;
            }
        }
        
        if (winner != null) {
            System.out.println("\n🏆 VICTOR: " + winner.getName().toUpperCase() + " 🏆");
            System.out.println(BattleUtils.formatFighterStatus(winner));
            System.out.println("The arena celebrates the new champion!");
        }
        
        // Display final statistics
        System.out.println("\n=== FINAL BATTLE STATISTICS ===");
        System.out.println("Total rounds: " + (roundNumber - 1));
        System.out.println("Fighters defeated: " + (fighters.size() - getAliveFighterCount(fighters)));
        
        System.out.println("\n--- Final Standings ---");
        Fighter[] sortedFighters = fighters.toArray(new Fighter[0]);
        Arrays.sort(sortedFighters, (a, b) -> Integer.compare(b.getHp(), a.getHp()));
        
        int rank = 1;
        for (Fighter fighter : sortedFighters) {
            String status = fighter.isAlive() ? "CHAMPION" : "DEFEATED";
            System.out.printf("%d. %-15s - %s\n", rank++, fighter.getName(), status);
        }
        
        System.out.println("========================================");
        System.out.println("Thank you for playing Dice Battle Arena!");
    }
}
