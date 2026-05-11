/**
 * Represents a fighter in the Dice Battle Arena.
 * Each fighter has a name, hit points (HP), and attack power.
 */
public class Fighter {
    
    private String name;
    private int hp;
    private int attackPower;
    private boolean alive;
    
    /**
     * Constructs a new Fighter with the specified name, HP, and attack power.
     * 
     * @param name The fighter's name
     * @param hp The fighter's initial hit points
     * @param attackPower The fighter's attack power stat
     */
    public Fighter(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.alive = true;
    }
    
    /**
     * Gets the fighter's name.
     * 
     * @return The fighter's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the fighter's name.
     * 
     * @param name The new name for the fighter
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the fighter's current hit points.
     * 
     * @return The fighter's current HP
     */
    public int getHp() {
        return hp;
    }
    
    /**
     * Sets the fighter's hit points.
     * If HP becomes 0 or below, the fighter is marked as dead.
     * 
     * @param hp The new HP value
     */
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp <= 0) {
            this.hp = 0;
            this.alive = false;
        }
    }
    
    /**
     * Gets the fighter's attack power.
     * 
     * @return The fighter's attack power
     */
    public int getAttackPower() {
        return attackPower;
    }
    
    /**
     * Sets the fighter's attack power.
     * 
     * @param attackPower The new attack power value
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    
    /**
     * Checks if the fighter is still alive.
     * 
     * @return true if the fighter is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * Sets the fighter's alive status.
     * 
     * @param alive The new alive status
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * Applies damage to the fighter.
     * Reduces HP by the specified damage amount.
     * 
     * @param damage The amount of damage to apply
     */
    public void takeDamage(int damage) {
        setHp(getHp() - damage);
    }
    
    /**
     * Returns a string representation of the fighter's current status.
     * 
     * @return String showing fighter name, HP, and alive status
     */
    @Override
    public String toString() {
        return String.format("%s - HP: %d/%d - %s", 
            name, hp, getOriginalHp(), alive ? "ALIVE" : "DEFEATED");
    }
    
    /**
     * Gets the original maximum HP of the fighter.
     * This is a helper method for display purposes.
     * 
     * @return The original HP value (assumed to be 100 for display)
     */
    public int getOriginalHp() {
        return 100; // Assuming all fighters start with 100 HP for display purposes
    }
}
