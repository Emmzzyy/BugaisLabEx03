# Dice Battle Arena

A console-based dice battle game built in Java that demonstrates object-oriented programming, method overloading, and Java API usage.

## Overview

The Dice Battle Arena is a turn-based combat game where fighters battle in an arena using dice rolls to determine attack damage. The game showcases Java programming concepts including encapsulation, method overloading, and the use of various Java API classes.

## Features

- **Multiple Fighters**: 6 unique fighters with different stats
- **Dynamic Combat**: Turn-based battle system with random targeting
- **Method Overloading**: Demonstrates 3 different overloaded method signatures
- **Java API Integration**: Uses ArrayList, Random, Collections, Scanner, Math, String, and Arrays
- **Visual Health Bars**: ASCII art health bars for each fighter
- **Comprehensive Logging**: Detailed combat logs and battle reports

## Requirements Met

### ✅ Java API Classes Used (7 classes)
1. **ArrayList** - Store dynamic list of Fighter objects
2. **Random** - Generate dice rolls and random targets
3. **Collections** - Shuffle turn order each round
4. **Scanner** - Accept user input (though minimal for demo purposes)
5. **Math** - Calculate damage with Math.max() and Math.min()
6. **String** - Format combat messages and build status displays
7. **Arrays** - Sort fighters by HP for rankings

### ✅ Method Overloading
The `BattleUtils` class contains overloaded methods:

#### rollAttack() - 3 versions:
```java
static int rollAttack()                          // Basic: 1d6
static int rollAttack(int bonus)                 // 1d6 + flat bonus  
static int rollAttack(String weapon, int bonus)  // Weapon dice + bonus
```

#### formatLog() - 3 versions:
```java
static String formatLog(String event)                              // Event message
static String formatLog(String attacker, int damage)               // Attacker + damage
static String formatLog(String attacker, String target, int damage)// Full combat log
```

### ✅ Javadoc Documentation
- All classes have comprehensive Javadoc comments
- All methods include @param and @return tags
- Proper author and version information

### ✅ Encapsulation
- `Fighter` class uses private fields with public getters/setters
- Proper data hiding and controlled access
- State management through methods like `takeDamage()`

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compilation
```bash
javac *.java
```

### Execution
```bash
java Main
```

## Game Flow

1. **Setup**: 6 fighters are created with unique names, HP, and attack power
2. **Demonstration**: All overloaded methods are demonstrated before battle
3. **Battle**: Fighters take turns attacking random opponents
4. **Combat**: Damage is calculated using dice rolls + attack power
5. **Elimination**: Fighters are removed when HP reaches 0
6. **Victory**: Last fighter standing wins

## Fighter Roster

| Fighter | HP | Attack Power | Description |
|---------|----|--------------|-------------|
| Goblin Warrior | 80 | 15 | Quick and agile |
| Orc Berserker | 120 | 20 | High health, moderate damage |
| Elf Archer | 70 | 18 | Low health, high attack |
| Dwarf Paladin | 100 | 12 | Balanced fighter |
| Dark Mage | 60 | 25 | Glass cannon - low HP, high damage |
| Dragon Knight | 140 | 16 | Tank with good damage |

## Combat System

### Attack Types
- **Basic Attack**: 1d6 roll + attack power modifier
- **Bonus Attack**: 1d6 + 2 bonus + attack power modifier  
- **Weapon Attack**: Variable dice (d4/d8/d10/d12) + 1 bonus + attack power modifier

### Damage Calculation
```java
damage = Math.max(1, Math.min(20, roll + (attackPower / 10)));
```
- Minimum damage: 1
- Maximum damage: 20
- Attack power provides bonus damage (1 per 10 attack power)

## File Structure

```
Lab03/
├── Fighter.java      # Fighter model class with encapsulation
├── BattleUtils.java  # Utility class with overloaded methods
├── Main.java         # Driver program and game logic
└── README.md         # This documentation file
```

## Educational Objectives

This project demonstrates mastery of:

- **Object-Oriented Programming**: Classes, objects, encapsulation
- **Method Overloading**: Multiple signatures for the same method name
- **Java API Usage**: Leveraging standard library classes effectively
- **Documentation**: Writing comprehensive Javadoc comments
- **Game Development**: Turn-based combat mechanics and state management




