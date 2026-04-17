/**
 * Represents a fighter in the arena.
 * Contains name, HP, and attack power.
 */
public class Fighter {

    private String name;
    private int hp;
    private int attackPower;

    // Constructor
    public Fighter(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    // Setter
    public void setHp(int hp) {
        this.hp = hp;
    }

    // Method to check if alive
    public boolean isAlive() {
        return hp > 0;
    }
}