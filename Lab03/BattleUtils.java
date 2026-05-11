import java.util.Random;

/**
 * Utility class for battle calculations and combat logging.
 * Demonstrates method overloading with multiple signatures.
 * 
 */
public class BattleUtils {
    
    private static Random random = new Random();
    
    /**
     * Performs a basic attack roll using a 6-sided die.
     * 
     * @return The result of rolling 1d6 (1-6)
     */
    public static int rollAttack() {
        return random.nextInt(6) + 1;
    }
    
    /**
     * Performs an attack roll with a flat bonus added to the result.
     * 
     * @param bonus The flat bonus to add to the roll
     * @return The result of rolling 1d6 plus the bonus
     */
    public static int rollAttack(int bonus) {
        return rollAttack() + bonus;
    }
    
    /**
     * Performs an attack roll using a specific weapon type with bonus.
     * Different weapons use different dice types.
     * 
     * @param weapon The weapon type ("dagger", "sword", "axe", "hammer")
     * @param bonus The flat bonus to add to the roll
     * @return The result of the weapon-specific roll plus bonus
     */
    public static int rollAttack(String weapon, int bonus) {
        int diceRoll;
        switch (weapon.toLowerCase()) {
            case "dagger":
                diceRoll = random.nextInt(4) + 1; // 1d4
                break;
            case "sword":
                diceRoll = random.nextInt(8) + 1; // 1d8
                break;
            case "axe":
                diceRoll = random.nextInt(10) + 1; // 1d10
                break;
            case "hammer":
                diceRoll = random.nextInt(12) + 1; // 1d12
                break;
            default:
                diceRoll = rollAttack(); // Default to 1d6
                break;
        }
        return diceRoll + bonus;
    }
    
    /**
     * Formats a basic combat log message with round information.
     * 
     * @param event The event description
     * @return Formatted log message with round prefix
     */
    public static String formatLog(String event) {
        return String.format("[EVENT] %s", event);
    }
    
    /**
     * Formats a combat log message showing damage dealt by an attacker.
     * 
     * @param attacker The name of the attacker
     * @param damage The amount of damage dealt
     * @return Formatted log message showing attacker and damage
     */
    public static String formatLog(String attacker, int damage) {
        return String.format("[COMBAT] %s deals %d damage!", attacker, damage);
    }
    
    /**
     * Formats a detailed combat log message showing attacker, target, and damage.
     * 
     * @param attacker The name of the attacker
     * @param target The name of the target
     * @param damage The amount of damage dealt
     * @return Formatted log message showing complete attack information
     */
    public static String formatLog(String attacker, String target, int damage) {
        return String.format("[COMBAT] %s hits %s for %d damage!", attacker, target, damage);
    }
    
    /**
     * Calculates damage based on attack roll and attack power.
     * Uses Math.max to ensure minimum damage and Math.round for rounding.
     * 
     * @param roll The attack roll result
     * @param attackPower The attacker's attack power stat
     * @return The calculated damage value
     */
    public static int calculateDamage(int roll, int attackPower) {
        // Base damage is roll + attack power bonus
        int baseDamage = roll + (attackPower / 10);
        // Ensure minimum damage of 1 and maximum of 20
        return Math.max(1, Math.min(20, baseDamage));
    }
    
    /**
     * Creates a visual health bar for a fighter.
     * 
     * @param currentHp Current hit points
     * @param maxHp Maximum hit points
     * @return String representation of health bar
     */
    public static String createHealthBar(int currentHp, int maxHp) {
        int barLength = 20;
        int filledBars = (currentHp * barLength) / maxHp;
        StringBuilder healthBar = new StringBuilder("[");
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledBars) {
                healthBar.append("█");
            } else {
                healthBar.append("░");
            }
        }
        healthBar.append("]");
        
        return healthBar.toString();
    }
    
    /**
     * Formats a fighter's status display with health bar.
     * 
     * @param fighter The fighter to display status for
     * @return Formatted status string with health bar
     */
    public static String formatFighterStatus(Fighter fighter) {
        String healthBar = createHealthBar(fighter.getHp(), fighter.getOriginalHp());
        return String.format("%-15s %s %3d/%3d HP", 
            fighter.getName(), healthBar, fighter.getHp(), fighter.getOriginalHp());
    }
}
