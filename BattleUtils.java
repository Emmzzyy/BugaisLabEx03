import java.util.Random;

/**
 * Utility class for battle calculations.
 * Demonstrates method overloading.
 */
public class BattleUtils {

    static Random rand = new Random();

    /**
     * Basic attack (1d6)
     */
    public static int rollAttack() {
        return rand.nextInt(6) + 1;
    }

    /**
     * Attack with bonus
     * @param bonus additional damage
     */
    public static int rollAttack(int bonus) {
        return rollAttack() + bonus;
    }

    /**
     * Weapon-based attack
     * @param weapon weapon type
     * @param bonus bonus damage
     */
    public static int rollAttack(String weapon, int bonus) {
        int base;

        if (weapon.equals("sword")) {
            base = rand.nextInt(8) + 1; // 1d8
        } else {
            base = rand.nextInt(6) + 1; // default
        }

        return base + bonus;
    }

    // LOG METHODS (Overloaded)

    public static String formatLog(String event) {
        return "[LOG] " + event;
    }

    public static String formatLog(String attacker, int damage) {
        return attacker + " deals " + damage + " damage!";
    }

    public static String formatLog(String attacker, String target, int damage) {
        return attacker + " hits " + target + " for " + damage + " damage!";
    }
}