import java.util.*;

/**
 * Main driver class for Dice Battle Arena
 * Uses ArrayList, Random, Collections
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Fighter> fighters = new ArrayList<>();

        fighters.add(new Fighter("Ana", 30, 5));
        fighters.add(new Fighter("Ben", 25, 6));
        fighters.add(new Fighter("Cleo", 28, 4));
        fighters.add(new Fighter("Dan", 35, 3));
        fighters.add(new Fighter("Eli", 20, 7));

        System.out.println("=== Fighters ===");
        for (Fighter f : fighters) {
            System.out.println(f.getName() + " HP: " + f.getHp());
        }

        Random rand = new Random();

        while (fighters.size() > 1) {

            Collections.shuffle(fighters);

            System.out.println("\n--- New Round ---");

            for (Fighter attacker : fighters) {

                if (!attacker.isAlive()) continue;

                Fighter target = fighters.get(rand.nextInt(fighters.size()));

                if (attacker == target || !target.isAlive()) continue;

                int damage = BattleUtils.rollAttack(attacker.getAttackPower());

                target.setHp(target.getHp() - damage);

                System.out.println(
                    BattleUtils.formatLog(attacker.getName(), target.getName(), damage)
                );

                if (!target.isAlive()) {
                    System.out.println(target.getName() + " has been eliminated!");
                }
            }

            fighters.removeIf(f -> !f.isAlive());
        }

        System.out.println("\n🏆 Winner: " + fighters.get(0).getName());
    }
}